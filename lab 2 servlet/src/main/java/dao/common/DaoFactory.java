package dao.common;

import connection.ConnectionPool;
import dao.helper.DaoManager;

public class DaoFactory {
    public static DaoManager createDaoManager(){
        return new DaoManager(ConnectionPool.getInstance());
    }
}
