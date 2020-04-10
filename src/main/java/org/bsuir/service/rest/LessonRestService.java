package org.bsuir.service.rest;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dto.Lesson;
import org.bsuir.service.LessonService;

import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class LessonRestService extends LessonService {
    @Override
    public List<Lesson> getAllLessons() {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Lesson> lessonDao = dao.getLessonRestDao();
            return lessonDao.getAll();
        }
    }

    @Override
    public Optional<Lesson> getById(Long id) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Lesson> lessonDao = dao.getLessonRestDao();
            return lessonDao.getById(id);
        }
    }

    @Override
    public int update(Lesson lesson) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Lesson> lessonDao = dao.getLessonRestDao();
            return lessonDao.save(lesson);
        }
    }

    @Override
    public int removeById(Long id) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Lesson> lessonDao = dao.getLessonRestDao();
            return lessonDao.removeById(id);
        }
    }

}
