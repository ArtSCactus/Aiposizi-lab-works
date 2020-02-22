import org.bsuir.connection.ConnectionPool;

import java.sql.Connection;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args){
       Connection pool =  ConnectionPool.getInstance().getConnection();
       if (pool==null){
           System.out.println("FUCK!");
       }
    }
}
