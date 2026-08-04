/** Currently being fixed to resolve design problems in TraitLookup.
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Field;
import java.util.*;

public class TraitsSelectorTest {

    static class FakeTraitLookup extends TraitLookup {
        private final Map<Integer, String> map;

        public FakeTraitLookup(Map<Integer, String> m) {
            super(null); // constructor won't be used
            this.map = m;
        }

        @Override
        public String getCharacteristic(int number) {
            return map.get(number);
        }
    }

    // Helper to inject fake lookups via reflection
    private static void setStaticLookup(String fieldName, TraitLookup lookup)
            throws Exception {
        Field field = TraitsSelector.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, lookup);
    }

    // Helper to override randomRange
    private static void setRandomRangeReturn(int... values) throws Exception {
        // We simulate randomRange by replacing ThreadLocalRandom call:
        Field field = TraitsSelector.class.getDeclaredField("RANDOM_OVERRIDE");
        field.setAccessible(true);
        field.set(null, values);
    }

    @BeforeAll
    static void setup() throws Exception {
        // Inject fake datasets
        setStaticLookup("feats", new FakeTraitLookup(Map.of(
                1, "Sharpshooter",
                2, "Tough",
                3, "Mage Slayer"
        )));

        setStaticLookup("languages", new FakeTraitLookup(Map.of(
                1, "Common",
                2, "Dwarvish",
                3, "Elvish"
        )));

        setStaticLookup("stats", new FakeTraitLookup(Map.of(
                1, "Strength",
                2, "Dexterity",
                3, "Wisdom"
        )));

        setStaticLookup("races", new FakeTraitLookup(Map.of(
                1, "Elf",
                2, "Dwarf",
                3, "Gnome"
        )));

        setStaticLookup("backgrounds", new FakeTraitLookup(Map.of(
                1, "Soldier",
                2, "Sage",
                3, "Hermit"
        )));
    }

    @Test
    void testFeatsReturnsUniqueFeat() throws Exception {
        // Force randomRange to produce a deterministic sequence
        setRandomRangeReturn(1, 1, 1); // same number forces featCount++

        List<String> list = TraitsSelector.feats(3);
        assertEquals(1, list.size());
        assertEquals("Sharpshooter", list.get(0));
    }

    @Test
    void testLanguagePriorityGeneratesUniqueLanguages() throws Exception {
        setRandomRangeReturn(1, 2, 3); // unique sequence

        HashSet<String> langs = TraitsSelector.getLanguagePriority(3, 3);
        assertEquals(3, langs.size());
        assertTrue(langs.contains("Common"));
        assertTrue(langs.contains("Dwarvish"));
        assertTrue(langs.contains("Elvish"));
    }

    @Test
    void testLanguagePriorityAvoidsDuplicates() throws Exception {
        setRandomRangeReturn(1, 1, 2, 3);

        HashSet<String> langs = TraitsSelector.getLanguagePriority(3, 3);
        assertEquals(3, langs.size());
    }

    @Test
    void testStatPriority() throws Exception {
        setRandomRangeReturn(3, 3, 2, 1);

        HashSet<String> stats = TraitsSelector.getStatPriority(3, 3);
        assertEquals(3, stats.size());
        assertTrue(stats.contains("Strength"));
        assertTrue(stats.contains("Dexterity"));
        assertTrue(stats.contains("Wisdom"));
    }

    @Test
    void testGetBackground() throws Exception {
        setRandomRangeReturn(2);
        assertEquals("Sage", TraitsSelector.getBackground(3));
    }

    @Test
    void testGetRace() throws Exception {
        setRandomRangeReturn(1);
        assertEquals("Elf", TraitsSelector.getRace(3));
    }
    
}

*/
