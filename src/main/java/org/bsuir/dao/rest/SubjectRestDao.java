package org.bsuir.dao.rest;

import com.google.gson.Gson;
import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.RestRequestExecutor;
import org.bsuir.dao.util.UriFormatter;
import org.bsuir.dto.Student;
import org.bsuir.dto.Subject;
import org.bsuir.rowmappers.StudentRowMapper;
import org.bsuir.rowmappers.SubjectRowMapper;

import java.util.List;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class SubjectRestDao extends RestRequestExecutor<Subject> implements Dao<Subject> {
    private ResourceBundle uris;

    public SubjectRestDao() {
        uris = PropertyResourceBundle.getBundle("requests/REST url");
    }
    @Override
    public Optional<Subject> getById(Long id) {
        UriFormatter uriFormatter = new UriFormatter();
        String uri = uris.getString("get_subject_by_id");
        uri = uriFormatter.format(uri, id);
        List<Subject> teacherList = super.executeGet(uri, new SubjectRowMapper());
        if (teacherList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(teacherList.get(0));
        }
    }

    @Override
    public List<Subject> getAll() {
        return super.executeGet(uris.getString("get_all_subjects"), new SubjectRowMapper());
    }

    @Override
    public int save(Subject item) {
        Gson gson = new Gson();
        String json = gson.toJson(item);
        return super.executePost(uris.getString("update_subject"), json);
    }

    @Override
    public int removeById(Long id) {
        UriFormatter formatter = new UriFormatter();
        String uri = uris.getString("delete_subject_by_id");
        uri = formatter.format(uri, id);
        return super.executeDelete(uri);
    }
}
