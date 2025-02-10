package model;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCampaign {
    private Campaign campaign;
    private Character c1;
    private Character c2;

    @BeforeEach
    void runBefore() {
        campaign = new Campaign();
        c1 = new Character();
        c2 = new Character();
    }

    @Test
    void testConstructor() {
        assertEquals(0, campaign.getCharacters());
    }

    @Test
    void testAddCharacterOnce() {
        campaign.addCharacter(c1);
        assertEquals(c1, campaign.getCharacters().get(0));
    }

    @Test
    void testAddCharacterMult() {
        campaign.addCharacter(c1);
        assertEquals(c1, campaign.getCharacters().get(0));
        campaign.addCharacter(c2);
        assertEquals(c2, campaign.getCharacters().get(1));
    }

    @Test
    void testRemoveCharacterOnce() {
        campaign.addCharacter(c2);
        campaign.addCharacter(c1);
        campaign.removeCharacter(c1);
        assertNull(campaign.getCharacters().get(1));
    }

    @Test
    void testRemoveCharacterMult() {
        campaign.addCharacter(c2);
        campaign.addCharacter(c1);
        campaign.removeCharacter(c1);
        campaign.removeCharacter(c2);
        assertNull(campaign.getCharacters().get(1));
        assertNull(campaign.getCharacters().get(0));
    }

    @Test
    void testEditCharacterName() {
        campaign.addCharacter(c2);
        campaign.editCharacterName(c2, "Yob");
        assertEquals("Yob", c2.getName());
    }

    @Test
    void testEditCharacterRace() {
        campaign.addCharacter(c2);
        campaign.editCharacterRace(c2, "Human");
        assertEquals("Human", c2.getRace());
    }

    @Test
    void testEditCharacterClass() {
        campaign.addCharacter(c1);
        campaign.editCharacterClass(c1, "Cleric");
        assertEquals("Cleric", c1.getCharacterClass());
    }

    @Test
    void testEditCharacterBackstory() {
        campaign.addCharacter(c2);
        campaign.editCharacterBackstory(c2, "Hello world");
        assertEquals("Hello world", c2.getName());
    }
}
