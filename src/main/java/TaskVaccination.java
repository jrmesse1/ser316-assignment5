public class TaskVaccination extends Task {
    private static final int DURATION = 30;

    TaskVaccination(Animal animal) {
        super(animal);
    }

    @Override
    String getName() {
        return "vaccination";
    }

    @Override
    int getDuration() {
        return DURATION;
    }

    @Override
    boolean isStillNeeded(Animal animal) {
        return animal.getStatus() == AnimalStatus.NEEDS_VACCINATION;
    }

    @Override
    void onCompletion(Animal animal) {
        animal.setStatus(AnimalStatus.AVAILABLE);
    }
}
