package model;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCharacter {

    public Character c;
    
    @BeforeEach
    void runBefore() {
        c = new Character();
    }

    @Test
    void testConstructor() {
        assertNull(c.getName());
        assertNull(c.getRace());
        assertNull(c.getCharacterClass());
        assertNull(c.getBackstory());
    }

    
}
