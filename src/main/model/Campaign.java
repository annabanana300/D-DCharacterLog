package model;

import java.util.ArrayList;
//import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.writeToJson;

//represents a D&D campaign showing all characters added and abilities to edit character
public class Campaign implements writeToJson{

    ArrayList<Character> characters;
    Character current;

    //EFFECTS: constructs an empty campaign
    public Campaign() {
        characters = new ArrayList<>();
        current = null;
    }

    //MODIFIES: this
    //EFFECTS: sets current character to chosen character
    public void currentCharacter(Character c) {
        this.current = c;
    }

    //MODIFIES: this
    //EFFECTS: creates a new character to add to campaign
    public void addCharacter(Character c) {
        characters.add(c);
    }

    //REQUIRES: character already exists in campaign && current != null
    //MODIFIES: this
    //EFFECTS: removes a character from campaign
    public void deleteCharacter() {
        characters.remove(current);
    }

    //REQUIRES: character exists in campaign && current != null
    //MODIFIES: this
    //EFFECTS: user can edit existing character name
    public void editCharacterName(String name) {
        current.setName(name);
    }

    //REQUIRES: character exists in campaign && current != null
    //MODIFIES: this
    //EFFECTS: user can edit existing character's race
    public void editCharacterRace(String race) {
        current.setRace(race);
    }

    //REQUIRES: character exists in campaign && current != null
    //MODIFIES: this
    //EFFECTS: user can edit existing character's class
    public void editCharacterClass(String characterClass) {
        current.setClass(characterClass);
    }

    //REQUIRES: character exists in campaign && current != null
    //MODIFIES: this
    //EFFECTS: user can edit existing character's backstory
    public void editCharacterBackstory(String story) {
        current.setBackstory(story);
    }

    //MODIFIES: this
    //EFFECTS: sets current character to null
    public void removeCurrentCharacter() {
        current = null;
    }

    //getters
    //EFFECTS: get list of characters currently in campaign
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    //EFFECTS: get current character; null if not set
    public Character getCurrentCharacter() {
        return current;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("characters", charactersToJson());
        return json;
    }

    //EFFECTS: returns characters in campaign as JSON list
    private JSONArray charactersToJson() {
        JSONArray array = new JSONArray();

        for (model.Character c:characters) {
            array.put(c.toJson());
        }
        return array;
    }

}
