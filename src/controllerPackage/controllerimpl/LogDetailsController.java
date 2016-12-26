package controllerPackage.controllerimpl;

import app.AlertBox;
import app.DialogPopUp;
import controllerPackage.controller.Controller;
import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.user.SnortLogIpDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LogDetailsController extends Controller{

    public Integer cid;
    public String proto;

    @FXML private Text ipTtlId;
    @FXML private Text ipOffId;
    @FXML private Text ipFlagId;
    @FXML private Text ipHeaderLengthId;
    @FXML private Text IpProtocol;
    @FXML private Text ipCheckSumId;
    @FXML private Text ipTosId;
    @FXML private Text ipSeqNumbId;
    @FXML private Text ipLengthId;
    @FXML private Text ipVersionId;
    @FXML private Text ipSrcId;
    @FXML private Text ipDestId;
    @FXML private TextArea ipPayloadId;
    @FXML private TextArea ipPayloadAsciiId;
    @FXML private Button showUpperLayerHeaderButtonId;

    public void initialize(){
        CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());
    }

    public void onEventOccured(ActionEvent event) throws IOException {
        super.onEventOccured(event);

        if (id.equals(showUpperLayerHeaderButtonId.getId().toString())) {

            if (proto.equals("TCP")){
                FXMLLoader aloader = new FXMLLoader(getClass().getResource("../../view/logTCPDetails.fxml"));
                Parent root = aloader.load();
                Stage stage = new Stage();
                stage.setTitle("Protokół TCP warstwy transportowej pakietu o cid: "+ cid);
                LogTCPDetailsController controller = aloader.getController();
                controller.setTCPDetailsByCid(cid);
                stage.setScene(new Scene(root));
                stage.show();
            }else if (proto.equals("ICMP")) {
                FXMLLoader aloader = new FXMLLoader(getClass().getResource("../../view/logICMPDetails.fxml"));
                Parent root = aloader.load();
                Stage stage = new Stage();
                stage.setTitle("Protokół ICMP warstwy transportowej pakietu o cid: "+ this.cid);
                LogICMPDetailsController controller = aloader.getController();
                controller.setICMPDetailsByCid(this.cid);
                stage.setScene(new Scene(root));
                stage.show();
            }else if (proto.equals("UDP")){
                System.out.println("UDP"+proto);
                FXMLLoader aloader = new FXMLLoader(getClass().getResource("../../view/logUDPDetails.fxml"));
                Parent root = aloader.load();
                Stage stage = new Stage();
                stage.setTitle("Protokół UDP warstwy transportowej pakietu o cid: "+ this.cid);
                LogUDPDetailsController controller = aloader.getController();
                controller.setUDPDetailsByCid(cid);
                stage.setScene(new Scene(root));
                stage.show();
            }else{
                new AlertBox("emptyProtocol", "Brak informacji o protokole "+proto+ "!");
            }
        }
    }

    public void setIpDetailsByCidAndProto(Integer cid, String protocol){
        this.cid = cid;
        this.proto = protocol;

        SnortLogIpDetails snortLogListSpecification;
        SnortLogDaoImpl details = new SnortLogDaoImpl();
        snortLogListSpecification = details.SelectLogIpSpecification(cid, protocol);

        ipTtlId.setText(snortLogListSpecification.getIpTtlId().toString());
        ipOffId.setText(snortLogListSpecification.getIpOffId().toString());
        ipFlagId.setText(snortLogListSpecification.getIpFlagId().toString());
        ipHeaderLengthId.setText(snortLogListSpecification.getIpHeaderLengthId().toString());
        IpProtocol.setText(snortLogListSpecification.getIpProtocol().toString());
        ipCheckSumId.setText(snortLogListSpecification.getIpCheckSumId().toString());
        ipTosId.setText(snortLogListSpecification.getIpTosId().toString());
        ipSeqNumbId.setText(snortLogListSpecification.getIpSeqNumbId().toString());
        ipLengthId.setText(snortLogListSpecification.getIpLengthId().toString());
        ipVersionId.setText(snortLogListSpecification.getIpVersionId().toString());
        ipSrcId.setText(snortLogListSpecification.getIpSrcId().toString());
        ipDestId.setText(snortLogListSpecification.getIpDestId().toString());
        ipPayloadId.setText(snortLogListSpecification.getIpPayloadId().toString());
        ipPayloadAsciiId.setText((snortLogListSpecification.getIpPayloardAsciiId()));

        if (protocol.equals("TCP") || protocol.equals("ICMP") || protocol.equals("UDP")){
            showUpperLayerHeaderButtonId.setDisable(false);
        }else{
            showUpperLayerHeaderButtonId.setDisable(true);
        }
    }
}
