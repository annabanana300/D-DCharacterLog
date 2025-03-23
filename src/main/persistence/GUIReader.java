package persistence;

import model.Campaign;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GUIReader {
    public static Campaign loadCampaign() {
        Campaign campaign = new Campaign();
        try (Scanner scanner = new Scanner(new File("./data/campaign.json"))) {
            StringBuilder jsonContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine().trim());
            }

            String jsonString = jsonContent.toString();
            int startIndex = jsonString.indexOf("[") + 1;
            int endIndex = jsonString.lastIndexOf("]");
            String charactersArray = jsonString.substring(startIndex, endIndex);

            String[] characterEntries = charactersArray.split("\\},\\s*\\{");
            for (String entry : characterEntries) {
                entry = entry.replaceAll("[{}\"]", "").trim();
                String[] fields = entry.split(",");

                String name = null, race = null, characterClass = null, backstory = null;
                for (String field : fields) {
                    String[] keyValue = field.split(":");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    switch (key) {
                        case "name":
                            name = value;
                            break;
                        case "race":
                            race = value;
                            break;
                        case "class":
                            characterClass = value;
                            break;
                        case "backstory":
                            backstory = value;
                            break;
                    }
                }

                if (name != null && race != null && characterClass != null && backstory != null) {
                    campaign.addCharacter(new model.Character(name, race, characterClass, backstory));
                }
            }

            System.out.println("Campaign data loaded successfully from " + "./data/campaign.json");
        } catch (FileNotFoundException e) {
            System.err.println("Error loading campaign data: " + e.getMessage());
        }

        return campaign;
    }
}
