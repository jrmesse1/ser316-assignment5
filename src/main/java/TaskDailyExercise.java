public class TaskDailyExercise extends Task {
    TaskDailyExercise(Animal animal) {
        super(animal);
    }

    @Override
    String getName() {
        return "daily exercise";
    }

    @Override
    int getDuration() {
        return 30;
    }

    @Override
    boolean isStillNeeded(Animal animal) {
        return animal.getStatus() != AnimalStatus.ADOPTED;
    }

    @Override
    void onCompletion(Animal animal) {
    }
}
