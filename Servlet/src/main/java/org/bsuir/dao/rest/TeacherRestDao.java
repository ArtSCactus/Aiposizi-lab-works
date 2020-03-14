package org.bsuir.dao.rest;

import com.google.gson.Gson;
import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.RestRequestExecutor;
import org.bsuir.dao.util.UriFormatter;
import org.bsuir.dto.Teacher;
import org.bsuir.rowmappers.TeacherRowMapper;

import java.util.List;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class TeacherRestDao extends RestRequestExecutor<Teacher> implements Dao<Teacher> {
    private ResourceBundle uris;

    public TeacherRestDao() {
        uris = PropertyResourceBundle.getBundle("requests/REST url");
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        UriFormatter uriFormatter = new UriFormatter();
        String uri = uris.getString("get_teacher_by_id");
        uri = uriFormatter.format(uri, id);
        List<Teacher> teacherList = super.executeGet(uri, new TeacherRowMapper());
        if (teacherList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(teacherList.get(0));
        }
    }

    @Override
    public List<Teacher> getAll() {
        return super.executeGet(uris.getString("get_all_teachers"), new TeacherRowMapper());
    }

    @Override
    public int save(Teacher item) {
        Gson gson = new Gson();
        String json = gson.toJson(item);
        return super.executePost(uris.getString("update_teacher"), json);
    }

    @Override
    public int removeById(Long id) {
        UriFormatter formatter = new UriFormatter();
        String uri = uris.getString("delete_teacher_by_id");
        uri = formatter.format(uri, id);
        return super.executeDelete(uri);
    }

}
