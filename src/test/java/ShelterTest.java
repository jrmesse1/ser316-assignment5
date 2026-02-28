import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelterTest {
    @Test
    void shelterConstructor() {
        Shelter shelter = new Shelter();

        // at startup there should be 5 animals
        assertEquals("0 animal(s) have been adopted. There are 5 animal(s) in the shelter and 0 are available for adoption", shelter.getStats());
    }
}