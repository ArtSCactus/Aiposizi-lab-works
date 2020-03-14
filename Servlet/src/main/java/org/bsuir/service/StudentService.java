package org.bsuir.service;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dao.types.StudentDao;
import org.bsuir.dto.Student;

import java.util.List;
import java.util.Optional;

public class StudentService {
    public List<Student> getAllStudents(){
        try(DaoManager dao = DaoFactory.createDaoManager()){
            StudentDao studentDao = dao.getStudentDao();
            return studentDao.getAll();
        }
    }

    public void update(Student item){
        try(DaoManager dao = DaoFactory.createDaoManager()){
            Dao<Student> studentDao = dao.getStudentDao();
            studentDao.save(item);
        }
    }

    public void delete(Long id){
        try(DaoManager dao = DaoFactory.createDaoManager()){
            Dao<Student> studentDao = dao.getStudentDao();
            studentDao.removeById(id);
        }
    }

    public Optional<Student> getById(Long id){
        try(DaoManager dao = DaoFactory.createDaoManager()){
            Dao<Student> studentDao = dao.getStudentDao();
            return studentDao.getById(id);
        }
    }
}
