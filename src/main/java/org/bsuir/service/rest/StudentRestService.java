package org.bsuir.service.rest;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dto.Student;
import org.bsuir.service.StudentService;

import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class StudentRestService extends StudentService {
    @Override
    public List<Student> getAllStudents() {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Student> studentDao = dao.getStudentRestDao();
            return studentDao.getAll();
        }
    }

    @Override
    public void update(Student item) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Student> studentDao = dao.getStudentRestDao();
            studentDao.save(item);
        }
    }

    @Override
    public void delete(Long id) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Student> studentDao = dao.getStudentRestDao();
            studentDao.removeById(id);
        }
    }

    @Override
    public Optional<Student> getById(Long id) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Student> studentDao = dao.getStudentRestDao();
            return studentDao.getById(id);
        }
    }
}
