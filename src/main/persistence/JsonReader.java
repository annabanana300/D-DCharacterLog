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

    //EFFECTS: constructs reader to read from file
    public JsonReader(String source) {

    }

    //EFFECTS: reads a Campaign from saved file and returns it
    //throws ReadErrorException if data cannot be read
    public Campaign read() {
        return null;
    }

    //reads file as string, returns string
    private String readFile(String source) {
        return null;
    }

    //EFFECTS: parse campaign from JSON object, return it
    private Campaign parseCampaign(JSONObject jsonObject) {
        return null;
    }

    //MODIFIES: Campaign c
    //EFFECTS: parse characters from JSON object, add to campaign
    private void addCharacters(Campaign c, JSONObject jsonObject) {
    
    }

    //MODIFIES: Campaign c
    //EFFECTS: parse character from JSON object, add to campaign
    private void addCharacter(Campaign c, JSONObject jsonObject) {

    }
}
