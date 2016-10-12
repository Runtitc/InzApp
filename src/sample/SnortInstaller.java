package sample;

import java.io.IOException;

/**
 * Created by Przemix on 10/12/16.
 */
public class SnortInstaller {
    private String filePath; // destination path

    public SnortInstaller(String filePath){
        this.filePath = filePath;
    }
    public void installLinux(){
        String scriptName = "./SnortInstallation.sh";
        System.out.println("Wykonuje: installer.installLinux();");
        try {
            Process SnortInstaller = Runtime.getRuntime().exec("sudo apt-get update");

        }
        catch (IOException e) {
            System.out.println("Script doesn't exist");
        }
    }
}
