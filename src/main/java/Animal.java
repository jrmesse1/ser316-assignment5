import java.util.Random;

public class Animal implements Observer {
    private int id;
    private String name;
    private AnimalStatus status;
    private AnimalSpecies species;
    private int age;
    private int weight;

    private WeightedCoin makeNoiseCoin = new WeightedCoin(5);

    public Animal(int id, String name) {
        this.id = id;
        this.name = name;

        // new animals start in intake
        this.status = AnimalStatus.INTAKE;

        // get random species
        Random random = new Random();
        int i = random.nextInt(AnimalSpecies.values().length);
        this.species = AnimalSpecies.values()[i];

        // get random age
        this.age = random.nextInt(10) + 1;

        // get random weight
        if (species == AnimalSpecies.BIRD) {
            this.weight = 0;
        } else if (species == AnimalSpecies.SNAKE) {
            this.weight = random.nextInt(3) + 1;
        } else if (species == AnimalSpecies.DOG) {
            this.weight = random.nextInt(65) + 5;
        } else {
            // cat or rabbit
            this.weight = random.nextInt(8) + 1;
        }
    }

    public String getSpecies() {
        return species.toString().toLowerCase();
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
