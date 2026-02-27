public class Animal implements Observer {
    private int id;
    private String name;
    private AnimalStatus status;
    private AnimalSpecies species;
    private int age;
    private int weight;

    private WeightedCoin makeNoiseCoin = new WeightedCoin(5);

    public Animal(int id, String name, AnimalSpecies species, int age, int weight) {
        this.id = id;
        this.name = name;
        this.status = AnimalStatus.INTAKE;
        this.species = species;
        this.age = age;
        this.weight = weight;
    }

    public void update(String event) {
        if (makeNoiseCoin.flip()) makeNoise();
    }

    private void makeNoise() {
        String noise = "...";
        if (species == AnimalSpecies.DOG) {
            noise = "woof";
        }  else if (species == AnimalSpecies.CAT) {
            noise = "meow";
        } else if (species == AnimalSpecies.BIRD) {
            noise = "chirp";
        } else if (species == AnimalSpecies.SNAKE) {
            noise = "hissss";
        }
        System.out.printf("[ANIMAL] %s says %s\n", name, noise);
    }
}
