package org.bsuir.dao.types;

import org.bsuir.dao.common.AbstractDao;
import org.bsuir.dao.common.Dao;
import org.bsuir.dto.Student;
import org.bsuir.rowmappers.StudentRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentDao extends AbstractDao<Student> implements Dao<Student> {
    private static final String RESOURCES_PATH = "requests/SQL_requests";
    private ResourceBundle resources;

    public StudentDao(Connection connection) {
        super(connection);
        resources = ResourceBundle.getBundle(RESOURCES_PATH);
    }

    @Override
    public Optional<Student> getById(Long id) {
        List<Student> students = super.executeQuery(resources.getString("get_student_by_id"), new StudentRowMapper(), id);
        if (students.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(students.get(0));
        }
    }

    @Override
    public List<Student> getAll() {
        return super.executeQuery(resources.getString("get_all_students"), new StudentRowMapper());
    }

    @Override
    public int save(Student item) {
        return super.executeUpdate(resources.getString("insert_odku_student"),
                item.getId(),
                item.getName(),
                item.getSurname(),
                item.getRating(),
                item.getGroup());
    }

    @Override
    public int removeById(Long id) {
       return super.executeUpdate(resources.getString("delete_student_by_id"), id);
    }
}
