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
        }else if (type.equals("registrationErr")){
            registrationError(additionalInformation);
        } else if (type.equals("registrationSucc")){
            registrationSucc(additionalInformation);
        } else if (type.equals(("loginErr"))){
            loginErr(additionalInformation);
        }
    }

    public AlertBox(String type){
        this.typeOfAlert = type;

        if (type.equals("savedWithSuccess")){
            savedSuccessfully();
        } else if(type.equals("about")){
            about();
        }
    }

    private void about() {
        String title = "O programie";
        String headerText = "Snort Intelligent Manager";
        String contentText = "Snort Intelligent Manager 2016\n" +
                "Programista: Przemysław Brożek\n" +
                "Praca Inżynierska AGH 2016/2017\n";

        Alert aboutAlert = createInformationAlert(title, headerText, contentText);
    }

    private void savedSuccessfully() {
        String title = "Powodzenie!";
        String headerText = "Zapis OK";
        String contentText = "Plik zostal zapisany";

        Alert saveSuccessful = createInformationAlert(title, headerText, contentText);
    }


    private void registrationError(String additionalInformation){
        String title = "Blad!";
        String headerText = "Niepoprawne wypelnienie formularza.";
        String contentText = additionalInformation;

        Alert RegErrAlert = createInformationAlert(title, headerText, contentText);
    }

    private void registrationSucc(String additionalInformation){
        String title = "Rejestracja zakonczona";
        String headerText = "Konto zostalo zalozone pomyslnie.";
        String contentText = additionalInformation;

        Alert RegErrAlert = createInformationAlert(title, headerText, contentText);
    }

    private void loginErr(String additionalInformation){
        String title = "Blad!";
        String headerText = "Niepoprawne dane logowania";
        String contentText = additionalInformation;

        Alert logErrAlert = createInformationAlert(title, headerText, contentText);
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