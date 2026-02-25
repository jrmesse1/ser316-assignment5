public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        // setup staff
        // setup initial animals (5)

        // set starting time - monday at 00:00

        // simulation loop - each iteration moves forward 1 hour
        for (int i = 0; i < 7 * 24; i++) {
            loop_iteration();
        }
    }

    public void loop_iteration() {
        // print out current time for this loop iteration

        // determine if shelter is closed or not based on time
        // can events still happen in off-hours?

        // beginning of loop there is a chance for random events to happen:
        // - new task added to task list
        // - animal health change
        // - new animal added
        // - adoption

        // staff and animals respond to those events through event listener

        // as the events are responded to, log messages are produced
    }
}
