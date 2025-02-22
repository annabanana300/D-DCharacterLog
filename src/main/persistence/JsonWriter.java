package persistence;
import model.Campaign;
import org.json.JSONObject;
import java.io.*;

public class JsonWriter {

    //EFFECTS: constructs writer
    public JsonWriter(String destination) {
        
    }

    //MODIFIES: this
    //EFFECTS: open file writer, if file not found/cannot open throw FileNotFoundException
    public void openWriter() throws FileNotFoundException {

    }

    //MODIFIES: this
    //EFFECTS: write campaign to file in JSON
    public void write(Campaign campaign) {

    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void closeWriter() {

    }

    //MODIFIES: this
    //EFFECTS: print and save JSON string to file
    private void saveToFile(String jString) {
        
    }
}
