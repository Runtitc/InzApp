package database.daoimpl;

import app.LoginServerDialog;
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
import java.util.Objects;

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
            conn = CreateConnection.getConn(LoginServerDialog.getServerAddr(),LoginServerDialog.getDatabasePass());
            // Without the account selection....
            q = conn.prepareStatement("" +
                    "SELECT " +
                        "iphdr.cid, " +
                        "sig_name, " +
                        "inet_ntoa(ip_src) AS ip_src, " +
                        "inet_ntoa(ip_dst) AS ip_dst, " +
                        "ip_proto, " +
                        "timestamp " +
                    "FROM signature, event, iphdr " +
                    "WHERE " +
                        "iphdr.cid=event.cid AND " +
                        "event.signature=signature.sig_id");// WHERE idUser = jakis tam user

            resultSet = q.executeQuery();

            while(resultSet.next()){
                Integer cidI = resultSet.getInt("cid");
                //String cid = cidI.toString();
                String sig_name = resultSet.getString("sig_name");
                String ip_src = resultSet.getString("ip_src");
                String ip_dst = resultSet.getString("ip_dst");
                String ip_proto = convertToName(resultSet.getString("ip_proto"));
                String timestamp = resultSet.getString("timestamp");

                snortLog selectedLog = new snortLog(cidI, sig_name, ip_src, ip_dst, ip_proto, timestamp);
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

    private String convertToName(String ip_proto) {
        if (Objects.equals(ip_proto, "1")) return "ICMP";
        if (Objects.equals(ip_proto, "6")) return "TCP";
        if (Objects.equals(ip_proto, "17")) return "UDP";
        if (Objects.equals(ip_proto, "46")) return "RSVP";
        if (Objects.equals(ip_proto, "58")) return "ICMPv6";
        if (Objects.equals(ip_proto, "88")) return "EIGRP";
        if (Objects.equals(ip_proto, "89")) return "OSPF";
        if (Objects.equals(ip_proto, "132")) return "SCTP";
        return ip_proto;
    }
}
