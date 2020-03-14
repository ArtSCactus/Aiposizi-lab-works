package org.bsuir.service;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dao.types.LessonDao;
import org.bsuir.dto.Lesson;

import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class LessonService {
    public List<Lesson> getAllLessons() {
        try (DaoManager dao = DaoFactory.createDaoManager()) {
            Dao<Lesson> lessonDao = dao.getLessonDao();
            return lessonDao.getAll();
        }
    }

    public Optional<Lesson> getById(Long id) {
        try (DaoManager dao = DaoFactory.createDaoManager()) {
            Dao<Lesson> lessonDao = dao.getLessonDao();
            return lessonDao.getById(id);
        }
    }

    public int update(Lesson lesson) {
        try (DaoManager dao = DaoFactory.createDaoManager()) {
            Dao<Lesson> lessonDao = dao.getLessonDao();
            return lessonDao.save(lesson);
        }
    }

    public int removeById(Long id) {
        try (DaoManager dao = DaoFactory.createDaoManager()) {
            Dao<Lesson> lessonDao = dao.getLessonDao();
            return lessonDao.removeById(id);
        }
    }
}
