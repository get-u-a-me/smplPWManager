import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        //liste laden
        PasswordList list = load();
        //Account klasse erstellen
        //Liste für gespeicherte passwörte

        //Logik
        boolean exit = false;
        while(!exit){

            switch (askForInput("Was möchtest du tun?\n ADD/SHOW/REMOVE/EDIT/EXIT").toLowerCase()){
                case "add" -> {

                    displayPlatform(list.list);
                    String platform = askForInput("Welche Platform?\n ");
                    String name = askForInput("Nutzername:");
                    String password = askForInput("Passwort: ");
                    String email = askForInput("Email:");

                    list.add(new Account(platform, name, password, email));
                }
                case "show" -> {
                    String platform = askForInput("Für welche platform möchtest du den Account sehen?\nFür alle gib \"ALL\" ein").toLowerCase();
                    displayPlatform(list.list);
                    if (platform.equalsIgnoreCase("all")) {
                        show(list.list);
                    }
                    show(list.list, platform);
                }
                case "remove" -> {
                    String platform = askForInput("Platform:");
                    String name = askForInput("name");
                    list.remove(platform, name);
                }
                case "edit" -> {

                }
                case "exit" -> {
                    exit = true;
                    save(list.list);
                }
            }
        }
    }

    //load list
    public static PasswordList load(){
        PasswordList list = new PasswordList();
        File file = new File("password.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(file);

            String filestr = "";
            int tmp= 0;
            while ((tmp = fileReader.read()) != -1){
                filestr+=  (char)tmp;
            }
            fileReader.close();

            String[] zeilen = filestr.split("\n");
            if(!zeilen[0].isEmpty()){
                for (String value : zeilen){
                    String[] daten = value.split(":");

                    list.add(new Account(daten[0], daten[1], daten[2], daten[3]));
                }
            }


        } catch (IOException e){
            System.out.println("Liste konnte nicht geladen werden!");
        }

        return list;
    }

    //Liste speichern
    public static void save(Account[] accounts){
        File file = new File("password.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);
            for (Account value : accounts) {
                if (value != null) {
                    fileWriter.write(value.getPlatform() + ":" + value.getName() + ":" + value.getPassword() + ":" + value.getEmail() + "\n");
                }
            }

            fileWriter.close();


        } catch(IOException e){
            System.out.println("Could not save.");
        }
    }

    public static void show(Account[] accounts, String platform){
        PasswordList list = new PasswordList();
        for(Account value : accounts){
            if(value != null){
                if(value.getPlatform().equalsIgnoreCase(platform)){
                    System.out.println(value.toString());
                }

            }

        }
    }

    public static void show(Account[] accounts){
        for(Account value : accounts){
            if(value !=null){
                System.out.println(value.toString());
            }
        }
    }

    //Eingabeaufforderung
    public static String askForInput(String text){
        String input = "";
        while (input.trim().isEmpty()){
            input = JOptionPane.showInputDialog(text);
        }

        return input;

    }

    public static String displayPlatform(Account[] accounts) {
        Set<String> platforms = new HashSet<>();
        for (Account account : accounts) {
            if (account != null && account.getPlatform() != null) {
                platforms.add(account.getPlatform());
            }
        }

        return (String.join(", ", platforms));
    }
}