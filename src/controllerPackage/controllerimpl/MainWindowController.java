package controllerPackage.controllerimpl;

import app.AlertBox;
import app.DialogPopUp;
import controllerPackage.controller.Controller;
import database.config.CreateConnection;
import database.daoimpl.SnortLogDaoImpl;
import database.objectDetails.snortLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
        CreateConnection.getConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass());

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

    public void rowClicked() throws IOException {
        ObservableList<snortLog> singleRow;
        singleRow = mainWindowTable.getSelectionModel().getSelectedItems();
        System.out.println(singleRow.get(0));
        if(singleRow.get(0) != null){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/logdetails.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Szczegóły alarmu, cid:"+ singleRow.get(0).getMainWindowColumnCid());

                LogDetailsController controller = loader.getController();
                //use getters to fetch data from objects
                controller.setIpDetailsByCidAndProto(singleRow.get(0).getMainWindowColumnCid(), singleRow.get(0).getMainWindowColumnProt());

                stage.setScene(new Scene(root));
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
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
                System.exit(0);
            }
            else if (menuItemId.equals("signinMenuButton")){
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
    }
}
