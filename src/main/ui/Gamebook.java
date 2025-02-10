package ui;

import model.Campaign;

import java.util.Scanner;


public class Gamebook {
    private Campaign campaign;
    private Scanner scanner;

    //EFFECTS: boots up Campaign console ui application
    public Gamebook() {
        this.scanner = new Scanner(System.in);
        campaign = new Campaign();
        System.out.println("Welcome to your D&D Campaign Character Logbook!");
        mainMenu();
    }

    //EFFECTS: display main menu and process user's request
    public void mainMenu() {
        printCampaign();
        String option = this.scanner.nextLine();
        processMainMenuRequest(option);
    }

    //EFFECTS: prints menu options
    public void printCampaign() {
        System.out.println("Select an option on your keyboard:");
        System.out.println("q: View all characters");
        System.out.println("w: Add a new character");
        System.out.println("e: Close application");
    }

    public void processMainMenuRequest(String option) {
        
    }
}
