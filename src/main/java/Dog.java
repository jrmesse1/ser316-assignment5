public class Dog extends Animal {

    public Dog(String name, String ID, String species, int age, int weight) {
        super(name, ID, species, age, weight);
    }
    public void eat() {
        System.out.println("Eating...");
    }
    public void sleep() {}

    public void speak() {
        System.out.println("BARK!\tWOOF! WOOF!");
    }

}
