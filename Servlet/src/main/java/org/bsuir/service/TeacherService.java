package org.bsuir.service;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dto.Teacher;

import java.util.List;
import java.util.Optional;

public class TeacherService {
    public List<Teacher> getAllTeachers() {
        try (DaoManager dao = DaoFactory.createDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherDao();
            return teacherDao.getAll();
        }
    }

    public void createTeacher(String name, String surname) {
        try (DaoManager dao = DaoFactory.createDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherDao();
            teacherDao.save(new Teacher(null, name, surname));
        }
    }

    public Optional<Teacher> getById(Long id) {
        try (DaoManager dao = DaoFactory.createDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherDao();
            return teacherDao.getById(id);
        }
    }

    public int update(Teacher teacher) {
        try (DaoManager dao = DaoFactory.createDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherDao();
            return teacherDao.save(teacher);
        }
    }

    public void removeById(Long id) {
        try (DaoManager dao = DaoFactory.createDaoManager()) {
            Dao<Teacher> teacherDao = dao.getTeacherDao();
            teacherDao.removeById(id);
        }
    }
}
