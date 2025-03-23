package persistence;

import model.Campaign;
//import org.json.JSONObject;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonWriter {
    //private PrintWriter writer;
    private String destination;
    private BufferedWriter writer;
    //Gson gson;

    JSONArray jsonArray = new JSONArray();

    //EFFECTS: constructs writer
    public JsonWriter(String destination) throws IOException {
        writer = new BufferedWriter(new FileWriter(destination));
        this.destination = destination;
    } 

    //MODIFIES: this
    //EFFECTS: open file writer, if file not found/cannot open throw FileNotFoundException
    public void openWriter() throws FileNotFoundException {
        //writer = new BufferedWriter(new File(destination));
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
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: print and save JSON string to file
    private void saveToFile(String jsonString) {
        //writer.print(jsonString);
    }
}
