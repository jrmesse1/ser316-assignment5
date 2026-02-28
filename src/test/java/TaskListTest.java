import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @BeforeEach
    void setUp() {
        TaskList.resetInstance();
    }

    @Test
    void addTask() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyFeeding(animal);
        TaskList.getInstance().addTask(task);

        // should have 1 task
        assertEquals("Staff have completed 0 tasks. They are working on 0 and 1 are unassigned.", TaskList.getInstance().getStats());
    }

    @Test
    void assignAvailableTask() {
        Animal animal = new Animal(0, "Tester");
        Staff staff = new Staff(0, "Tested", StaffRole.VETERINARIAN);
        Task task = new TaskDailyFeeding(animal);
        TaskList.getInstance().addTask(task);

        // staff should not have a task at this point
        assertNull(TaskList.getInstance().getAssignedTask(staff));

        // task should be unassigned
        assertEquals("Staff have completed 0 tasks. They are working on 0 and 1 are unassigned.", TaskList.getInstance().getStats());

        // the only task in the list should get assigned
        assertEquals(task, TaskList.getInstance().assignAvailableTask(staff));

        // task should be assigned now
        assertEquals("Staff have completed 0 tasks. They are working on 1 and 0 are unassigned.", TaskList.getInstance().getStats());

        assertEquals(task, TaskList.getInstance().getAssignedTask(staff));

        // attempting to assign another task should fail
        assertNull(TaskList.getInstance().assignAvailableTask(staff));

    }
}