package persistence;

import model.Campaign;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GUIWriter {
    public static void saveCampaign(Campaign campaign) {
        try (FileWriter writer = new FileWriter("./data/campaign.json")) {
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{\n");
            jsonBuilder.append("  \"characters\": [\n");

            ArrayList<model.Character> characters = campaign.getCharacters();
            for (int i = 0; i < characters.size(); i++) {
                model.Character character = characters.get(i);
                jsonBuilder.append("    {\n");
                jsonBuilder.append("      \"name\": \"" + escapeSpecialCharacters(character.getName()) + "\",\n");
                jsonBuilder.append("      \"race\": \"" + escapeSpecialCharacters(character.getRace()) + "\",\n");
                jsonBuilder.append(
                        "      \"class\": \"" + escapeSpecialCharacters(character.getCharacterClass()) + "\",\n");
                jsonBuilder
                        .append("      \"backstory\": \"" + escapeSpecialCharacters(character.getBackstory()) + "\"\n");
                jsonBuilder.append("    }");
                if (i < characters.size() - 1) {
                    jsonBuilder.append(",");
                }
                jsonBuilder.append("\n");
            }

            jsonBuilder.append("  ]\n");
            jsonBuilder.append("}\n");

            // Debugging: Print the JSON string
            System.out.println("JSON to be saved:");
            System.out.println(jsonBuilder.toString());

            writer.write(jsonBuilder.toString());
            System.out.println("Campaign data saved successfully to " + "./data/campaign.json");
        } catch (IOException e) {
            System.err.println("Error saving campaign data: " + e.getMessage());
        }
    }

    static String escapeSpecialCharacters(String input) {
        if (input == null) {
            return ""; // Replace null with an empty string
        }
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
    }
}
