package database.dao;

import database.objectDetails.User;

/**
 * Created by runtitc on 10/25/16.
 */
public interface UserDao {
    void createUser(User user);
    User selectByUsername(String username);
}
