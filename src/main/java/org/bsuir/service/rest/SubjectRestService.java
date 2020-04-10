package org.bsuir.service.rest;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dao.types.SubjectDao;
import org.bsuir.dto.Subject;
import org.bsuir.service.SubjectService;

import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class SubjectRestService extends SubjectService {
    @Override
    public Optional<Subject> getById(Long id) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Subject> subjectDao = dao.getSubjectRestDao();
            return subjectDao.getById(id);
        }
    }

    @Override
    public List<Subject> getAll() {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Subject> subjectDao = dao.getSubjectRestDao();
            return subjectDao.getAll();
        }
    }

    @Override
    public int update(Subject subject) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Subject> subjectDao = dao.getSubjectRestDao();
            return subjectDao.save(subject);
        }
    }

    @Override
    public int removeById(Long id) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Subject> subjectDao = dao.getSubjectRestDao();
            return subjectDao.removeById(id);
        }
    }

    @Override
    public void create(String name, Integer hours) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Subject> subjectDao = dao.getSubjectRestDao();
            subjectDao.save(new Subject(null, name, hours));
        }
    }
}
