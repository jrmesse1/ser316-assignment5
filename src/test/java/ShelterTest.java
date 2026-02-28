import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelterTest {
    @Test
    void shelterConstructor() {
        Shelter shelter = new Shelter();

        // at startup there should be 5 animals
        assertEquals("0 animal(s) have been adopted. There are 5 animal(s) in the shelter and 0 are available for adoption", shelter.getStats());
    }

    @Test
    void addDailyTaskAssignment() {
        Shelter shelter = new Shelter();
        TaskList tasks = TaskList.getInstance();
        assertEquals("Staff have completed 0 tasks. They are working on 0 and 10 are unassigned.", tasks.getStats());

        // should create 3 tasks per animal, adding 15 tasks to the list
        shelter.addDailyTaskAssignment();
        assertEquals("Staff have completed 0 tasks. They are working on 0 and 25 are unassigned.", tasks.getStats());
    }
}