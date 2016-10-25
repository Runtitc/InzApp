package database.daoimpl;

import database.config.CreateConnection;
import database.dao.UserDao;
import database.user.User;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by runtitc on 10/25/16.
 */
public class UserDaoImpl implements UserDao{

    @Override
    public void createUser(User user) {
        Connection conn = null;
        Statement q = null;

        try{
            conn = CreateConnection.getConn();
            q = conn.createStatement();
            q.execute("");
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
    }

    @Override
    public User selectByID(int id) {
        return null;
    }
}
