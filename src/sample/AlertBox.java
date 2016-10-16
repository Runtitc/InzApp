package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Przemix on 10/9/16.
 */
public class AlertBox {

    private String typeOfAlert;
    private String additionalInformation;

    public AlertBox(String type, String additionalInformation) {
        this.typeOfAlert = type;
        this.additionalInformation = additionalInformation;

        if (type.equals("noSnortDetected")){
            noSnortDetectedAlert(additionalInformation);
        }
        else if(type.equals("noFileFound")){
            noFileFound(additionalInformation);
        }
    }
    public AlertBox(String type){
        this.typeOfAlert = type;

        if (type.equals("savedWithSuccess")){
            savedSuccessfully();
        }
    }

    private void savedSuccessfully() {
        String title = "Powodzenie!";
        String headerText = "Zapis OK";
        String contentText = "Plik zostal zapisany";

        Alert saveSuccessful = createInformationAlert(title, headerText, contentText);
    }

    private void noFileFound(String additionalInformation) {
        String title = "Blad!";
        String headerText = "Nie znaleziono pliku";
        String contentText = "Plik " + additionalInformation + " jest niedostepny!";

        Alert noSnortAlert = createInformationAlert(title, headerText, contentText);
    }

    private Alert createConfirmationAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        setAlertContent(alert, title, headerText, contentText);

        return alert;
    }
    private Alert createInformationAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //TODO : alert.setContent instead of setContent(alert)
        setAlertContent(alert, title, headerText, contentText);
        alert.showAndWait();

        return alert;
    }
    private void setAlertContent(Alert alert, String title, String headerText, String contentText){
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
    }
    private void noSnortDetectedAlert(String additionalInformation) {
        String title = "Uwaga";
        String headerText = "Nie wykryto zainstalowanego programu Snort";
        String contentText = "Czy zainstalowac program Snort?";

        Alert noSnortAlert = createConfirmationAlert(title, headerText, contentText);

        Optional<ButtonType> result = noSnortAlert.showAndWait();

        if (result.get() == ButtonType.OK) {
            SnortInstaller installer = new SnortInstaller("");
            // check the type of OS
            String typeOfOperatingSystem = System.getProperty("os.name");
            if (typeOfOperatingSystem.equals("Linux")) {
                // install linux
               // installer.installLinux();
            } else if (typeOfOperatingSystem.equals("Windows")) {
                //install on Windows
            }
        }
        else{
            System.exit(0);
        }
    }
}