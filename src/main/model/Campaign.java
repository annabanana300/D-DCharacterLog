package model;

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
        EventLog.getInstance().logEvent(new Event("Please welcome new character added!"));
        System.out.println("Add character event added");
    }

    // REQUIRES: character already exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: removes a character from campaign
    public void deleteCharacter() {
        characters.remove(current);
    }

    // REQUIRES: character already exists in campaign ; campaign is not empty
    // MODIFIES: this
    // EFFECTS: removes a character from campaign
    public void removeCharacter(String name) {
        for (Character c : getCharacters()) {
            if (c.getName().equals(name)) {
                characters.remove(c);
                break;
            }
        }
    }

    // REQUIRES: character exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: user can edit existing character name
    public void editCharacterName(String name) {
        current.setName(name);
        EventLog.getInstance().logEvent(new Event(current.name + "'s name has been changed."));
        System.out.println("Change character name event added");
    }

    // REQUIRES: character exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: user can edit existing character's race
    public void editCharacterRace(String race) {
        current.setRace(race);
        EventLog.getInstance().logEvent(new Event(current.name + "'s race has been changed."));
    }

    // REQUIRES: character exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: user can edit existing character's class
    public void editCharacterClass(String characterClass) {
        current.setClass(characterClass);
        EventLog.getInstance().logEvent(new Event(current.name + "'s class has been changed."));
    }

    // REQUIRES: character exists in campaign && current != null
    // MODIFIES: this
    // EFFECTS: user can edit existing character's backstory
    public void editCharacterBackstory(String story) {
        current.setBackstory(story);
        EventLog.getInstance().logEvent(new Event(current.name + "'s backstory has been changed."));
    }

    // MODIFIES: this
    // EFFECTS: sets current character to null
    public void removeCurrentCharacter() {
        current = null;
        EventLog.getInstance().logEvent(new Event("Character was removed. Bye bye!"));
        System.out.println("Remove character event added");
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
}
