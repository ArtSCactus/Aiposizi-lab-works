package org.bsuir.dao.rest;

import com.google.gson.Gson;
import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.RestRequestExecutor;
import org.bsuir.dao.util.UriFormatter;
import org.bsuir.dto.Student;
import org.bsuir.rowmappers.StudentRowMapper;

import java.util.List;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class StudentRestDao extends RestRequestExecutor<Student> implements Dao<Student> {
    private ResourceBundle uris;

    public StudentRestDao() {
        uris = PropertyResourceBundle.getBundle("requests/REST url");
    }

    @Override
    public Optional<Student> getById(Long id) {
        UriFormatter uriFormatter = new UriFormatter();
        String uri = uris.getString("get_student_by_id");
        uri = uriFormatter.format(uri, id);
        List<Student> studentList = super.executeGet(uri, new StudentRowMapper());
        if (studentList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(studentList.get(0));
        }
    }

    @Override
    public List<Student> getAll() {
        return super.executeGet(uris.getString("get_all_students"), new StudentRowMapper());
    }

    @Override
    public int save(Student item) {
        Gson gson = new Gson();
        String json = gson.toJson(item);
        return super.executePost(uris.getString("update_student"), json);
    }

    @Override
    public int removeById(Long id) {
        UriFormatter formatter = new UriFormatter();
        String uri = uris.getString("delete_student_by_id");
        uri = formatter.format(uri, id);
        return super.executeDelete(uri);
    }
}
