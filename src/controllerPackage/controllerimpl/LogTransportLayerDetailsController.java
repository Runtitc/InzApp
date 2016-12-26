package controllerPackage.controllerimpl;

import app.DialogPopUp;
import controllerPackage.controller.Controller;
import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.user.SnortLogTCPDetails;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LogTransportLayerDetailsController extends Controller{

    public Integer cid;
    public String proto;
    private ObservableList<SnortLogTCPDetails> snortLogTCPListSpecification;

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
/*
        sPort.setText(snortLogTCPListSpecification.get(0).getTcpSport().toString());
        dPort.setText(snortLogTCPListSpecification.get(0).getTcpDport().toString());
        seq.setText(snortLogTCPListSpecification.get(0).getTcpSeq().toString());
        ack.setText(snortLogTCPListSpecification.get(0).getTcpAck().toString());
        offset.setText(snortLogTCPListSpecification.get(0).getTcpOff().toString());
        reserved.setText(snortLogTCPListSpecification.get(0).getTcpRes().toString());
        flags.setText(snortLogTCPListSpecification.get(0).getTcpFlags().toString());
        window.setText(snortLogTCPListSpecification.get(0).getTcpWin().toString());
        checksum.setText(snortLogTCPListSpecification.get(0).getTcpCheckSum().toString());
        urgentPointer.setText(snortLogTCPListSpecification.get(0).getTcpUrp().toString());
*/
    }
}
