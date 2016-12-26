package database.daoimpl;

import app.DialogPopUp;
import database.config.CreateConnection;
import database.dao.SnortLogDao;
import database.user.*;
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

    //private ObservableList<snortLog> snortLogsList;
    //private ObservableList<SnortLogIpDetails> snortLogListSpecification;

    @Override
    public ObservableList<snortLog> selectLogs() {
        ObservableList<snortLog> snortLogsList = FXCollections.observableArrayList();
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

    public SnortLogIpDetails SelectLogIpSpecification(Integer selectedCid, String protocol){
        SnortLogIpDetails selectedIpHeader = null;
        Connection conn = null;
        PreparedStatement q = null;
        ResultSet resultSet = null;
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
        try {
            conn = CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());
            // Without the account selection....

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
                            "iphdr.cid= ?" );
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
                    selectedIpHeader = new SnortLogIpDetails(
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
                q.setString(1, selectedCid.toString());
                resultSet = q.executeQuery();

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
                selectedIpHeader = new SnortLogIpDetails(
                        ipTtlId, ipOffId, ipFlagId, ipHeaderLengthId, ipProtocol, ipCheckSumId, ipTosId, ipSeqNumbId, ipLengthId,
                        ipVersionId, ipSrcId, ipDestId, "");
            }
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
        return selectedIpHeader;

    }

    public SnortLogTCPDetails SelectLogTCPSpecification(Integer selectedCid) {
        SnortLogTCPDetails selectedTcpHeader = null;
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

        try {
            conn = CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());
            q = conn.prepareStatement("" +
                    "select " +
                        "tcp_sport, " +
                        "tcp_dport, " +
                        "tcp_seq, " +
                        "tcp_ack, " +
                        "tcp_off, " +
                        "tcp_res, " +
                        "tcp_flags, " +
                        "tcp_win, " +
                        "tcp_csum, " +
                        "tcp_urp " +
                    "FROM tcphdr where cid= ?;");
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
            selectedTcpHeader = new SnortLogTCPDetails(tcp_sport, tcp_dport, tcp_seq, tcp_ack, tcp_off, tcp_res, tcp_flags,
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
        return selectedTcpHeader;
    }

    public SnortLogUDPDetails SelectLogUDPSpecification(Integer selectedCid) {
        SnortLogUDPDetails selectedUdpHeader =null;
        Connection conn = null;
        PreparedStatement q = null;
        ResultSet resultSet = null;

        Integer udp_sport = null;
        Integer udp_dport = null;
        Integer udp_len = null;
        Integer udp_csum = null;

        try {
            conn = CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());
            System.out.println("selectedCid: " + selectedCid.toString());
            q = conn.prepareStatement("" +
                    "select " +
                    "udp_sport, " +
                    "udp_dport, " +
                    "udp_len, " +
                    "udp_csum " +
                    "FROM udphdr where cid= ?;");
            q.setString(1, selectedCid.toString());
            resultSet = q.executeQuery();

            while (resultSet.next()) {
                udp_sport = resultSet.getInt("udp_sport");
                udp_dport = resultSet.getInt("udp_dport");
                udp_len = resultSet.getInt("udp_len");
                udp_csum = resultSet.getInt("udp_csum");
            }
            selectedUdpHeader = new SnortLogUDPDetails(udp_sport, udp_dport, udp_len, udp_csum);

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
        //System.out.println(selectedUdpHeader.getUdp_csum());
        return selectedUdpHeader;
    }

    public SnortLogICMPDetails SelectLogIcmpSpecification(Integer selectedCid) {
        SnortLogICMPDetails selectedIcmpHeader = null;
        Connection conn = null;
        PreparedStatement q = null;
        ResultSet resultSet = null;

        Integer icmp_type = null;
        Integer icmp_code = null;
        Integer icmp_csum = null;
        Integer icmp_id = null;
        Integer icmp_seq = null;

        try {
            conn = CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());

            q = conn.prepareStatement("" +
                    "select " +
                    "icmp_type, " +
                    "icmp_code, " +
                    "icmp_csum, " +
                    "icmp_id, " +
                    "icmp_seq " +
                    "FROM icmphdr where cid= ?;");
            q.setString(1, selectedCid.toString());
            resultSet = q.executeQuery();

            while (resultSet.next()) {
                icmp_type = resultSet.getInt("icmp_type");
                icmp_code = resultSet.getInt("icmp_code");
                icmp_csum = resultSet.getInt("icmp_csum");
                icmp_id = resultSet.getInt("icmp_id");
                icmp_seq = resultSet.getInt("icmp_seq");
            }
            selectedIcmpHeader = new SnortLogICMPDetails(icmp_type, icmp_code, icmp_csum, icmp_id, icmp_seq);

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

        return selectedIcmpHeader;
    }
    private String convertToName(String ip_proto) {
        if (Objects.equals(ip_proto, "1")) return "ICMP";
        if (Objects.equals(ip_proto, "6")) return "TCP";
        if (Objects.equals(ip_proto, "17")) return "UDP";
        return ip_proto;
    }
}
