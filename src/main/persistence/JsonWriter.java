package persistence;

import model.Campaign;
import org.json.JSONObject;
import java.io.*;

public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: open file writer, if file not found/cannot open throw FileNotFoundException
    public void openWriter() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: write campaign to file in JSON
    public void write(Campaign campaign) {
        JSONObject json = campaign.toJson();
        saveToFile(json.toString(4));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void closeWriter() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: print and save JSON string to file
    private void saveToFile(String jsonString) {
        writer.print(jsonString);
    }
}