package controllerPackage.controllerimpl;

import app.DialogPopUp;
import controllerPackage.controller.Controller;
import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.user.SnortLogTCPDetails;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LogTransportLayerDetailsController extends Controller{

    public Integer cid;
    public String proto;
    private SnortLogTCPDetails snortLogTCPListSpecification;

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
        System.out.println("LogTransportLayerDetailsController: "+cid);

        SnortLogDaoImpl details = new SnortLogDaoImpl();
        snortLogTCPListSpecification = details.SelectLogTCPSpecification(cid);

        System.out.printf(snortLogTCPListSpecification.getTcpCheckSum().toString());
        sPort.setText(snortLogTCPListSpecification.getTcpSport().toString());
        dPort.setText(snortLogTCPListSpecification.getTcpDport().toString());
        seq.setText(snortLogTCPListSpecification.getTcpSeq().toString());
        ack.setText(snortLogTCPListSpecification.getTcpAck().toString());
        offset.setText(snortLogTCPListSpecification.getTcpOff().toString());
        reserved.setText(snortLogTCPListSpecification.getTcpRes().toString());
        flags.setText(snortLogTCPListSpecification.getTcpFlags().toString());
        window.setText(snortLogTCPListSpecification.getTcpWin().toString());
        checksum.setText(snortLogTCPListSpecification.getTcpCheckSum().toString());
        urgentPointer.setText(snortLogTCPListSpecification.getTcpUrp().toString());

    }
}
