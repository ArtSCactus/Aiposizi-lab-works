package org.bsuir.dao.types;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bsuir.dao.common.AbstractDao;
import org.bsuir.dao.common.Dao;
import org.bsuir.dto.Teacher;
import org.bsuir.rowmappers.TeacherRowMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TeacherDao extends AbstractDao<Teacher> implements Dao<Teacher> {
    private ResourceBundle resources;

    public TeacherDao(Connection connection) {
        super(connection);
        this.resources = ResourceBundle.getBundle("requests/SQL_requests");
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        List<Teacher> teachers = super.executeQuery(resources.getString("get_teacher_by_id"), new TeacherRowMapper(), id);
        if (teachers.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(teachers.get(0));
        }
    }

    @Override
    public List<Teacher> getAll() {
        return super.executeQuery(resources.getString("get_all_teachers"), new TeacherRowMapper());
    }

    @Override
    public int save(Teacher item) {
        return super.executeUpdate(resources.getString("insert_odku_into_teachers"),
                item.getId(),
                item.getName(),
                item.getSurname());
    }

    @Override
    public int removeById(Long id) {
        return super.executeUpdate(resources.getString("delete_teacher_by_id"), id);
    }
}
