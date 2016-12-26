package controllerPackage.controllerimpl;

import app.DialogPopUp;
import controllerPackage.controller.Controller;
import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.user.SnortLogUDPDetails;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LogUDPDetailsController extends Controller{

    public Integer cid;

    private SnortLogUDPDetails snortLogUDPListSpecification;

    @FXML private Text udp_sport;
    @FXML private Text udp_dport;
    @FXML private Text udp_len;
    @FXML private Text udp_csum;
    public void initialize(){
        CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());

    }

    public void setUDPDetailsByCid(Integer cid){
        this.cid = cid;
        SnortLogDaoImpl details = new SnortLogDaoImpl();

        System.out.println("setUDPDetailsByCid: "+cid);
        snortLogUDPListSpecification = details.SelectLogUDPSpecification(cid);

        udp_sport.setText(snortLogUDPListSpecification.getUdp_sport().toString());
        udp_dport.setText(snortLogUDPListSpecification.getUdp_dport().toString());
        udp_len.setText(snortLogUDPListSpecification.getUdp_len().toString());
        udp_csum.setText(snortLogUDPListSpecification.getUdp_csum().toString());
    }
}
