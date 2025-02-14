package model;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCharacter {

    private Character character;
    
    @BeforeEach
    void runBefore() {
        character = new Character();
    }

    @Test
    void testConstructor() {
        assertNull(character.getName());
        assertNull(character.getRace());
        assertNull(character.getCharacterClass());
        assertNull(character.getBackstory());
    }

    
}
