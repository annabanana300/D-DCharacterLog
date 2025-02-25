package ui;

import java.io.FileNotFoundException;

//runs a new instance of the application
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            new Gamebook();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}
