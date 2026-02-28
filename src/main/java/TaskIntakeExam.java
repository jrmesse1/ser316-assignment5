public class TaskIntakeExam extends Task {
    TaskIntakeExam(Animal animal) {
        super(animal);
    }

    @Override
    String getName() {
        return "intake exam";
    }

    @Override
    int getDuration() {
        return 120;
    }

    @Override
    void onCompletion(Animal animal) {
        animal.setStatus(AnimalStatus.NEEDS_VACCINATION);
    }
}
