package database.daoimpl;

import database.config.CreateConnection;
import database.dao.SnortLogDao;
import database.user.User;
import database.user.snortLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by runtitc on 11/15/16.
 */
public class SnortLogDaoImpl implements SnortLogDao{

    private ObservableList<snortLog> snortLogsList;

    @Override
    public ObservableList<snortLog> selectLogsByUsername(User user) {
        snortLogsList = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement q = null;
        ResultSet resultSet = null;

        try {
            conn = CreateConnection.getConn();
            // Without the account selection....
            q = conn.prepareStatement("SELECT iphdr.cid, inet_ntoa(ip_src) AS ip_src, inet_ntoa(ip_dst) AS ip_dst, timestamp FROM iphdr, event where iphdr.cid=event.cid;");// WHERE idUser = jakis tam user

            resultSet = q.executeQuery();

            while(resultSet.next()){
                Integer cidI = resultSet.getInt("cid");
                String cid = cidI.toString();
                String ip_src = resultSet.getString("ip_src");
                String ip_dst = resultSet.getString("ip_dst");
                String timestamp = resultSet.getString("timestamp");

                snortLog selectedLog = new snortLog(cid, ip_src, ip_dst, timestamp);
                snortLogsList.add(selectedLog);
            }
            System.out.println(snortLogsList.getClass());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if ( null != resultSet ){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if ( null != q ){
                try {
                    q.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if ( null != conn){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return snortLogsList;
    }
}
