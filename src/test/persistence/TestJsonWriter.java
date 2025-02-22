package persistence;

import model.Campaign;
import model.Character;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestJsonWriter extends TestJson{
    private Campaign campaign;
    JsonWriter writer;
    JsonReader reader;

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
}
