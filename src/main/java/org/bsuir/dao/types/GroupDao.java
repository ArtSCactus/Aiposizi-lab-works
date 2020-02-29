package org.bsuir.dao.types;

import org.bsuir.dao.common.AbstractDao;
import org.bsuir.dao.common.Dao;
import org.bsuir.dto.Group;
import org.bsuir.rowmappers.GroupRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class GroupDao extends AbstractDao<Group> implements Dao<Group> {
    private static final String REQUESTS_PATH = "requests/SQL_requests";
    private ResourceBundle requests;

    public GroupDao(Connection connection) {
        super(connection);
        requests = PropertyResourceBundle.getBundle(REQUESTS_PATH);
    }

    @Override
    public Optional<Group> getById(Long id) {
        List<Group> groupsList = super.executeQuery(requests.getString("get_group_by_id"), new GroupRowMapper(), id);
        if (groupsList.isEmpty()){
            return Optional.empty();
        } else {
           return Optional.of(groupsList.get(0));
        }
    }

    @Override
    public List<Group> getAll() {
        return super.executeQuery(requests.getString("get_all_groups"), new GroupRowMapper());
    }

    @Override
    public int save(Group item) {
        return 0;
    }

    @Override
    public int removeById(Long id) {
        return 0;
    }
}
