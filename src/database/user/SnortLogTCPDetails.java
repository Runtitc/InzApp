package database.user;

/**
 * Created by runtitc on 12/25/16.
 */
public class SnortLogTCPDetails {

    //TCP
    private Integer tcpSport;
    private Integer tcpDport;
    private Integer tcpSeq;
    private Integer tcpAck;
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
            Integer tcpSeq,
            Integer tcpAck,
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

    private String hexToAscii(String ipPayloadId) {
        StringBuilder asciiOutput = new StringBuilder();
        for (int i = 0; i < ipPayloadId.length(); i+=2) {
            String str = ipPayloadId.substring(i, i+2);
            asciiOutput.append((char)Integer.parseInt(str, 16));
        }
        return asciiOutput.toString();
    }

    public Integer getIpTtlId() {
        return ipTtlId;
    }

    public Integer getIpOffId() {
        return ipOffId;
    }

    public Integer getIpFlagId() {
        return ipFlagId;
    }

    public Integer getIpHeaderLengthId() {
        return ipHeaderLengthId;
    }

    public String getIpProtocol() {
        return ipProtocol;
    }

    public Integer getIpCheckSumId() {
        return ipCheckSumId;
    }

    public Integer getIpTosId() {
        return ipTosId;
    }

    public Integer getIpSeqNumbId() {
        return ipSeqNumbId;
    }

    public Integer getIpLengthId() {
        return ipLengthId;
    }

    public Integer getIpVersionId() {
        return ipVersionId;
    }

    public String getIpSrcId() {
        return ipSrcId;
    }

    public String getIpDestId() {
        return ipDestId;
    }

    public String getIpPayloadId() {
        return ipPayloadId;
    }

    public String getIpPayloardAsciiId() {
        return ipPayloardAsciiId;
    }
}
