package database.daoimpl;

import app.DialogPopUp;
import database.config.CreateConnection;
import database.dao.SnortLogDao;
import database.user.SnortLogIpDetails;
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
    private ObservableList<SnortLogIpDetails> snortLogListSpecification;

    @Override
    public ObservableList<snortLog> selectLogs() {
        snortLogsList = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement q = null;
        ResultSet resultSet = null;

        try {
            conn = CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());
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
                        "event.signature=signature.sig_id");// extendable to users

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

    public ObservableList<SnortLogIpDetails> SelectLogIpSpecification(Integer selectedCid){
        snortLogListSpecification = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement q = null;
        ResultSet resultSet = null;

        try {
            conn = CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());
            // Without the account selection....
            System.out.println(selectedCid);
            q = conn.prepareStatement("" +
                    "select " +
                        "ip_ttl, " +
                        "ip_off, " +
                        "ip_flags, " +
                        "ip_hlen, " +
                        "ip_proto, " +
                        "ip_csum, " +
                        "ip_tos, " +
                        "ip_id, " +
                        "ip_len," +
                        "ip_ver, " +
                        "inet_ntoa(ip_src) AS ip_src, " +
                        "inet_ntoa(ip_dst) AS ip_dst, " +
                        "data_payload " +
                    "FROM iphdr, data " +
                    "WHERE " +
                    "iphdr.cid=data.cid AND " +
                    " iphdr.cid="+selectedCid    );
            resultSet = q.executeQuery();

            while(resultSet.next()){

                Integer ipTtlId = resultSet.getInt("ip_ttl");
                Integer ipOffId = resultSet.getInt("ip_off");
                Integer ipFlagId = resultSet.getInt("ip_flags");
                Integer ipHeaderLengthId = resultSet.getInt("ip_hlen");
                Integer IpProtocol= resultSet.getInt("ip_proto");
                Integer ipCheckSumId = resultSet.getInt("ip_csum");
                Integer ipTosId = resultSet.getInt("ip_tos");
                Integer ipSeqNumbId = resultSet.getInt("ip_id");
                Integer ipLengthId = resultSet.getInt("ip_len");
                Integer ipVersionId = resultSet.getInt("ip_ver");
                String ipSrcId = resultSet.getString("ip_src");
                String ipDestId = resultSet.getString("ip_dst");
                String ipPayloadId = resultSet.getString("data_payload");

                SnortLogIpDetails selectedLog = new SnortLogIpDetails(
                        ipTtlId, ipOffId, ipFlagId, ipHeaderLengthId, IpProtocol, ipCheckSumId, ipTosId, ipSeqNumbId, ipLengthId,
                        ipVersionId, ipSrcId, ipDestId, ipPayloadId);
                snortLogListSpecification.add(selectedLog);
            }
            //System.out.println(snortLogSpecificationList.getClass());
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
        return snortLogListSpecification;

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
