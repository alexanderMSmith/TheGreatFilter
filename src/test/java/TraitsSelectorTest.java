import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TraitsSelectorTest {
    private TraitsSelector selector;
    @BeforeEach
    void setup() {

        selector = new TraitsSelector(
            new FakeRandomGenerator(1,2,3),

            new TraitLookup(Map.of(
                1, "Sharpshooter",
                2, "Tough",
                3, "Mage Slayer"
            )),

            new TraitLookup(Map.of(
                1, "Common",
                2, "Dwarvish",
                3, "Elvish"
            )),

            new TraitLookup(Map.of(
                1, "Strength",
                2, "Dexterity",
                3, "Wisdom"
            )),

            new TraitLookup(Map.of(
                1, "Elf",
                2, "Dwarf",
                3, "Gnome"
            )),

            new TraitLookup(Map.of(
                1, "Soldier",
                2, "Sage",
                3, "Hermit"
            ))
        );
    }

    @Test
    void testFeatsReturnsUniqueFeat() throws Exception {
        // Force randomRange to produce a deterministic sequence
        RandomGenerator fakeRandom = new FakeRandomGenerator(1,1,1);
        selector.setRandomGenerator(fakeRandom);
        List<String> list = selector.feats(3);
        assertEquals(1, list.size());
        assertEquals("Sharpshooter", list.get(0));
    }

    @Test
    void testLanguagePriorityGeneratesUniqueLanguages() throws Exception {
        //setRandomRangeReturn(1, 2, 3); // unique sequence
        RandomGenerator fakeRandom = new FakeRandomGenerator(1,2,3);
        selector.setRandomGenerator(fakeRandom);
        HashSet<String> langs = selector.getLanguagePriority(3, 3);
        assertEquals(3, langs.size());
        assertTrue(langs.contains("Common"));
        assertTrue(langs.contains("Dwarvish"));
        assertTrue(langs.contains("Elvish"));
    }

    @Test
    void testLanguagePriorityAvoidsDuplicates() throws Exception {
        //setRandomRangeReturn(1, 1, 2, 3);
        RandomGenerator fakeRandom = new FakeRandomGenerator(1,1,2,3);
        selector.setRandomGenerator(fakeRandom);
        HashSet<String> langs = selector.getLanguagePriority(3, 3);
        assertEquals(3, langs.size());
    }

    @Test
    void testStatPriority() throws Exception {
        //setRandomRangeReturn(3, 3, 2, 1);
        RandomGenerator fakeRandom = new FakeRandomGenerator(3,3,2,1);
        selector.setRandomGenerator(fakeRandom);
        HashSet<String> stats = selector.getStatPriority(3, 3);
        assertEquals(3, stats.size());
        assertTrue(stats.contains("Strength"));
        assertTrue(stats.contains("Dexterity"));
        assertTrue(stats.contains("Wisdom"));
    }

    @Test
    void testGetBackground() throws Exception {
        //setRandomRangeReturn(2);
        RandomGenerator fakeRandom = new FakeRandomGenerator(2);
        selector.setRandomGenerator(fakeRandom);
        assertEquals("Sage", selector.getBackground(3));
    }

    @Test
    void testGetRace() throws Exception {
        //setRandomRangeReturn(1);
        RandomGenerator fakeRandom = new FakeRandomGenerator(1);
        selector.setRandomGenerator(fakeRandom);
        assertEquals("Elf", selector.getRace(3));
    }
}
