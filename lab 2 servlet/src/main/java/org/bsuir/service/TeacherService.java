package org.bsuir.service;

import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dao.types.TeacherDao;
import org.bsuir.dto.Teacher;

import java.util.List;

public class TeacherService {
    public List<Teacher> getAllTeachers(){
        try(DaoManager dao = DaoFactory.createDaoManager()){
            TeacherDao teacherDao = dao.getTeacherDao();
            return teacherDao.getAll();
        }
    }
}
