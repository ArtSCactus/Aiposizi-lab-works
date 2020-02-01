package dao.helper;

import connection.ConnectionPool;
import exceptions.dao.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager implements AutoCloseable {
    private Connection connection;

    public DaoManager(ConnectionPool connectionPool) {
        this.connection = connectionPool.getConnection();
    }

    /**
     * @deprecated use DaoFactory instead
     * @return DaoManager obj
     */
    public static DaoManager create(){
        return new DaoManager(ConnectionPool.getInstance());
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
