package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Przemix on 10/9/16.
 */
public class AlertBox {
    private String typeOfAlert;
    private boolean  ifCancel;
    public AlertBox(String type) {
        this.typeOfAlert = type;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Nie wykryto zainstalowanego programu Snort");
        alert.setContentText("Czy zainstalowac program Snort?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            SnortInstaller installer = new SnortInstaller("");
            // check the type of OS
            String typeOfOperatingSystem = System.getProperty("os.name");
            if (typeOfOperatingSystem.equals("Linux")) {
                // install linux
                installer.installLinux();
            } else if (typeOfOperatingSystem.equals("Windows")) {
                //install on Windows
            }
        } else{
            System.exit(0);
        }

    }
}