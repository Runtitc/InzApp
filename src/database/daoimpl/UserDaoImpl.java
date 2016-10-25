package database.daoimpl;

import database.config.CreateConnection;
import database.dao.UserDao;
import database.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by runtitc on 10/25/16.
 */
public class UserDaoImpl implements UserDao{

    @Override
    public void createUser(User user) {
        Connection conn = null;
        PreparedStatement q = null;

        try{
            conn = CreateConnection.getConn();
            q = conn.prepareStatement("INSERT INTO users (Username, Password) values (?, ?);");
            q.setString(1, user.getUsername());
            q.setString(2, user.getPassword());
            q.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (null != q){
                try{
                    q.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn){
                try{
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public User selectByID(int id) {
        return null;
    }
}
