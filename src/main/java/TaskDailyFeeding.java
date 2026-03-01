public class TaskDailyFeeding extends Task {
    private static final int DURATION = 10;

    TaskDailyFeeding(Animal animal) {
        super(animal);
    }

    @Override
    String getName() {
        return "daily feeding";
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
