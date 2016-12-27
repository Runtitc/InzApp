package database.user;

/**
 * Created by runtitc on 12/25/16.
 */
public class SnortLogICMPDetails {

    //ICMP
    private String icmp_type;
    private Integer icmp_code;
    private Integer icmp_csum;
    private Integer icmp_id;
    private Integer icmp_seq;

    public SnortLogICMPDetails(){}

    public SnortLogICMPDetails(
            String icmp_type,
            Integer icmp_code,
            Integer icmp_csum,
            Integer icmp_id,
            Integer icmp_seq
    ){
        this.icmp_type = icmp_type;
        this.icmp_code = icmp_code;
        this.icmp_csum = icmp_csum;
        this.icmp_id = icmp_id;
        this.icmp_seq = icmp_seq;
    }


    public String getIcmp_type() {
        return icmp_type;
    }

    public Integer getIcmp_code() {
        return icmp_code;
    }

    public Integer getIcmp_csum() {
        return icmp_csum;
    }

    public Integer getIcmp_id() {
        return icmp_id;
    }

    public Integer getIcmp_seq() {
        return icmp_seq;
    }
}
