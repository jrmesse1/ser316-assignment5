import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShelterTest {
    @BeforeEach
    void setUp() {
        TaskList.resetInstance();
    }

    @Test
    void shelterConstructor() {
        Shelter shelter = new Shelter();

        // at startup there should be 5 animals
        assertEquals(
                "0 animal(s) have been adopted. There are 5 animal(s) in the shelter and 0 are available for adoption",
                shelter.getStats());
    }

    @Test
    void addDailyTaskAssignment() {
        Shelter shelter = new Shelter();
        TaskList tasks = TaskList.getInstance();
        assertEquals("Staff have completed 0 tasks. They are working on 0 and 5 are unassigned.", tasks.getStats());

        // should create 3 tasks per animal, adding 15 tasks to the list
        shelter.addDailyTaskAssignment();
        assertEquals("Staff have completed 0 tasks. They are working on 0 and 20 are unassigned.", tasks.getStats());
    }

    @Test
    void maybeAdoptAnimals() {
        // setup shelter with an animal that is ready for adoption
        Shelter shelter = new Shelter();
        Animal animal = shelter.intakeAnimal();
        animal.setStatus(AnimalStatus.AVAILABLE);

        // stats should indicate that no animals are adopted
        assertEquals(
                "0 animal(s) have been adopted. There are 6 animal(s) in the shelter and 1 are available for adoption",
                shelter.getStats());

        // redirect output to capture logs
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        while (outputStream.toString().isEmpty()) {
            // repeat animal adoption attempt until the coin flip picks an animal
            shelter.maybeAdoptAnimals();
        }

        // check log message that was produced
        assertTrue(outputStream.toString().contains("was adopted by a person named"));

        // stats should indicate that one animal is adopted
        assertEquals(
                "1 animal(s) have been adopted. There are 5 animal(s) in the shelter and 0 are available for adoption",
                shelter.getStats());

        // reset output
        System.setOut(System.out);
    }
}