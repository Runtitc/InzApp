package controllerPackage.controllerimpl;

import app.AlertBox;
import app.Dialog;
import controllerPackage.controller.Controller;
import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.user.snortLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

/**
 * Created by runtitc on 11/8/16.
 */
public class MainWindowController extends Controller{

    @FXML TableView<snortLog> mainWindowTable;

    @FXML private TableColumn<snortLog, String> mainWindowColumnCid;
    @FXML private TableColumn<snortLog, String> mainWindowColumnSig;
    @FXML private TableColumn<snortLog, String> mainWindowColumnSrcAddr;
    @FXML private TableColumn<snortLog, String> mainWindowColumnProt;
    @FXML private TableColumn<snortLog, String> mainWindowColumnDestAddr;
    @FXML private TableColumn<snortLog, String> mainWindowColumnTime;
    private ObservableList<snortLog> snortLogs = FXCollections.observableArrayList();

    @FXML private MenuBar menuBar;

    public void initialize(){
        CreateConnection.getConn(Dialog.getServerAddr(), Dialog.getDatabasePass());

        SnortLogDaoImpl Logs = new SnortLogDaoImpl();
        snortLogs = Logs.selectLogs();

        mainWindowColumnCid.setCellValueFactory(new PropertyValueFactory<>("mainWindowColumnCid"));
        mainWindowColumnSig.setCellValueFactory(new PropertyValueFactory<>("mainWindowColumnSig"));
        mainWindowColumnSrcAddr.setCellValueFactory(new PropertyValueFactory<>("mainWindowColumnSrcAddr"));
        mainWindowColumnDestAddr.setCellValueFactory(new PropertyValueFactory<>("mainWindowColumnDestAddr"));
        mainWindowColumnProt.setCellValueFactory(new PropertyValueFactory<>("mainWindowColumnProt"));
        mainWindowColumnTime.setCellValueFactory(new PropertyValueFactory<>("mainWindowColumnTime"));
        mainWindowTable.getItems().setAll(this.snortLogs);
    }

    public void rowClicked(){
        ObservableList<snortLog> singleRow;
        singleRow = mainWindowTable.getSelectionModel().getSelectedItems();

        if(singleRow.get(0) instanceof snortLog){
            //System.out.println(singleRow.get(0));
            System.out.println("Dupa");
            Dialog serverAddressInput = new Dialog();
            serverAddressInput.createPopUp();


        }
    }

    private void refreshLogs(){
        SnortLogDaoImpl Logs = new SnortLogDaoImpl();
        snortLogs = Logs.selectLogs();
        mainWindowTable.getItems().setAll(this.snortLogs);

    }
    public void onEventOccured(ActionEvent event) throws IOException {

      if(event.getSource() instanceof MenuItem){
            //Menu Item

            MenuItem menuItem = (MenuItem) event.getSource();
            String menuItemId = menuItem.getId();

            if(menuItemId.equals("closeMenuButton")){
                System.out.println("Do widzenia!");
                System.exit(0);
            }
            else if (menuItemId.equals("signinMenuButton")){
                System.out.println("Logowanie");

                menuSwitchScene(menuBar, "../../view/signin.fxml");
            }
            else if (menuItemId.equals("refreshMenuButton")){
                System.out.println("Odswiezanie");
                refreshLogs();
            }
            else  if (menuItemId.equals("aboutMenuButton")){
                AlertBox alert = new AlertBox("about", "");
            }
        }
        //System.out.println(event.getSource());
    }
}
