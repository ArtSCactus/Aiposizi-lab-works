package org.bsuir.dao.common;

import org.bsuir.exceptions.dao.DaoException;
import org.bsuir.rowmappers.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {
    protected Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String sql, RowMapper<T> rowMapper, Object... params) throws DaoException {
        try (PreparedStatement statement = prepareStatement(sql, params)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<T> objects = new ArrayList<>();
                while (resultSet.next()) {
                    objects.add(rowMapper.map(resultSet));
                }
                return objects;
            }
        } catch (SQLException e) {
            throw new DaoException("An error occurred while request executing", e);
        }
    }

    protected int executeUpdate(String sql, Object... params) {
        try (PreparedStatement statement = prepareStatement(sql, params)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error occurred while request executing", e);
        }
    }

    private PreparedStatement prepareStatement(String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int index = 1; index <= params.length; index++) {
            try {
                statement.setObject(index, params[index - 1]);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException(e);
            }
        }
        return statement;
    }
}
