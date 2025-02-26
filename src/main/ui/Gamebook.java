package ui;

import model.Campaign;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//UI class allowing user to access app's functions
public class Gamebook {
    private static final String FILE_DEST = "./data/campaign.json";
    private Campaign campaign;
    private Scanner scanner;
    private boolean appRunning;

    private JsonWriter writer;
    private JsonReader reader;

    // EFFECTS: constructs Campaign console ui application
    public Gamebook() throws FileNotFoundException {
        this.scanner = new Scanner(System.in);
        campaign = new Campaign();

        writer = new JsonWriter(FILE_DEST);
        reader = new JsonReader(FILE_DEST);

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
        System.out.println("p: Save current campaign to file");
        System.out.println("o: Load campaign from file");
        System.out.println("e: Close application");
    }

    //REQUIRES: option is one of q, w, e
    //EFFECTS: do menu request
    public void processMainMenuRequest(String option) {
        switch (option) {
            case "q":
                viewCharacters();
                break;
            case "w":
                addCharacter();
                break;
            case "p":
                saveCampaign();
                break;
            case "o":
                loadCampaign();
                break;
            case "e":
                quit();
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    //EFFECTS: save campaign to file
    private void saveCampaign() {
        try {
            writer.openWriter();
            writer.write(campaign);
            writer.closeWriter();
            System.out.println("Successfully saved campaign to" + FILE_DEST);
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file");
        }
    }

    //EFFECTS: loads saved campaign
    private void loadCampaign() {
        try {
            campaign = reader.read();
            System.out.println("Loaded campaign from" + FILE_DEST);
        } catch (IOException e) {
            System.out.println("Error: Please try again");
        }
    }

    //EFFECTS: print all characters in campaign
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

    //EFFECTS: computes options to select character or return home
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
        // processMainMenuRequest(option);
    }

    //EFFECTS: prints menu for editing character options
    public void selectedCharacterMenu() {
        System.out.println("\n Would you like to change any of the following about your character?");
        System.out.println("z: Name");
        System.out.println("x: Race");
        System.out.println("c: Class");
        System.out.println("v: Backstory");
        System.out.println("b: View character profile");
        System.out.println("n: Remove character");
    }

    //REQUIRES: character is one of z, x, c, v, b, n
    //EFFECTS: process editingCharacter commands
    public void editCharacter(String selected, model.Character character) {

        switch (selected) {
            case "z":
                editCharacterName(character);
                break;

            case "x":
                editCharacterRace(character);
                break;

            case "c":
                editCharacterClass(character);
                break;

            case "v":
                editCharacterBackstory(character);
                break;

            case "b":
                viewCharacter(character);
                break;

            case "n":
                deleteCharacter(character);
                break;
        }
    }

    //EFFECTS: print character profile
    public void viewCharacter(model.Character character) {
        System.out.println("\n Character profile:");
        System.out.println("Name: " + character.getName());
        System.out.println("Race: " + character.getRace());
        System.out.println("Class: " + character.getCharacterClass());
        System.out.println("Backstory: " + character.getBackstory());
    }

    //EFFECTS: delete selected character from campaign
    public void deleteCharacter(model.Character character) {
        System.out.println("\n Say goodbye to " + character.getName() + "!");
        campaign.deleteCharacter();
    }

    //EFFECTS: changes character's name to given string
    public void editCharacterName(model.Character character) {
        System.out.println("Enter character name:");
        String newName = this.scanner.nextLine();
        character.setName(newName);
    }

    //EFFECTS: changes character's race to given string
    public void editCharacterRace(model.Character character) {
        System.out.println("\nEnter character race:");
        String newRace = this.scanner.nextLine();
        character.setRace(newRace);
    }

    //EFFECTS: changes character's class to given string
    public void editCharacterClass(model.Character character) {
        System.out.println("\nEnter character class:");
        String newClass = this.scanner.nextLine();
        character.setClass(newClass);
    }

    //EFFECTS: changes character's backstory to given string
    public void editCharacterBackstory(model.Character character) {
        System.out.println("\nEnter character backstory:");
        String newStory = this.scanner.nextLine();
        character.setBackstory(newStory);
    }

    //EFFECTS: add character to campaign
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

    //EFFECTS: quit application
    public void quit() {
        appRunning = false;
    }

}
