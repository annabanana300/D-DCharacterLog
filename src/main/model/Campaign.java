package model;

import java.util.ArrayList;
//import java.util.List;

//represents a D&D campaign showing all characters added
public class Campaign {

    ArrayList<Character> characters;

    //EFFECTS: constructs an empty campaign
    public Campaign() {
        characters = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: creates a new character to add to campaign
    public void addCharacter(Character c) {

    }

    //REQUIRES: character already exists in campaign
    //MODIFIES: this
    //EFFECTS: removes a character from campaign
    public void removeCharacter(Character c) {

    }

    //REQUIRES: character exists in campaign
    //MODIFIES: this
    //EFFECTS: user can edit existing character name
    public void editCharacterName(Character c, String name) {

    }

    //REQUIRES: character exists in campaign
    //MODIFIES: this
    //EFFECTS: user can edit existing character's race
    public void editCharacterRace(Character c, String race) {
        
    }

    //REQUIRES: character exists in campaign
    //MODIFIES: this
    //EFFECTS: user can edit existing character's class
    public void editCharacterClass(Character c, String characterClass) {
        
    }

    //REQUIRES: character exists in campaign
    //MODIFIES: this
    //EFFECTS: user can edit existing character's backstory
    public void editCharacterBackstory(Character c, String story) {
        
    }

    //getters
    //EFFECTS: get list of characters currently in campaign
    public ArrayList<Character> getCharacters() {
        return characters;
    }

}
