package org.bsuir.service;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dto.Subject;

import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class SubjectService {
public Optional<Subject> getById(Long id){
    try(DaoManager dao = DaoFactory.createDaoManager()){
        Dao<Subject> subjectDao = dao.getSubjectDao();
        return subjectDao.getById(id);
    }
}

public List<Subject> getAll(){
    try(DaoManager dao = DaoFactory.createDaoManager()){
        Dao<Subject> subjectDao = dao.getSubjectDao();
        return subjectDao.getAll();
    }
}
public int update(Subject subject){
    try(DaoManager dao = DaoFactory.createDaoManager()){
        Dao<Subject> subjectDao = dao.getSubjectDao();
        return subjectDao.save(subject);
    }
}

public int removeById(Long id){
    try(DaoManager dao = DaoFactory.createDaoManager()){
        Dao<Subject> subjectDao = dao.getSubjectDao();
        return subjectDao.removeById(id);
    }
}

public void create(String name, Integer hours){
    try(DaoManager dao = DaoFactory.createDaoManager()){
        Dao<Subject> subjectDao = dao.getSubjectDao();
        subjectDao.save(new Subject(null, name, hours));
    }
}
}
