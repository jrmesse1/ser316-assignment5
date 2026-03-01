public class TaskDailyExercise extends Task {
    private static final int DURATION = 30;

    TaskDailyExercise(Animal animal) {
        super(animal);
    }

    @Override
    String getName() {
        return "daily exercise";
    }

    @Override
    int getDuration() {
        return DURATION;
    }

    @Override
    boolean isStillNeeded(Animal animal) {
        return animal.getStatus() != AnimalStatus.ADOPTED;
    }

    @Override
    void onCompletion(Animal animal) {
    }
}
