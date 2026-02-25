public class Veterinarian extends Staff {


    public Veterinarian(String name) {
        super(name);
        setRole(Role.veterinarian);
    }

    public void performIntakeExam(Animal animal) {
        // update health conditions
        // administer medications
        // perform age assessment if needed
    }
    public void performExam(Animal animal) {
        // general health assessment
        // administer medications if needed
    }

}
