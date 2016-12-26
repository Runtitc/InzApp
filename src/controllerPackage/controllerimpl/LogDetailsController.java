package controllerPackage.controllerimpl;

import app.DialogPopUp;
import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.user.SnortLogIpDetails;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class LogDetailsController {

    public Integer cid;
    private ObservableList<SnortLogIpDetails> snortLogListSpecification;

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

    public void initialize(){
        CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());

        //SnortLogDaoImpl SpecificLog = new SnortLogDaoImpl();

        //ipProtocolVersion.setText("Ipv4");
    }

    public void setCid(Integer cid){
        this.cid = cid;
        System.out.println(cid);

        SnortLogDaoImpl details = new SnortLogDaoImpl();
        snortLogListSpecification = details.SelectLogIpSpecification(cid);

        ipTtlId.setText(snortLogListSpecification.get(0).getIpTtlId().toString());
        ipOffId.setText(snortLogListSpecification.get(0).getIpOffId().toString());
        ipFlagId.setText(snortLogListSpecification.get(0).getIpFlagId().toString());
        ipHeaderLengthId.setText(snortLogListSpecification.get(0).getIpHeaderLengthId().toString());
        IpProtocol.setText(snortLogListSpecification.get(0).getIpProtocol().toString());
        ipCheckSumId.setText(snortLogListSpecification.get(0).getIpCheckSumId().toString());
        ipTosId.setText(snortLogListSpecification.get(0).getIpTosId().toString());
        ipSeqNumbId.setText(snortLogListSpecification.get(0).getIpSeqNumbId().toString());
        ipLengthId.setText(snortLogListSpecification.get(0).getIpLengthId().toString());
        ipVersionId.setText(snortLogListSpecification.get(0).getIpVersionId().toString());
        ipSrcId.setText(snortLogListSpecification.get(0).getIpSrcId().toString());
        ipDestId.setText(snortLogListSpecification.get(0).getIpDestId().toString());
        ipPayloadId.setText(snortLogListSpecification.get(0).getIpPayloadId().toString());

    }
}
