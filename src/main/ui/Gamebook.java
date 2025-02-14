package ui;

import model.Campaign;

import java.util.Scanner;

public class Gamebook {
    private Campaign campaign;
    private Scanner scanner;
    private boolean appRunning;

    // EFFECTS: boots up Campaign console ui application
    public Gamebook() {
        this.scanner = new Scanner(System.in);
        campaign = new Campaign();
        appRunning = true;
        System.out.println("Welcome to your D&D Campaign Character Logbook!");

        while (this.appRunning) {
            mainMenu();
        }

    }

    // EFFECTS: display main menu and process user's request
    public void mainMenu() {
        printCampaign();
        String option = this.scanner.nextLine();
        processMainMenuRequest(option);
    }

    // EFFECTS: prints menu options
    public void printCampaign() {
        System.out.println("\nSelect an option on your keyboard:");
        System.out.println("q: View all characters");
        System.out.println("w: Add a new character");
        System.out.println("e: Close application");
    }

    // do menu request
    public void processMainMenuRequest(String option) {
        switch (option) {
            case "q":
                viewCharacters();
                break;
            case "w":
                addCharacter();
                break;
            case "e":
                quit();
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    // print all characters in campaign
    public void viewCharacters() {
        System.out.println("Current characters in campaign: \n");

        for (model.Character character : campaign.getCharacters()) {
            System.out.println("\n" + character.getName());
        }
        System.out.println("\nSelect a character by typing their name or enter random character to return home:");

        String option = this.scanner.nextLine();
        characterMenuProcess(option);

        // display character list options: select character, remove character
    }

    // options to select character or return home
    public void characterMenuProcess(String option) {
        if (campaign.getCharacters().size() == 0) {
            processMainMenuRequest(option);
        }
        for (model.Character character : campaign.getCharacters()) {
            if (option.equals(character.getName())) {
                campaign.currentCharacter(character);

                selectedCharacterMenu(); // displays options to edit character
                String selected = this.scanner.nextLine();
                editCharacter(selected, character); // processes requests to edit character
                break;
            }
        }
        //processMainMenuRequest(option);
    }

    // menu for editing character options
    public void selectedCharacterMenu() {
        System.out.println("\n What would you like to edit about your character?");
        System.out.println("z: Name");
        System.out.println("x: Race");
        System.out.println("c: Class");
        System.out.println("v: Backstory");
        System.out.println("b: Remove character");
    }

    // process editingCharacter commands
    public void editCharacter(String selected, model.Character character) {

        switch (selected) {
            case "z":
                System.out.println("Enter character name:");
                String newName = this.scanner.nextLine();
                character.setName(newName);
                break;

            case "x":
                System.out.println("\nEnter character race:");
                String newRace = this.scanner.nextLine();
                character.setRace(newRace);
                break;

            case "c":
                System.out.println("\nEnter character class:");
                String newClass = this.scanner.nextLine();
                character.setClass(newClass);
                break;

            case "v":
                System.out.println("\nEnter character backstory:");
                String newStory = this.scanner.nextLine();
                character.setBackstory(newStory);
                break;

            case "b":
                System.out.println("\n Say goodbye to " + character.getName() + "!");
                campaign.currentCharacter(character);
                campaign.deleteCharacter();
                break;
        }
    }

    // add character to campaign
    public void addCharacter() {
        System.out.println("Enter character name:");
        String newName = this.scanner.nextLine();

        System.out.println("\nEnter character race:");
        String newRace = this.scanner.nextLine();

        System.out.println("\nEnter character class:");
        String newClass = this.scanner.nextLine();

        System.out.println("\nEnter character backstory:");
        String newStory = this.scanner.nextLine();

        model.Character newCharacter = new model.Character();
        newCharacter.setName(newName);
        newCharacter.setRace(newRace);
        newCharacter.setClass(newClass);
        newCharacter.setBackstory(newStory);

        campaign.addCharacter(newCharacter);

        System.out.println("Your character has been added \n");

    }

    // quit application
    public void quit() {
        appRunning = false;
    }

}
