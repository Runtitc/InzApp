package database.objectDetails;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by runtitc on 11/8/16.
 */
public class snortLog {
    private SimpleIntegerProperty mainWindowColumnCid;
    private SimpleStringProperty mainWindowColumnSig;
    private SimpleStringProperty mainWindowColumnSrcAddr;
    private SimpleStringProperty mainWindowColumnDestAddr;
    private SimpleStringProperty mainWindowColumnProt;
    private SimpleStringProperty mainWindowColumnTime;

    private SimpleStringProperty mainWindowColumnMsgp;

    public snortLog() {

    }


    public snortLog(
            Integer cid,
            String sigName,
            String srcAddr,
            String destAddr,
            String ipProto,
            String timestamp) {


        this.mainWindowColumnCid = new SimpleIntegerProperty(cid);
        this.mainWindowColumnSig = new SimpleStringProperty(sigName);
        this.mainWindowColumnSrcAddr = new SimpleStringProperty(srcAddr);
        this.mainWindowColumnDestAddr = new SimpleStringProperty(destAddr);
        this.mainWindowColumnProt = new SimpleStringProperty(ipProto);
        this.mainWindowColumnTime = new SimpleStringProperty(timestamp);
    }

    public int getMainWindowColumnCid() {
        return mainWindowColumnCid.get();
    }

    public SimpleIntegerProperty mainWindowColumnCidProperty() {
        return mainWindowColumnCid;
    }

    public void setMainWindowColumnCid(int mainWindowColumnCid) {
        this.mainWindowColumnCid.set(mainWindowColumnCid);
    }

    public String getMainWindowColumnTime() {
        return mainWindowColumnTime.get();
    }

    public void setMainWindowColumnTime(String mainWindowColumnTime) {
        this.mainWindowColumnTime.set(mainWindowColumnTime);
    }

    public String getMainWindowColumnSig() {
        return mainWindowColumnSig.get();
    }

    public SimpleStringProperty mainWindowColumnSigProperty() {
        return mainWindowColumnSig;
    }

    public void setMainWindowColumnSig(String mainWindowColumnSig) {
        this.mainWindowColumnSig.set(mainWindowColumnSig);
    }

    public String getMainWindowColumnSrcAddr() {
        return mainWindowColumnSrcAddr.get();
    }

    public SimpleStringProperty mainWindowColumnSrcAddrProperty() {
        return mainWindowColumnSrcAddr;
    }

    public void setMainWindowColumnSrcAddr(String mainWindowColumnSrcAddr) {
        this.mainWindowColumnSrcAddr.set(mainWindowColumnSrcAddr);
    }

    public String getMainWindowColumnDestAddr() {
        return mainWindowColumnDestAddr.get();
    }

    public SimpleStringProperty mainWindowColumnDestAddrProperty() {
        return mainWindowColumnDestAddr;
    }

    public void setMainWindowColumnDestAddr(String mainWindowColumnDestAddr) {
        this.mainWindowColumnDestAddr.set(mainWindowColumnDestAddr);
    }

    public String getMainWindowColumnProt() {
        return mainWindowColumnProt.get();
    }

    public SimpleStringProperty mainWindowColumnProtProperty() {
        return mainWindowColumnProt;
    }

    public void setMainWindowColumnProt(String mainWindowColumnProt) {
        this.mainWindowColumnProt.set(mainWindowColumnProt);
    }

    public String getMainWindowColumnTimestamp() {
        return mainWindowColumnTime.get();
    }

    public SimpleStringProperty mainWindowColumnTimeProperty() {
        return mainWindowColumnTime;
    }

    public void setMainWindowColumnTimestamp(String mainWindowColumnTime) {
        this.mainWindowColumnTime.set(mainWindowColumnTime);
    }

    public String getMainWindowColumnMsgp() {
        return mainWindowColumnMsgp.get();
    }

    public SimpleStringProperty mainWindowColumnMsgpProperty() {
        return mainWindowColumnMsgp;
    }

    public void setMainWindowColumnMsgp(String mainWindowColumnMsgp) {
        this.mainWindowColumnMsgp.set(mainWindowColumnMsgp);
    }
}
