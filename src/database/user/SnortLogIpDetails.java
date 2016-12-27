package database.user;

/**
 * Created by runtitc on 12/25/16.
 */
public class SnortLogIpDetails {

    //IP
    private Integer ipTtlId;
    private Integer ipOffId;
    private Integer ipFlagId;
    private Integer ipHeaderLengthId;
    private String ipProtocol;
    private Integer ipCheckSumId;
    private Integer ipTosId;
    private Integer ipSeqNumbId;
    private Integer ipLengthId;
    private Integer ipVersionId;
    private String ipSrcId;
    private String ipDestId;
    private String ipPayloadId;
    private String ipPayloardAsciiId;


    public SnortLogIpDetails(){}

    public SnortLogIpDetails(
            Integer ipTtlId,
            Integer ipOffId,
            Integer ipFlagId,
            Integer ipHeaderLengthId,
            String ipProtocol,
            Integer ipCheckSumId,
            Integer ipTosId,
            Integer ipSeqNumbId,
            Integer ipLengthId,
            Integer ipVersionId,
            String ipSrcId,
            String ipDestId,
            String ipPayloadId
    ){
        this.ipTtlId = ipTtlId;
        this.ipOffId = ipOffId;
        this.ipFlagId= ipFlagId;
        this.ipHeaderLengthId = ipHeaderLengthId;
        this.ipProtocol = ipProtocol;
        this.ipCheckSumId = ipCheckSumId;
        this.ipTosId = ipTosId;
        this.ipSeqNumbId = ipSeqNumbId;
        this.ipLengthId = ipLengthId;
        this.ipVersionId = ipVersionId;
        this.ipSrcId = ipSrcId;
        this.ipDestId = ipDestId;
        this.ipPayloadId = ipPayloadId;
        if (ipPayloadId.equals("Pusty Payload")) {
            this.ipPayloardAsciiId = "Pusty Payload";
        }else if (!ipPayloadId.isEmpty()){
            this.ipPayloardAsciiId = hexToAscii(ipPayloadId);
        }
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
