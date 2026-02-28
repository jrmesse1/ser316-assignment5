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
    void onCompletion(Animal animal) {
    }
}
