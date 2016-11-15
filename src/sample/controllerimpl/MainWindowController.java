package sample.controllerimpl;

import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.user.User;
import database.user.snortLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by runtitc on 11/8/16.
 */
public class MainWindowController{

    User temporaryUser = new User("TestowyUzytkownik", "Testowehaslo");

    @FXML TableView<snortLog> mainWindowTable;

    @FXML private TableColumn mainWindowColumnCid;
    @FXML private TableColumn mainWindowColumnSrcAddr;
    @FXML private TableColumn mainWindowColumnDestAddr;
    @FXML private TableColumn mainWindowColumnTimestamp;
    private ObservableList<snortLog> snortLogs = FXCollections.observableArrayList();

    @FXML private MenuItem buttonProgramTermination;
    @FXML private MenuItem buttonProgramTermination1; //IMPORTANT

    public void initialize(){
        System.out.println("jestem w mainWindow");

        CreateConnection.getConn();

        SnortLogDaoImpl Logs = new SnortLogDaoImpl();
        snortLogs = Logs.selectLogsByUsername(temporaryUser);
        System.out.println(snortLogs);

        mainWindowColumnCid.setCellValueFactory(new PropertyValueFactory<snortLog, String>("cid"));
        mainWindowColumnSrcAddr.setCellValueFactory(new PropertyValueFactory<snortLog, String>("ip_src"));
        mainWindowColumnDestAddr.setCellValueFactory(new PropertyValueFactory<snortLog, String>("ip_dst"));
        mainWindowColumnTimestamp.setCellValueFactory(new PropertyValueFactory<snortLog, String>("timestamp"));
        mainWindowTable.getItems().setAll(this.snortLogs);

        //refreshLogs();
    }

    private void refreshLogs(){
        SnortLogDaoImpl Logs = new SnortLogDaoImpl();
        snortLogs = Logs.selectLogsByUsername(temporaryUser);
        mainWindowTable.getItems().setAll(this.snortLogs);

    }
    public void onEventOccured(ActionEvent event){

        if (event.getSource() instanceof Button) {
            //Button

            Button button = (Button) event.getSource();
            String buttonId = button.getId();


        }else if(event.getSource() instanceof MenuItem){
            //Menu Item

            MenuItem menuItem = (MenuItem) event.getSource();
            String menuItemId = menuItem.getId();

            if(menuItemId.equals("buttonProgramTermination")){
                System.out.println("Do widzenia!");
                System.exit(0);
            }
        }
        System.out.println(event.getSource());
    }

}