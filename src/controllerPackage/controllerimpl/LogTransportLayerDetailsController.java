package controllerPackage.controllerimpl;

import app.DialogPopUp;
import controllerPackage.controller.Controller;
import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.user.SnortLogIpDetails;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LogTransportLayerDetailsController extends Controller{

    public Integer cid;
    public String proto;
    private ObservableList<SnortLogIpDetails> snortLogListSpecification;

    @FXML private Text sPort;
    @FXML private Text dPort;
    @FXML private Text seq;
    @FXML private Text ack;
    @FXML private Text offset;
    @FXML private Text reserved;
    @FXML private Text flags;
    @FXML private Text window;
    @FXML private Text checksum;
    @FXML private Text urgentPointer;

    public void initialize(){
        CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());

        //SnortLogDaoImpl SpecificLog = new SnortLogDaoImpl();

        //ipProtocolVersion.setText("Ipv4");
    }

    public void setTCPDetailsByCid(Integer cid){
        this.cid = cid;
        System.out.println("LogDetailsController: "+cid);

        SnortLogDaoImpl details = new SnortLogDaoImpl();
        snortLogListSpecification = details.SelectLogTCPSpecification(cid);


    }
}
