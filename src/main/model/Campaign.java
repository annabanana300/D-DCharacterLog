package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

//represents a D&D campaign showing all characters added and abilities to edit character
public class Campaign implements Writable {

    ArrayList<Character> characters;
    Character current;

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
    private JSONArray charactersToJson() {
        JSONArray array = new JSONArray();

        for (model.Character c : characters) {
            array.put(c.toJson());
        }
        return array;
    }

    // EFFECTS: saves campaign in GUI app to file
    public void saveCampaignToFile() throws IOException {
        File file = new File("campaign.json"); // Fixed file destination
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("[\n");
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            writer.write("  {\n");
            writer.write("    \"name\": \"" + character.getName() + "\",\n");
            writer.write("    \"race\": \"" + character.getRace() + "\",\n");
            writer.write("    \"class\": \"" + character.getCharacterClass() + "\",\n");
            writer.write("    \"backstory\": \"" + character.getBackstory() + "\"\n");
            writer.write("  }");
            if (i < characters.size() - 1) {
                writer.write(",");
            }
            writer.write("\n");
        }
        writer.write("]\n");

        writer.close();
    }

    // EFFECTS: loads a saved GUI version of campaign
    public void loadCampaignFromFile() throws IOException {
        File file = new File("campaign.json"); // Fixed file destination
        if (!file.exists()) {
            return; // If file doesn't exist, return early
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<Character> loadedCharacters = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if (line.contains("\"name\":")) {
                String name = line.split(":")[1].trim().replace("\"", "").replace(",", "");
                String race = reader.readLine().split(":")[1].trim().replace("\"", "").replace(",", "");
                String characterClass = reader.readLine().split(":")[1].trim().replace("\"", "").replace(",", "");
                String backstory = reader.readLine().split(":")[1].trim().replace("\"", "").replace(",", "");

                Character character = new Character(name, race, characterClass, backstory);
                loadedCharacters.add(character);

                // Skip to the next character or closing bracket
                reader.readLine(); // Skip closing }
                reader.readLine(); // Skip comma or closing bracket
            }
        }

        characters = loadedCharacters;
        reader.close();
    }
}
