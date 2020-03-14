package org.bsuir.service;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dao.types.GroupDao;
import org.bsuir.dto.Group;

import java.util.List;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class GroupService {
public List<Group> getAllGroups(){
    try(DaoManager dao = DaoFactory.createDaoManager()){
        Dao<Group> groupDao = dao.getGroupDao();
       return groupDao.getAll();
    }
}
}
