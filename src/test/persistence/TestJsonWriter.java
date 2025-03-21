package persistence;

import model.Campaign;
import model.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class TestJsonWriter extends TestJson {
    private Campaign campaign;
    JsonWriter writer;
    JsonReader reader;
    model.Character taro = new Character();

    @BeforeEach
    void makeCharacter() {
        //character to add
        taro.setName("Taro");
        taro.setRace("Half-Elf");
        taro.setClass("Cleric");
        taro.setBackstory("Taro wears a flowered eyepatch on her eye after having poisoned tea spilled on her.");
    }

    @Test
    void testWriteInvalidFile() {
        try {
            writer = new JsonWriter("./data/InvalidFile\0.json");
            writer.openWriter();
            fail();
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriteEmptyCampaign() {
        //write empty file, read file, show zero characters in campaign
        try {
            campaign = new Campaign();
            writer = new JsonWriter("./data/testWriterEmptyCampaign.json");
            writer.openWriter();
            writer.write(campaign);
            writer.closeWriter();

            reader = new JsonReader("./data/testWriterEmptyCampaign.json");
            campaign = reader.read();
            assertEquals(0, campaign.getCharacters().size());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testWriteCampaign() {
        //construct characters in json file
        //write normal file, read file, show list of characters in campaign
        try {
            campaign = new Campaign();
            //make two characters to add to campaign
            campaign.addCharacter(taro);

            model.Character peach = new Character();
            peach.setName("Peach");
            peach.setRace("Fairy");
            peach.setClass("Rogue");
            peach.setBackstory("Peach makes a living as a spy for those who wish to uncover secrets.");
            campaign.addCharacter(peach);

            //write added characters to file
            writer = new JsonWriter("./data/testWriterCampaign.json");
            writer.openWriter();
            writer.write(campaign);
            writer.closeWriter();

            //check that reading the saved file produces same list of added characters
            reader = new JsonReader("./data/testWriterCampaign.json");
            campaign = reader.read();
            assertEquals(2, campaign.getCharacters().size());
            checkCharacter("Taro", "Half-Elf", "Cleric", campaign.getCharacters().get(0));
            checkCharacter("Peach", "Fairy", "Rogue", campaign.getCharacters().get(1));
        } catch (IOException e) {
            fail();
        }
    }
}
