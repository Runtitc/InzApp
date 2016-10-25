package database.user;

/**
 * Created by runtitc on 10/25/16.
 */
public class User {

    //private int id;
    private String username;
    private String password;

    public User(String username, String password){
        setUsername(username);
        setPassword(password);
        encrypt();
        //password should be encrypted/hashed
    }

    private String encrypt(){
        String hashed = getPassword();
        //here algorith to hashing
        return hashed;
    }
    public void CreateUser(int id, String username, String password){

    }

    /*public int getId() {
        return id;
    }*/

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
