package database.objectDetails;

/**
 * Created by runtitc on 12/25/16.
 */
public class SnortLogUDPDetails {

    //UDP
    private Integer udp_sport;
    private Integer udp_dport;
    private Integer udp_len;
    private Integer udp_csum;

    public SnortLogUDPDetails(){}

    public SnortLogUDPDetails(
            Integer udp_sport,
            Integer udp_dport,
            Integer udp_len,
            Integer udp_csum
    ){
        this.udp_sport = udp_sport;
        this.udp_dport = udp_dport;
        this.udp_len = udp_len;
        this.udp_csum= udp_csum;
    }


    public Integer getUdp_sport() {
        return udp_sport;
    }

    public Integer getUdp_dport() {
        return udp_dport;
    }

    public Integer getUdp_len() {
        return udp_len;
    }

    public Integer getUdp_csum() {
        return udp_csum;
    }
}
