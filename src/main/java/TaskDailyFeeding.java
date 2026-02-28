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
    void onCompletion(Animal animal) {
    }
}
