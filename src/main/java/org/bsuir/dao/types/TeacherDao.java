package org.bsuir.dao.types;

import org.bsuir.dao.common.AbstractDao;
import org.bsuir.dao.common.Dao;
import org.bsuir.dto.Teacher;
import org.bsuir.rowmappers.TeacherRowMapper;

import java.sql.Connection;
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
        return 0;
    }

    @Override
    public void removeById(Long id) {

    }
}
