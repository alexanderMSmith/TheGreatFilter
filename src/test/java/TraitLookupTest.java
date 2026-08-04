import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TraitLookupTest{

    @Test
    void testGetCharacteristicReturnsCorrectValue() {

        TraitLookup lookup = new TraitLookup(Map.of(
            1, "Elf",
            2, "Dwarf",
            3, "Human"
        ));

        assertEquals("Elf", lookup.getCharacteristic(1));
        assertEquals("Dwarf", lookup.getCharacteristic(2));
    }

    @Test
    void testMissingCharacteristicReturnsUnknown() {

        TraitLookup lookup = new TraitLookup(Map.of(
            1, "Elf"
        ));

        assertEquals(
            "Unknown",
            lookup.getCharacteristic(99)
        );
    }

    @Test
    void testLoadsTraitsFromJSON() {

        TraitLookup lookup =
            new TraitLookup("/data/stats.json");

        assertEquals(
            "STR",
            lookup.getCharacteristic(1)
        );
    }

    @Test
    void testMissingJSONThrowsException(){

        assertThrows(
            IllegalArgumentException.class,
            () -> new TraitLookup("/data/not_real.json")
        );
    }
}