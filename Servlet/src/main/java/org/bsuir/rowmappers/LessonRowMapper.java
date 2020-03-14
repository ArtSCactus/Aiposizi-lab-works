package org.bsuir.rowmappers;

import com.google.gson.reflect.TypeToken;
import org.bsuir.dto.Lesson;
import org.bsuir.dto.Teacher;
import org.bsuir.exceptions.rowmapper.RowMapperException;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonRowMapper implements RowMapper<Lesson> {
    private static final String ID_COL_NAME = "id";
    private static final String TEACHER_ID_COL_NAME = "teacher";
    private static final String GROUP_ID_COL_NAME = "group";
    private static final String SUBJECT_COL_NAME = "subject";
    @Override
    public Lesson map(ResultSet resultSet) {
        try {
            return new Lesson(resultSet.getLong(ID_COL_NAME),
                    resultSet.getLong(GROUP_ID_COL_NAME),
                    resultSet.getLong(SUBJECT_COL_NAME),
                    resultSet.getLong(TEACHER_ID_COL_NAME));
        } catch (SQLException e) {
            throw new RowMapperException(e);
        }
    }

    @Override
    public Type getJsonMeta() {
        return new TypeToken<Lesson>(){}.getType();
    }
}
