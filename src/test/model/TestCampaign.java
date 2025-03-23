package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertEquals(0, campaign.getCharacters().size());
        assertNull(campaign.getCurrentCharacter());
    }

    @Test
    void testRemoveCurrentCharacter() {
        campaign.currentCharacter(c1);
        campaign.removeCurrentCharacter();
        assertNull(campaign.getCurrentCharacter());
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
        campaign.currentCharacter(c1);
        campaign.deleteCharacter();
        assertEquals(1, campaign.getCharacters().size());
    }

    @Test
    void testRemoveCharacterMult() {
        campaign.addCharacter(c2);
        campaign.addCharacter(c1);
        campaign.currentCharacter(c1);
        campaign.deleteCharacter();
        campaign.currentCharacter(c2);
        campaign.deleteCharacter();
        assertEquals(0, campaign.getCharacters().size());
    }

    @Test
    void testRemoveCharacterGUI() {
        campaign.addCharacter(c2);
        campaign.addCharacter(c1);
        c1.setName("Asher");
        c2.setName("Ash");
        campaign.removeCharacter("Asher");
        assertEquals(1, campaign.getCharacters().size());
    }

    @Test
    void testRemoveCharacterGUIBoth() {
        campaign.addCharacter(c2);
        campaign.addCharacter(c1);
        c1.setName("Asher");
        c2.setName("Ash");
        campaign.removeCharacter(c2.getName());
        campaign.removeCharacter(c1.getName());
        assertEquals(0, campaign.getCharacters().size());
    }

    @Test
    void testEditCharacterName() {
        campaign.addCharacter(c2);
        campaign.currentCharacter(c2);

        campaign.editCharacterName("Yob");
        assertEquals("Yob", c2.getName());
    }

    @Test
    void testEditCharacterRace() {
        campaign.addCharacter(c2);
        campaign.currentCharacter(c2);

        campaign.editCharacterRace("Human");
        assertEquals("Human", c2.getRace());
    }

    @Test
    void testEditCharacterClass() {
        campaign.addCharacter(c1);
        campaign.currentCharacter(c1);

        campaign.editCharacterClass("Cleric");
        assertEquals("Cleric", c1.getCharacterClass());
    }

    @Test
    void testEditCharacterBackstory() {
        campaign.addCharacter(c2);
        campaign.currentCharacter(c2);

        campaign.editCharacterBackstory("Hello world");
        assertEquals("Hello world", c2.getBackstory());
    }

    @Test
    void testCurrentCharacter() {
        campaign.currentCharacter(c1);
        assertEquals(c1, campaign.getCurrentCharacter());
    }

    @Test
    void testCurrentCharacterMult() {
        campaign.currentCharacter(c1);
        assertEquals(c1, campaign.getCurrentCharacter());
        campaign.currentCharacter(c2);
        assertEquals(c2, campaign.getCurrentCharacter());
    }

    
}
