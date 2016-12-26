package database.user;

/**
 * Created by runtitc on 12/25/16.
 */
public class SnortLogIpDetails {

    private Integer ipTtlId;
    private Integer ipOffId;
    private Integer ipFlagId;
    private Integer ipHeaderLengthId;
    private Integer ipProtocol;
    private Integer ipCheckSumId;
    private Integer ipTosId;
    private Integer ipSeqNumbId;
    private Integer ipLengthId;
    private Integer ipVersionId;
    private String ipSrcId;
    private String ipDestId;
    private String ipPayloadId;

    public SnortLogIpDetails(){}

    public SnortLogIpDetails(
            Integer ipTtlId,
            Integer ipOffId,
            Integer ipFlagId,
            Integer ipHeaderLengthId,
            Integer ipProtocol,
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

    public Integer getIpProtocol() {
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
}