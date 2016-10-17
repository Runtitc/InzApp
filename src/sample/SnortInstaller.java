package sample;

import java.io.IOException;

/**
 * Created by Przemix on 10/12/16.
 */
public class SnortInstaller {
    private String filePath;

    public SnortInstaller(String filePath){
        this.filePath = filePath;
    }
    public void install(){
        String scriptName = "nazwa skryptu w pythonie do odpalenia";

        try {
            Process pythonSnortInstaller = Runtime.getRuntime().exec(scriptName);
        }
        catch (IOException e) {
            System.out.println("Script doesn't exist");
        }
    }
}