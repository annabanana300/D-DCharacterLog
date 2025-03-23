package model;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

//represents a D&D campaign showing all characters added and abilities to edit character
public class Campaign implements Writable {

    ArrayList<Character> characters;
    Character current;
    private static final String FILE_DEST = "./data/campaign.json";

    // EFFECTS: constructs an empty campaign
    public Campaign() {
        characters = new ArrayList<>();
        current = null;
    }

    // MODIFIES: this
    // EFFECTS: sets current character to chosen character
    public void currentCharacter(Character c) {
        this.current = c;
    }

    // MODIFIES: this
    // EFFECTS: creates a new character to add to campaign
    public void addCharacter(Character c) {
        characters.add(c);
    }

    // REQUIRES: character already exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: removes a character from campaign
    public void deleteCharacter() {
        characters.remove(current);
    }

    // REQUIRES: character already exists in campaign
    // MODIFIES: this
    // EFFECTS: removes a character from campaign
    public void removeCharacter(String name) {
        for (Character c : getCharacters()) {
            if (c.getName().equals(name)) {
                characters.remove(c);
            }
        }
    }

    // REQUIRES: character exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: user can edit existing character name
    public void editCharacterName(String name) {
        current.setName(name);
    }

    // REQUIRES: character exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: user can edit existing character's race
    public void editCharacterRace(String race) {
        current.setRace(race);
    }

    // REQUIRES: character exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: user can edit existing character's class
    public void editCharacterClass(String characterClass) {
        current.setClass(characterClass);
    }

    // REQUIRES: character exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: user can edit existing character's backstory
    public void editCharacterBackstory(String story) {
        current.setBackstory(story);
    }

    // MODIFIES: this
    // EFFECTS: sets current character to null
    public void removeCurrentCharacter() {
        current = null;
    }

    // getters
    // EFFECTS: get list of characters currently in campaign
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    // EFFECTS: get current character; null if not set
    public Character getCurrentCharacter() {
        return current;
    }

    @Override
    // EFFECTS: puts all the characters in campaign into Json file
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("characters", charactersToJson());
        return json;
    }

    // EFFECTS: returns characters in campaign as JSON list
    public JSONArray charactersToJson() {
        JSONArray array = new JSONArray();

        for (model.Character c : characters) {
            array.put(c.toJson());
        }
        return array;
    }

    // EFFECTS: saves campaign in GUI app to file
    public void saveCampaignToFile() throws IOException {
        JsonWriter writer = new JsonWriter(FILE_DEST);
        writer.openWriter();
        writer.write(this);
        writer.closeWriter();
    }

    // EFFECTS: loads a saved GUI version of campaign
    public void loadCampaignFromFile() throws IOException {
        JsonReader reader = new JsonReader(FILE_DEST);
        reader.read();
    }
}
