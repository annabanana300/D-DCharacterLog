package persistence;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: returns object (campaign) as JSON object
    JSONObject toJson();
}
