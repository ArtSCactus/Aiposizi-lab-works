package org.bsuir.dao.helper;

import org.bsuir.connection.ConnectionPool;
import org.bsuir.dao.rest.*;
import org.bsuir.dao.types.*;
import org.bsuir.exceptions.dao.DaoException;
import org.bsuir.service.rest.LessonRestService;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager implements AutoCloseable {
    private Connection connection;

    public DaoManager(ConnectionPool connectionPool) {
        this.connection = connectionPool.getConnection();
    }

    /**Should be called if no database connection is needed.
     *
     */
    public DaoManager(){
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

    public TeacherRestDao getTeacherRestDao() {
        return new TeacherRestDao();
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

    public StudentRestDao getStudentRestDao() {
        return new StudentRestDao();
    }

    public LessonRestDao getLessonRestDao() {
        return new LessonRestDao();
    }

    public SubjectRestDao getSubjectRestDao() {
        return new SubjectRestDao();
    }

    public GroupRestDao getGroupRestDao() {
        return new GroupRestDao();
    }

    @Override
    public void close() throws DaoException {
        try {
            if (connection !=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
