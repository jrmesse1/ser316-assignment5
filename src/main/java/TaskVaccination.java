public class TaskVaccination extends Task {
    TaskVaccination(Animal animal) {
        super(animal);
    }

    @Override
    String getName() {
        return "vaccination";
    }

    @Override
    int getDuration() {
        return 30;
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
