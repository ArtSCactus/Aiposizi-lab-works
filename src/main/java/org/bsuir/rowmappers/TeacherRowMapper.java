package org.bsuir.rowmappers;

import com.google.gson.reflect.TypeToken;
import org.bsuir.dto.Teacher;
import org.bsuir.exceptions.rowmapper.RowMapperException;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeacherRowMapper implements RowMapper<Teacher> {
    private static final String ID_COL_NAME = "id";
    private static final String NAME_COL = "name";
    private static final String SURNAME_COL = "surname";
    private static final String INSTANCE_PATH = "org.bsuir.dto.Teacher";

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

    @Override
    public Type getJsonMeta() {
        return new TypeToken<Teacher>() {
        }.getType();
    }

    // @Override
    public Type getJsonListMeta() {
        return new TypeToken<List<Teacher>>() {
        }.getType();
    }
}
