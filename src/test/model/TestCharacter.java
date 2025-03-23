package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Test class for Character methods
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

    @Test
    void testConstructorParameters() {
        model.Character taro = new Character("Taro", "Elf", "Cleric", "Wears eyepatch");
        assertEquals("Taro", taro.getName());
        assertEquals("Elf", taro.getRace());
        assertEquals("Cleric", taro.getCharacterClass());
        assertEquals("Wears eyepatch", taro.getBackstory());
    }

    
}
