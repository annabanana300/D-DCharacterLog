package persistence;

import model.Campaign;
import model.Character;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//a reader that reads a Campaign from JSON data stored in saved file
public class JsonReader {
    private String source; //string of source file to read from

    //EFFECTS: constructs reader to read from file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads a Campaign from saved file and returns it
    //throws IOException if data cannot be read
    public Campaign read() throws IOException {
        String data = readFile(source);
        JSONObject jsonObject = new JSONObject(data);
        return parseCampaign(jsonObject);
    }

    //reads file as string, returns string
    private String readFile(String source) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s));
        }
        return stringBuilder.toString();
    }

    //EFFECTS: parse campaign from JSON object, return it
    private Campaign parseCampaign(JSONObject jsonObject) {
        Campaign campaign = new Campaign();
        addCharacters(campaign, jsonObject);
        return campaign;
    }

    //MODIFIES: Campaign c
    //EFFECTS: parse characters from JSON object, add to campaign
    private void addCharacters(Campaign c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("characters");
        for (Object json : jsonArray) {
            JSONObject nextCharacter = (JSONObject) json;
            addCharacter(c, nextCharacter);
        }
        //key: "characters"
    }

    //MODIFIES: Campaign c
    //EFFECTS: parse character from JSON object, add to campaign
    private void addCharacter(Campaign c, JSONObject jsonObject) {
        String characterName = jsonObject.getString("name");
        String race = jsonObject.getString("race");
        String characterClass = jsonObject.getString("class");
        String backstory = jsonObject.getString("backstory");
        
        model.Character character = new Character();

        character.setName(characterName);
        character.setRace(race);
        character.setClass(characterClass);
        character.setBackstory(backstory);

        c.addCharacter(character);
    }
}