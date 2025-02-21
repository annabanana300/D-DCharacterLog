package persistence;

import static org.junit.jupiter.api.Assertions.*;

public class TestJson {
    protected void checkCharacter(String name, String race, String characterClass, model.Character character) {
        assertEquals(name, character.getName());
        assertEquals(race, character.getRace());
        assertEquals(characterClass, character.getCharacterClass());
    }
}
