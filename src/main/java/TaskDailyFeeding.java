public class TaskDailyFeeding extends Task {
    TaskDailyFeeding(Animal animal) {
        super(animal);
    }

    @Override
    String getName() {
        return "daily feeding";
    }

    @Override
    int getDuration() {
        return 10;
    }

    @Override
    boolean isStillNeeded(Animal animal) {
        return animal.getStatus() != AnimalStatus.ADOPTED;
    }

    @Override
    void onCompletion(Animal animal) {
    }
}
