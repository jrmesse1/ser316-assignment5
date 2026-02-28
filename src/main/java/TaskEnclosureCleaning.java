public class TaskEnclosureCleaning extends Task {
    TaskEnclosureCleaning(Animal animal) {
        super(animal);
    }

    @Override
    String getName() {
        return "enclosure cleaning";
    }

    @Override
    int getDuration() {
        return 100;
    }

    @Override
    boolean isStillNeeded(Animal animal) {
        return animal.getStatus() != AnimalStatus.ADOPTED;
    }

    @Override
    void onCompletion(Animal animal) {
    }
}
