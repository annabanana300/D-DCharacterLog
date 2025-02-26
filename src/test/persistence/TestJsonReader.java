package persistence;

import model.Campaign;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestJsonReader extends TestJson {
    private Campaign campaign;

    @Test
    void testReadNoFile() {
        //read from a non-existent file; catch exception
        JsonReader reader = new JsonReader("./data/imaginaryFile.json");
        try {
            campaign = reader.read();
            fail();
        } catch (IOException e) {
            //pass if exception thrown
        }
    }

    @Test
    void testReadEmptyCampaign() {
        //read from an empty file; return an empty campaign w/ 0 characters
        JsonReader reader = new JsonReader("./data/testReaderEmptyCampaign.json");
        try {
            campaign = reader.read();
            assertEquals(0, campaign.getCharacters().size());
        } catch (IOException e) {
            fail();
            //pass if exception thrown
        }
    }

    @Test
    void testReadCampaign() {
        //read from a general file; return campaign's list of characters in it
        JsonReader reader = new JsonReader("./data/testReaderCampaign.json");
        try {
            campaign = reader.read();
            assertEquals(2, campaign.getCharacters().size());
            checkCharacter("Taro", "Half-Elf", "Cleric", campaign.getCharacters().get(0));
            checkCharacter("Peach", "Fairy", "Rogue", campaign.getCharacters().get(1));
        } catch (IOException e) {
            fail();
        }
    }
}
