public interface Observer {
    /**
     * Process an event from a Subject that this Observer is attached to.
     * @param event String describing the source of the event.
     */
    void update(String event);
}
