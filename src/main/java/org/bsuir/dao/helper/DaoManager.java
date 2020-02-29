package org.bsuir.dao.helper;

import org.bsuir.connection.ConnectionPool;
import org.bsuir.dao.types.*;
import org.bsuir.exceptions.dao.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager implements AutoCloseable {
    private Connection connection;

    public DaoManager(ConnectionPool connectionPool) {
        this.connection = connectionPool.getConnection();
    }

    /**
     * @return DaoManager obj
     * @deprecated use DaoFactory instead
     */
    public static DaoManager create() {
        return new DaoManager(ConnectionPool.getInstance());
    }

    public StudentDao getStudentDao() {
        return new StudentDao(connection);
    }

    public TeacherDao getTeacherDao() {
        return new TeacherDao(connection);
    }

    public LessonDao getLessonDao() {
        return new LessonDao(connection);
    }

    public SubjectDao getSubjectDao() {
        return new SubjectDao(connection);
    }

    public GroupDao getGroupDao() {
        return new GroupDao(connection);
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
