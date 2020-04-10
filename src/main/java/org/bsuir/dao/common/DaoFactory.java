package org.bsuir.dao.common;

import org.bsuir.connection.ConnectionPool;
import org.bsuir.dao.helper.DaoManager;

public class DaoFactory {

   public static DaoManager createDaoManager(){
        return new DaoManager(ConnectionPool.getInstance());
    }

   public static DaoManager createRestDaoManager(){
       return new DaoManager();
   }
}
