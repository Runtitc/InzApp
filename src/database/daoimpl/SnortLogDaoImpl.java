package database.daoimpl;

import app.DialogPopUp;
import database.config.CreateConnection;
import database.dao.SnortLogDao;
import database.user.SnortLogIpDetails;
import database.user.SnortLogTCPDetails;
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
    private ObservableList<SnortLogTCPDetails> snortLogTCPListSpecification;

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

    public ObservableList<SnortLogIpDetails> SelectLogIpSpecification(Integer selectedCid, String protocol){
        snortLogListSpecification = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement q = null;
        ResultSet resultSet = null;

        try {
            conn = CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());
            // Without the account selection....
            System.out.println("SnortLogDaoImpl: " + selectedCid);
            SnortLogIpDetails selectedLog;
            Integer ipTtlId =null;
            Integer ipOffId =null;
            Integer ipFlagId = null;
            Integer ipHeaderLengthId = null;
            String ipProtocol  = null;
            Integer ipCheckSumId = null;
            Integer ipTosId = null;
            Integer ipSeqNumbId = null;
            Integer ipLengthId = null;
            Integer ipVersionId = null;
            String ipSrcId = null;
            String ipDestId = null;
            String ipPayloadId = null;
            if (!protocol.equals("TCP")) {
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
                        " iphdr.cid= ?" );
                q.setString(1, selectedCid.toString());
                resultSet = q.executeQuery();

                while(resultSet.next()) {

                    ipTtlId = resultSet.getInt("ip_ttl");
                    ipOffId = resultSet.getInt("ip_off");
                    ipFlagId = resultSet.getInt("ip_flags");
                    ipHeaderLengthId = resultSet.getInt("ip_hlen");
                    ipProtocol = convertToName(resultSet.getString("ip_proto"));
                    ipCheckSumId = resultSet.getInt("ip_csum");
                    ipTosId = resultSet.getInt("ip_tos");
                    ipSeqNumbId = resultSet.getInt("ip_id");
                    ipLengthId = resultSet.getInt("ip_len");
                    ipVersionId = resultSet.getInt("ip_ver");
                    ipSrcId = resultSet.getString("ip_src");
                    ipDestId = resultSet.getString("ip_dst");
                    ipPayloadId = resultSet.getString("data_payload");
                }
                    selectedLog = new SnortLogIpDetails(
                            ipTtlId, ipOffId, ipFlagId, ipHeaderLengthId, ipProtocol, ipCheckSumId, ipTosId, ipSeqNumbId, ipLengthId,
                            ipVersionId, ipSrcId, ipDestId, ipPayloadId);
            }else {
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
                        "inet_ntoa(ip_dst) AS ip_dst " +
                        "FROM iphdr WHERE iphdr.cid= ?" );
                resultSet = q.executeQuery(selectedCid.toString());


                while (resultSet.next()) {

                    ipTtlId = resultSet.getInt("ip_ttl");
                    ipOffId = resultSet.getInt("ip_off");
                    ipFlagId = resultSet.getInt("ip_flags");
                    ipHeaderLengthId = resultSet.getInt("ip_hlen");
                    ipProtocol = convertToName(resultSet.getString("ip_proto"));
                    ipCheckSumId = resultSet.getInt("ip_csum");
                    ipTosId = resultSet.getInt("ip_tos");
                    ipSeqNumbId = resultSet.getInt("ip_id");
                    ipLengthId = resultSet.getInt("ip_len");
                    ipVersionId = resultSet.getInt("ip_ver");
                    ipSrcId = resultSet.getString("ip_src");
                    ipDestId = resultSet.getString("ip_dst");
                }
                selectedLog = new SnortLogIpDetails(
                        ipTtlId, ipOffId, ipFlagId, ipHeaderLengthId, ipProtocol, ipCheckSumId, ipTosId, ipSeqNumbId, ipLengthId,
                        ipVersionId, ipSrcId, ipDestId, "");
            }
            snortLogListSpecification.add(selectedLog);
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

    public ObservableList<SnortLogTCPDetails> SelectLogTCPSpecification(Integer selectedCid) {
        snortLogTCPListSpecification = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement q = null;
        ResultSet resultSet = null;

        Integer tcp_sport = null;
        Integer tcp_dport = null;
        Integer tcp_seq = null;
        Integer tcp_ack = null;
        Integer tcp_off = null;
        Integer tcp_res = null;
        Integer tcp_flags = null;
        Integer tcp_win = null;
        Integer tcp_csum = null;
        Integer tcp_urp = null;
        SnortLogTCPDetails selectTcpHeader  = null;
        try{
            System.out.println("SnorTLOGDAoIMPL: " + selectedCid.toString());

            q = conn.prepareStatement("SELECT " +
                    "  tcp_sport, " +
                    "  tcp_dport, " +
                    "  tcp_seq, " +
                    "  tcp_ack, " +
                    "  tcp_off, " +
                    "  tcp_res, " +
                    "  tcp_flags, " +
                    "  tcp_win, " +
                    "  tcp_csum, " +
                    "  tcp_urp " +
                    " FROM tcphdr " +
                    "WHERE cid= ?;");
            q.setString(1, selectedCid.toString());
            resultSet = q.executeQuery();

            while (resultSet.next()) {
                tcp_sport = resultSet.getInt("tcp_sport");
                tcp_dport = resultSet.getInt("tcp_dport");
                tcp_seq = resultSet.getInt("tcp_seq");
                tcp_ack = resultSet.getInt("tcp_ack");
                tcp_off = resultSet.getInt("tcp_off");
                tcp_res = resultSet.getInt("tcp_res");
                tcp_flags = resultSet.getInt("tcp_flags");
                tcp_win = resultSet.getInt("tcp_win");
                tcp_csum = resultSet.getInt("tcp_csum");
                tcp_urp = resultSet.getInt("tcp_urp");
            }

            selectTcpHeader = new SnortLogTCPDetails(tcp_sport, tcp_dport, tcp_seq, tcp_ack, tcp_off, tcp_res, tcp_flags,
                    tcp_win, tcp_csum, tcp_urp);
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


        return snortLogTCPListSpecification;
    }

    private String convertToName(String ip_proto) {
        if (Objects.equals(ip_proto, "1")) return "ICMP";
        if (Objects.equals(ip_proto, "6")) return "TCP";
        if (Objects.equals(ip_proto, "17")) return "UDP";
        return ip_proto;
    }
}
