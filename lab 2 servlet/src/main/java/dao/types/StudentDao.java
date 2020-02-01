package dao.types;

import dao.common.AbstractDao;
import dao.common.Dao;
import dto.Student;
import rowmappers.RowMapper;
import rowmappers.StudentRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentDao extends AbstractDao<Student> implements Dao<Student> {
   private static final String RESOURCES_PATH = "requests/SQL requests";
    private ResourceBundle requests;
    protected StudentDao(Connection connection) {
        super(connection);
        requests = ResourceBundle.getBundle(RESOURCES_PATH);
    }

    @Override
    public Optional<Student> getById(Long id) {
        List<Student> students = super.executeQuery(requests.getString("get_all_students_by_id"), new StudentRowMapper(), id);
        if (students.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(students.get(0));
        }
    }

    @Override
    public List<Student> getAll() {
        return super.executeQuery(requests.getString("get_all_students"), new StudentRowMapper());
    }

    @Override
    public int save(Student item) {
        return 0;
    }

    @Override
    public void removeById(Long id) {

    }
}
