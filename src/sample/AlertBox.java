package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Przemix on 10/9/16.
 */
public class AlertBox {
    private String typeOfAlert;

    public AlertBox(String type){
        this.typeOfAlert = type;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Nie wykryto zainstalowanego programu Snort");
        alert.setContentText("Czy zainstalowac program Snort?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            SnortInstaller installer = new SnortInstaller("");
            installer.install();
        } else {
            System.exit(0);
        }
    }

}
