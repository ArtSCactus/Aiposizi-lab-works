package org.bsuir.rowmappers;

import com.google.gson.reflect.TypeToken;
import org.bsuir.dto.Student;
import org.bsuir.dto.Teacher;
import org.bsuir.exceptions.rowmapper.RowMapperException;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    private static final String ID_COL_NAME = "id";
    private static final String NAME_COL_NAME ="name";
    private static final String SURNAME_COL_NAME ="surname";
    private static final String RATING_COL_NAME = "rating";
    private static final String GROUP_ID_COL_NAME = "group";
    @Override
    public Student map(ResultSet resultSet) {
        try {
            return new Student(resultSet.getLong(ID_COL_NAME),
                    resultSet.getString(NAME_COL_NAME),
                    resultSet.getString(SURNAME_COL_NAME),
                    resultSet.getLong(RATING_COL_NAME),
                    resultSet.getLong(GROUP_ID_COL_NAME));
        } catch (SQLException e) {
            throw new RowMapperException(e);
        }
    }

    @Override
    public Type getJsonMeta() {
        return new TypeToken<Student>(){}.getType();
    }
}
