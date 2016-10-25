package database.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by runtitc on 10/24/16.
 */
public class CreateConnection {
    public static Connection getConn(){
        Connection newConnection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //get the instance of the class as calling new com.mysql.jdbc.Driver

            //Making connection
            newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "gabrysia");
        }catch (Exception e){
            e.printStackTrace();
        }

        return newConnection;
    }
}
