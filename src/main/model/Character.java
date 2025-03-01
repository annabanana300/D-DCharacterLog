package model;

import org.json.JSONObject;

import persistence.Writable;

//represents a character in a D&D campaign; includes methods to edit character
public class Character implements Writable {
    String name;
    String race;
    String characterClass;
    String backstory;
    
    //EFFECTS: constructs a new character
    public Character() {
        name = null;
        race = null;
        characterClass = null;
        backstory = null;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }


    //getters
    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public String getBackstory() {
        return backstory;
    }

    @Override
    //EFFECTS: puts character into json file
    public JSONObject toJson() {
        JSONObject character = new JSONObject();
        character.put("name", name);
        character.put("race", race);
        character.put("class", characterClass);
        character.put("backstory", backstory);
        return character;
    }

}
