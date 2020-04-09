package org.bsuir.dao.rest;

import com.google.gson.Gson;
import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.RestRequestExecutor;
import org.bsuir.dao.util.UriFormatter;
import org.bsuir.dto.Group;
import org.bsuir.rowmappers.GroupRowMapper;

import java.util.List;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class GroupRestDao extends RestRequestExecutor<Group> implements Dao<Group> {
    private ResourceBundle uris;

    public GroupRestDao() {
        uris = PropertyResourceBundle.getBundle("requests/REST url");
    }

    @Override
    public Optional<Group> getById(Long id) {
        UriFormatter uriFormatter = new UriFormatter();
        String uri = uris.getString("get_group_by_id");
        uri = uriFormatter.format(uri, id);
        List<Group> teacherList = super.executeGet(uri, new GroupRowMapper());
        if (teacherList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(teacherList.get(0));
        }
    }

    @Override
    public List<Group> getAll() {
        return super.executeGet(uris.getString("get_all_groups"), new GroupRowMapper());
    }

    @Override
    public int save(Group item) {
        Gson gson = new Gson();
        String json = gson.toJson(item);
        return super.executePost(uris.getString("update_group"), json);
    }

    @Override
    public int removeById(Long id) {
        UriFormatter formatter = new UriFormatter();
        String uri = uris.getString("delete_group_by_id");
        uri = formatter.format(uri, id);
        return super.executeDelete(uri);
    }
}
