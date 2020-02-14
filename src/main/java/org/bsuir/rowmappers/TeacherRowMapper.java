package org.bsuir.rowmappers;

import org.bsuir.dto.Teacher;
import org.bsuir.exceptions.rowmapper.RowMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRowMapper implements RowMapper<Teacher> {
    private static final String ID_COL_NAME = "id";
    private static final String NAME_COL = "name";
    private static final String SURNAME_COL = "surname";

    @Override
    public Teacher map(ResultSet resultSet) {
        try {
            return new Teacher(resultSet.getLong(ID_COL_NAME),
                    resultSet.getString(NAME_COL),
                    resultSet.getString(SURNAME_COL));
        } catch (SQLException e) {
            throw new RowMapperException(e);
        }
    }
}
