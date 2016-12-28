package database.objectDetails;

/**
 * Created by runtitc on 12/25/16.
 */
public class SnortLogTCPDetails {

    //TCP
    private Integer tcpSport;
    private Integer tcpDport;
    private String tcpSeq;
    private String tcpAck;
    private Integer tcpOff;
    private Integer tcpRes;
    private Integer tcpFlags;
    private Integer tcpWin;
    private Integer tcpCheckSum;
    private Integer tcpUrp;

    public SnortLogTCPDetails(){}

    public SnortLogTCPDetails(
            Integer tcpSport,
            Integer tcpDport,
            String tcpSeq,
            String tcpAck,
            Integer tcpOff,
            Integer tcpRes,
            Integer tcpFlags,
            Integer tcpWin,
            Integer tcpCheckSum,
            Integer tcpUrp
    ){
        this.tcpSport = tcpSport;
        this.tcpDport = tcpDport;
        this.tcpSeq = tcpSeq;
        this.tcpAck = tcpAck;
        this.tcpOff = tcpOff;
        this.tcpRes = tcpRes;
        this.tcpFlags = tcpFlags;
        this.tcpWin = tcpWin;
        this.tcpCheckSum = tcpCheckSum;
        this.tcpUrp = tcpUrp;
    }

    public Integer getTcpSport() {
        return tcpSport;
    }

    public Integer getTcpDport() {
        return tcpDport;
    }

    public String getTcpSeq() {
        return tcpSeq;
    }

    public String getTcpAck() {
        return tcpAck;
    }

    public Integer getTcpOff() {
        return tcpOff;
    }

    public Integer getTcpRes() {
        return tcpRes;
    }

    public Integer getTcpFlags() {
        return tcpFlags;
    }

    public Integer getTcpWin() {
        return tcpWin;
    }

    public Integer getTcpCheckSum() {
        return tcpCheckSum;
    }

    public Integer getTcpUrp() {
        return tcpUrp;
    }
}
