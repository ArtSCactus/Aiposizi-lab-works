package org.bsuir.service;

import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dao.types.StudentDao;
import org.bsuir.dto.Student;

import java.util.List;

public class StudentService {
    public List<Student> getAllStudents(){
        try(DaoManager dao = DaoFactory.createDaoManager()){
            StudentDao studentDao = dao.getStudentDao();
            return studentDao.getAll();
        }
    }
}
