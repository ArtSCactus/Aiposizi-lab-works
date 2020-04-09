package org.bsuir.dao.types;

import org.bsuir.dao.common.AbstractDao;
import org.bsuir.dao.common.Dao;
import org.bsuir.dto.Lesson;
import org.bsuir.rowmappers.LessonRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class LessonDao extends AbstractDao<Lesson> implements Dao<Lesson> {
    private static final String REQUESTS_PATH = "requests/SQL_requests";
    private ResourceBundle requests;

    public LessonDao(Connection connection) {
        super(connection);
        requests = ResourceBundle.getBundle(REQUESTS_PATH);
    }

    @Override
    public Optional<Lesson> getById(Long id) {
        List<Lesson> lessons = super.executeQuery(requests.getString("get_lesson_by_id"), new LessonRowMapper(), id);
        if (lessons.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(lessons.get(0));
        }
    }

    @Override
    public List<Lesson> getAll() {
        return super.executeQuery(requests.getString("get_all_lessons"), new LessonRowMapper());
    }

    @Override
    public int save(Lesson item) {
        return super.executeUpdate(requests.getString("insert_odku_into_lessons"),
                item.getId(),
                item.getTeacherId(),
                item.getGroupId(),
                item.getSubjectId());
    }

    @Override
    public int removeById(Long id) {
        return super.executeUpdate(requests.getString("delete_lesson_by_id"), id);
    }
}
