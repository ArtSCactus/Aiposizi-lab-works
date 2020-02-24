package org.bsuir.rowmappers;

import org.bsuir.dto.Subject;
import org.bsuir.exceptions.rowmapper.RowMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectRowMapper implements RowMapper<Subject> {
    private static final String ID_COL_NAME = "id";
    private static final String NAME_COL = "name";
    private static final String HOURS_COL_NAME ="hours";
    @Override
    public Subject map(ResultSet resultSet) {
        try {
            return new Subject(resultSet.getLong(ID_COL_NAME),
                    resultSet.getString(NAME_COL),
                    resultSet.getInt(HOURS_COL_NAME));
        } catch (SQLException e) {
            throw new RowMapperException(e);
        }
    }
}
