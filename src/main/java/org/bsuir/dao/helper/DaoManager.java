package org.bsuir.dao.helper;

import org.bsuir.connection.ConnectionPool;
import org.bsuir.dao.types.LessonDao;
import org.bsuir.dao.types.StudentDao;
import org.bsuir.dao.types.SubjectDao;
import org.bsuir.dao.types.TeacherDao;
import org.bsuir.exceptions.dao.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager implements AutoCloseable {
    private Connection connection;

    public DaoManager(ConnectionPool connectionPool) {
        this.connection = connectionPool.getConnection();
    }

    /**
     * @deprecated use DaoFactory instead
     * @return DaoManager obj
     */
    public static DaoManager create(){
        return new DaoManager(ConnectionPool.getInstance());
    }

    public StudentDao getStudentDao(){
     return new StudentDao(connection);
    }

    public TeacherDao getTeacherDao(){
        return new TeacherDao(connection);
    }

    public LessonDao getLessonDao(){
        return new LessonDao(connection);
    }

    public SubjectDao getSubjectDao() {
        return new SubjectDao(connection);
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
