package database.user;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by runtitc on 11/8/16.
 */
public class snortLog {
    private SimpleStringProperty mainWindowColumnCid;
    private SimpleStringProperty mainWindowColumnSrcAddr;
    private SimpleStringProperty mainWindowColumnDestAddr;
    private SimpleStringProperty mainWindowColumnTimestamp;

    private SimpleStringProperty mainWindowColumnMsgp;

    public snortLog() {

    }

    public snortLog(
            String cid,
            String srcAddr,
            String destAddr,
            String timestamp) {


        this.mainWindowColumnCid = new SimpleStringProperty(cid);
        this.mainWindowColumnSrcAddr = new SimpleStringProperty(srcAddr);
        this.mainWindowColumnDestAddr = new SimpleStringProperty(destAddr);
        this.mainWindowColumnTimestamp = new SimpleStringProperty(timestamp);
    }

    public String getMainWindowColumnCid() {
        return mainWindowColumnCid.get();
    }

    public SimpleStringProperty mainWindowColumnCidProperty() {
        return mainWindowColumnCid;
    }

    public void setMainWindowColumnCid(String mainWindowColumnCid) {
        this.mainWindowColumnCid.set(mainWindowColumnCid);
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

    public String getMainWindowColumnTimestamp() {
        return mainWindowColumnTimestamp.get();
    }

    public SimpleStringProperty mainWindowColumnTimestampProperty() {
        return mainWindowColumnTimestamp;
    }

    public void setMainWindowColumnTimestamp(String mainWindowColumnTimestamp) {
        this.mainWindowColumnTimestamp.set(mainWindowColumnTimestamp);
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
