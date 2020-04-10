package org.bsuir.service.rest;

import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.DaoFactory;
import org.bsuir.dao.helper.DaoManager;
import org.bsuir.dto.Group;
import org.bsuir.service.GroupService;

import java.util.List;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class GroupRestService extends GroupService {
    @Override
    public List<Group> getAllGroups() {
        try(DaoManager dao = DaoFactory.createRestDaoManager()){
            Dao<Group> groupDao = dao.getGroupRestDao();
            return groupDao.getAll();
        }
    }
}
