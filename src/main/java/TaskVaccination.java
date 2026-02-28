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
    void onCompletion(Animal animal) {
        animal.setStatus(AnimalStatus.AVAILABLE);
    }
}
