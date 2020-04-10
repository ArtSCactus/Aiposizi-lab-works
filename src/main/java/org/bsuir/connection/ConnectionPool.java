package org.bsuir.connection;

import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bsuir.exceptions.connection.DriverManagerException;
import org.bsuir.exceptions.files.ResourcesLoadingException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static int initialCapacity = 10;
    private static final String RESOURCES_PATH = "config/connection pool.properties";
    private static volatile ConnectionPool pool;
    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> busyConnections;

    private ConnectionPool() throws SQLException {
        try {
            LOCK.lock();
            if (pool != null) {
                throw new UnsupportedOperationException();
            } else {
                DriverManager.registerDriver(new Driver());
                initConnections();
            }
        } finally {
            LOCK.unlock();
        }
    }

    private void initConnections() {
        Properties properties = new Properties();
        try {
            ClassLoader loader = getClass().getClassLoader();
            InputStream inputStream = loader.getResourceAsStream(RESOURCES_PATH);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Failed to load resources from properties file (" + RESOURCES_PATH + ").");
            throw new ResourcesLoadingException("An error occurred while loading resources", e);
        }
        String databaseUrl = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String schema = properties.getProperty("schema");
        if (properties.containsKey("initialPoolSize")) {
            initialCapacity = Integer.parseInt(properties.getProperty("initialPoolSize"));
        }
        freeConnections = new ArrayBlockingQueue<>(initialCapacity);
        busyConnections = new ArrayBlockingQueue<>(initialCapacity);

        for (int index = 0; index < initialCapacity; index++) {
            try {
                Connection connection = new ProxyConnection(DriverManager.getConnection(databaseUrl, user, password));
                connection.setSchema(schema);
                freeConnections.offer(connection);
            } catch (SQLException e) {
                LOGGER.error("Failed to get connection from DriverManager" + e.getMessage());
                throw new DriverManagerException("Failed to get connection from DriverManager: " + e.getMessage(), e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (pool == null) {
            try {
                LOCK.lock();
                if (pool == null) {
                    pool = new ConnectionPool();
                }
            } catch (SQLException e) {
                throw new RuntimeException("An error occurred while ConnectionPool creating", e);
            } finally {
                LOCK.unlock();
            }
        }
        return pool;
    }

    public void releaseConnection(Connection connection) {
        try {
            LOCK.lock();
            freeConnections.offer(connection);
            busyConnections.remove(connection);
        }finally{
            LOCK.unlock();
        }
    }

    public Connection getConnection() {
        LOCK.lock();
        try {
            Connection connection = freeConnections.take();
            busyConnections.offer(connection);
            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException("An error occurred while getting connection from connection pool", e);
        } finally {
            LOCK.unlock();
        }
    }

    public void terminate() {
        LOCK.lock();
        try {
            for (Connection connection : freeConnections) {
                ((ProxyConnection) connection).terminate();
            }
            for (Connection connection : busyConnections) {
                ((ProxyConnection) connection).terminate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to terminate connections. Message: " + e.getMessage());
        } finally {
            LOCK.unlock();
        }
    }

}
