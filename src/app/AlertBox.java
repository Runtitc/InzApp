package app;

import javafx.scene.control.Alert;

/**
 * Created by Przemix on 10/9/16.
 */
public class AlertBox {

    private String typeOfAlert;
    private String additionalInformation;

    public AlertBox(String type, String additionalInformation) {
        this.typeOfAlert = type;
        this.additionalInformation = additionalInformation;

        if (type.equals("registrationErr")){
            registrationError(additionalInformation);
        } else if (type.equals("makingConnErr")){
            makingConnErr(additionalInformation);
        } else if (type.equals("registrationSucc")){
            registrationSucc(additionalInformation);
        } else if (type.equals(("loginErr"))){
            loginErr(additionalInformation);
        } else if (type.equals("emptyProtocol")) {
            popUpWindowErr(additionalInformation);
        } else if (type.equals(("about"))){
            about();
        }
    }

    private void makingConnErr(String additionalInformation) {

        String title = "Błąd!";
        String headerText = "Wystąpił problem z połączeniem!";
        String contentText = additionalInformation;

        Alert aboutAlert = createErrAlert(title, headerText, contentText);
    }

    private void popUpWindowErr(String additionalInformation) {

        String title = "Błąd!";
        String headerText = "Otwarcie okna nie powiodlo sie!";
        String contentText = additionalInformation;

        Alert aboutAlert = createErrAlert(title, headerText, contentText);
    }

    private void about() {
        String title = "O programie";
        String headerText = "Snort Intelligent Manager";
        String contentText = "Snort Intelligent Manager 2016\n" +
                "Programista: Przemysław Brożek\n" +
                "Praca Inżynierska AGH 2016/2017\n";

        Alert aboutAlert = createInformationAlert(title, headerText, contentText);
    }

    private void registrationError(String additionalInformation){
        String title = "Błąd!";
        String headerText = "Formualarz został źle uzupełniony!";
        String contentText = additionalInformation;

        Alert RegErrAlert = createErrAlert(title, headerText, contentText);
    }

    private void registrationSucc(String additionalInformation){
        String title = "Zakończono!";
        String headerText = "Konto został ozałożone pomyślnie";
        String contentText = additionalInformation;

        Alert RegErrAlert = createInformationAlert(title, headerText, contentText);
    }

    private void loginErr(String additionalInformation){
        String title = "Błąd!";
        String headerText = "Login lub hasło jest niepoprawne";
        String contentText = additionalInformation;

        Alert logErrAlert = createErrAlert(title, headerText, contentText);
    }

    private Alert createErrAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        setAlertContent(alert, title, headerText, contentText);
        alert.showAndWait();
        return alert;
    }
    private Alert createConfirmationAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        setAlertContent(alert, title, headerText, contentText);
        alert.showAndWait();
        return alert;
    }
    private Alert createInformationAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        setAlertContent(alert, title, headerText, contentText);
        alert.showAndWait();
        return alert;
    }
    private void setAlertContent(Alert alert, String title, String headerText, String contentText){
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
    }
}