package org.bsuir.service.rest;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dao.rest.TeacherRestDao;
import org.bsuir.dto.Teacher;
import org.bsuir.service.TeacherService;

import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class TeacherRestService extends TeacherService {
    @Override
    public List<Teacher> getAllTeachers() {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherRestDao();
            return teacherDao.getAll();
        }
    }

    @Override
    public void createTeacher(String name, String surname) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherRestDao();
            teacherDao.save(new Teacher(null, name, surname));
        }
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherRestDao();
            return teacherDao.getById(id);
        }
    }

    @Override
    public int update(Teacher teacher) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherRestDao();
            return teacherDao.save(teacher);
        }
    }

    @Override
    public void removeById(Long id) {
        try (DaoManager dao = DaoFactory.createRestDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherRestDao();
            teacherDao.removeById(id);
        }
    }
}
