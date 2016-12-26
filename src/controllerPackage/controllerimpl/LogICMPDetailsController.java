package controllerPackage.controllerimpl;

import app.DialogPopUp;
import controllerPackage.controller.Controller;
import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.user.SnortLogICMPDetails;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LogICMPDetailsController extends Controller{

    public Integer cid;
    public String proto;
    private SnortLogICMPDetails snortLogICMPListSpecification;

    @FXML private Text icmp_type;
    @FXML private Text icmp_code;
    @FXML private Text icmp_csum;
    @FXML private Text icmp_id;
    @FXML private Text icmp_seq;

    public void initialize(){
        CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());

        //SnortLogDaoImpl SpecificLog = new SnortLogDaoImpl();

        //ipProtocolVersion.setText("Ipv4");
    }

    public void setTCPDetailsByCid(Integer cid){
        this.cid = cid;
        SnortLogDaoImpl details = new SnortLogDaoImpl();
        snortLogICMPListSpecification = details.SelectLogIcmpSpecification(cid);


        icmp_type.setText(snortLogICMPListSpecification.getIcmp_type().toString());
        icmp_code.setText(snortLogICMPListSpecification.getIcmp_code().toString());
        icmp_csum.setText(snortLogICMPListSpecification.getIcmp_csum().toString());
        icmp_id.setText(snortLogICMPListSpecification.getIcmp_id().toString());
        icmp_seq.setText(snortLogICMPListSpecification.getIcmp_seq().toString());
    }
}
