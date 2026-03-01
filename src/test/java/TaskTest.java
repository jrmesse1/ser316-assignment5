import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private static final int DURATION_DAILY_EXERCISE = 30;
    private static final int DURATION_DAILY_FEEDING = 10;
    private static final int DURATION_ENCLOSURE_CLEANING = 100;
    private static final int DURATION_INTAKE_EXAM = 120;
    private static final int DURATION_VACCINATION = 30;
    private static final double MINUTES_IN_HOUR = 60.0;

    @Test
    void getAndSetAssignee() {
        Animal animal = new Animal(0, "Tester");
        Staff staff = new Staff(0, "Tested", StaffRole.VETERINARIAN);
        Task task = new TaskDailyFeeding(animal);
        task.setAssignee(staff);
        assertEquals(staff, task.getAssignee());
        task.setAssignee(null);
        assertNull(task.getAssignee());
    }

    @Test
    void decrementTimeRemaining() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyFeeding(animal);
        for (int i = 0; i < task.getDuration(); i++) {
            // task takes number of decrements equal to duration to complete
            assertFalse(task.isComplete());
            task.decrementTimeRemaining();
        }
        assertTrue(task.isComplete());
    }

    @Test
    void isDiscardedTaskExercise() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyExercise(animal);
        assertFalse(task.isDiscarded());
        animal.setStatus(AnimalStatus.ADOPTED);
        assertFalse(task.isStillNeeded(animal));
        task.discardIfNotNeeded();
        assertTrue(task.isDiscarded());
    }

    @Test
    void isDiscardedTaskFeeding() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyFeeding(animal);
        assertFalse(task.isDiscarded());
        animal.setStatus(AnimalStatus.ADOPTED);
        assertFalse(task.isStillNeeded(animal));
        task.discardIfNotNeeded();
        assertTrue(task.isDiscarded());
    }

    @Test
    void isDiscardedTaskEnclosureCleaning() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskEnclosureCleaning(animal);
        assertFalse(task.isDiscarded());
        animal.setStatus(AnimalStatus.ADOPTED);
        assertFalse(task.isStillNeeded(animal));
        task.discardIfNotNeeded();
        assertTrue(task.isDiscarded());
    }

    @Test
    void isDiscardedTaskIntakeExam() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskIntakeExam(animal);
        assertFalse(task.isDiscarded());
        animal.setStatus(AnimalStatus.NEEDS_VACCINATION);
        assertFalse(task.isStillNeeded(animal));
        task.discardIfNotNeeded();
        assertTrue(task.isDiscarded());
    }


    @Test
    void isDiscardedTaskVaccination() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskVaccination(animal);
        assertFalse(task.isDiscarded());
        animal.setStatus(AnimalStatus.AVAILABLE);
        assertFalse(task.isStillNeeded(animal));
        task.discardIfNotNeeded();
        assertTrue(task.isDiscarded());
    }

    @Test
    void getTimeWorkedHours() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyFeeding(animal);
        assertEquals(0.0, task.getTimeWorkedHours());
        task.decrementTimeRemaining();
        assertEquals(1.0 / MINUTES_IN_HOUR, task.getTimeWorkedHours());
    }

    @Test
    void getName() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyExercise(animal);
        assertEquals("daily exercise", task.getName());
        task = new TaskDailyFeeding(animal);
        assertEquals("daily feeding", task.getName());
        task = new TaskEnclosureCleaning(animal);
        assertEquals("enclosure cleaning", task.getName());
        task = new TaskIntakeExam(animal);
        assertEquals("intake exam", task.getName());
        task = new TaskVaccination(animal);
        assertEquals("vaccination", task.getName());
    }

    @Test
    void getDuration() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyExercise(animal);
        assertEquals(DURATION_DAILY_EXERCISE, task.getDuration());
        task = new TaskDailyFeeding(animal);
        assertEquals(DURATION_DAILY_FEEDING, task.getDuration());
        task = new TaskEnclosureCleaning(animal);
        assertEquals(DURATION_ENCLOSURE_CLEANING, task.getDuration());
        task = new TaskIntakeExam(animal);
        assertEquals(DURATION_INTAKE_EXAM, task.getDuration());
        task = new TaskVaccination(animal);
        assertEquals(DURATION_VACCINATION, task.getDuration());
    }

    @Test
    void completeTaskExercise() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyExercise(animal);
        while (!task.isComplete()) task.decrementTimeRemaining();
        assertTrue(task.isComplete());
    }

    @Test
    void completeTaskFeeding() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyFeeding(animal);
        while (!task.isComplete()) task.decrementTimeRemaining();
        assertTrue(task.isComplete());
    }

    @Test
    void completeTaskEnclosureCleaning() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskEnclosureCleaning(animal);
        while (!task.isComplete()) task.decrementTimeRemaining();
        assertTrue(task.isComplete());
    }

    @Test
    void completeTaskIntakeExam() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskIntakeExam(animal);
        while (!task.isComplete()) task.decrementTimeRemaining();
        assertTrue(task.isComplete());

        // animal status should change in onComplete fn
        assertEquals(AnimalStatus.NEEDS_VACCINATION, animal.getStatus());
    }


    @Test
    void completeTaskVaccination() {
        Animal animal = new Animal(0, "Tester");
        animal.setStatus(AnimalStatus.NEEDS_VACCINATION);
        Task task = new TaskVaccination(animal);
        while (!task.isComplete()) task.decrementTimeRemaining();
        assertTrue(task.isComplete());

        // animal status should change in onComplete fn
        assertEquals(AnimalStatus.AVAILABLE, animal.getStatus());
    }

    @Test
    void testToString() {
        Animal animal = new Animal(0, "Tester");
        Task task = new TaskDailyExercise(animal);
        assertEquals("daily exercise for Tester", task.toString());
        task = new TaskDailyFeeding(animal);
        assertEquals("daily feeding for Tester", task.toString());
        task = new TaskEnclosureCleaning(animal);
        assertEquals("enclosure cleaning for Tester", task.toString());
        task = new TaskIntakeExam(animal);
        assertEquals("intake exam for Tester", task.toString());
        task = new TaskVaccination(animal);
        assertEquals("vaccination for Tester", task.toString());
    }
}