/**
 * This class searches an associated JSON file decided during the creation of the instance.
 * Each JSON file contains a mapping of integer IDs to characteristic names.
 * @author Alexander Smith
 * @version 1.0
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class TraitLookup {

    private final Map<Integer, String> characteristics;

    /**
     * Loads traits from JSON files located in resources.
     * @param jsonResourcePath path to the relevant json, e.g., "/data/feats.json"
     */
    public TraitLookup(String jsonResourcePath) {
        Map<Integer, String> tempMap;
        try (InputStream is = getClass().getResourceAsStream(jsonResourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + jsonResourcePath);
            }
            ObjectMapper mapper = new ObjectMapper();
            tempMap = mapper.readValue(
                getClass().getResourceAsStream(jsonResourcePath),
                new TypeReference<Map<Integer, String>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON: " + jsonResourcePath, e);
        }
        characteristics = Collections.unmodifiableMap(tempMap); //Locks in the map
    }
    /**
     * Gets the characteristic name for a given ID.
     * @param n The ID of the characteristic.
     * @return The name of the characteristic, or "Unknown" if not found.
     */
    public String getCharacteristic(int n) {
        return characteristics.getOrDefault(n, "Unknown");
    }
}
