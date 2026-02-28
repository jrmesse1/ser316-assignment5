import java.util.Random;

public class Animal implements Observer {
    private final int id;
    private final String name;
    private final AnimalSpecies species;
    private final int age;
    private final int weight;
    private final WeightedCoin makeNoiseCoin = new WeightedCoin(0.0013);
    private final AnimalHealth health;
    private AnimalStatus status;

    Animal(int id, String name) {
        this(id, name, getRandomSpecies());
    }

    Animal(int id, String name, AnimalSpecies species) {
        // connect observer to global clock
        Clock.getInstance().attach(this);

        this.id = id;
        this.name = name;
        this.species = species;

        // new animals start in intake
        this.status = AnimalStatus.INTAKE;

        // get random health
        Random random = new Random();
        int i = random.nextInt(AnimalHealth.values().length);
        this.health = AnimalHealth.values()[i];

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

    private static AnimalSpecies getRandomSpecies() {
        Random random = new Random();
        int i = random.nextInt(AnimalSpecies.values().length);
        return AnimalSpecies.values()[i];
    }

    public AnimalStatus getStatus() {
        return status;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
        if (status == AnimalStatus.AVAILABLE) Logger.log("ANIMAL", String.format("%s is available for adoption", name));
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species.toString().toLowerCase();
    }

    public void update(String event) {
        if (event.equals("minute") && status != AnimalStatus.ADOPTED && makeNoiseCoin.flip())
            Logger.log("ANIMAL", String.format("%s says %s", name, getNoise()));
    }

    public String getNoise() {
        if (species == AnimalSpecies.DOG) {
            return "woof";
        } else if (species == AnimalSpecies.CAT) {
            return "meow";
        } else if (species == AnimalSpecies.BIRD) {
            return "chirp";
        } else if (species == AnimalSpecies.SNAKE) {
            return "hissss";
        } else {
            // rabbit or unspecified animal. anything can munch
            return "munch munch";
        }
    }

    public int getWeight() {
        return this.weight;
    }

    public int getAge() {
        return this.age;
    }

    public String getHealth() {
        return health.toString().toLowerCase();
    }
}
