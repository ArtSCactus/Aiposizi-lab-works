package org.bsuir.dao.types;

import org.bsuir.dao.common.AbstractDao;
import org.bsuir.dao.common.Dao;
import org.bsuir.dto.Subject;
import org.bsuir.rowmappers.SubjectRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class SubjectDao extends AbstractDao<Subject> implements Dao<Subject> {
private static final String REQUESTS_PATH = "requests/SQL_requests";
private ResourceBundle resources;

    public SubjectDao(Connection connection) {
        super(connection);
        resources = PropertyResourceBundle.getBundle(REQUESTS_PATH);
    }

    @Override
    public Optional<Subject> getById(Long id) {
        List<Subject> subjects = super.executeQuery(resources.getString("get_subject_by_id"), new SubjectRowMapper(), id);
        if (subjects.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(subjects.get(0));
        }
    }

    @Override
    public List<Subject> getAll() {
        return super.executeQuery(resources.getString("get_all_subjects"), new SubjectRowMapper());
    }

    @Override
    public int save(Subject item) {
        return super.executeUpdate(resources.getString("insert_odku_into_subjects"),
                item.getId(),
                item.getName(),
                item.getHours());
    }

    @Override
    public int removeById(Long id) {
        return super.executeUpdate(resources.getString("delete_subject_by_id"), id);
    }
}
