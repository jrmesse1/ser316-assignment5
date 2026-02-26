public class Animal {
    private int id;
    private String name;
    private String species;
    private int age;
    private int weight;

    private WeightedCoin makeNoiseCoin = new WeightedCoin(5);

    public Animal(int id, String name, String species, int age, int weight) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.weight = weight;
    }

    public void runCycle() {
        if (makeNoiseCoin.flip()) {
            makeNoise();
        }
    }

    private void makeNoise() {
        String noise = "...";
        if (species == "Dog") {
            noise = "woof";
        }  else if (species == "Cat") {
            noise = "meow";
        }
        System.out.printf("[ANIMAL] %s says %s\n", name, noise);
    }
}
