import java.util.Random;

public class Animal implements Observer {
    private final int id;
    private final String name;
    private final AnimalSpecies species;
    private final int age;
    private final double weight;
    private final WeightedCoin makeNoiseCoin = new WeightedCoin(0.0013);
    private final AnimalHealth health;
    private AnimalStatus status;

    private static final int MAX_AGE = 10;
    private static final int MIN_AGE = 1;

    private static final double MAX_BIRD_WEIGHT = 0.5;
    private static final double MAX_CAT_WEIGHT = 9;
    private static final double MAX_DOG_WEIGHT = 67;
    private static final double MAX_SNAKE_WEIGHT = 3;
    private static final double MAX_RABBIT_WEIGHT = 8;
    private static final double MIN_BIRD_WEIGHT = 0.1;
    private static final double MIN_CAT_WEIGHT = 1;
    private static final double MIN_DOG_WEIGHT = 1;
    private static final double MIN_SNAKE_WEIGHT = 1;
    private static final double MIN_RABBIT_WEIGHT = 1;


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
        this.age = random.nextInt(MAX_AGE) + MIN_AGE;

        double maxWeight, minWeight;
        if (species == AnimalSpecies.BIRD) {
            maxWeight = MAX_BIRD_WEIGHT;
            minWeight = MIN_BIRD_WEIGHT;
        } else if (species == AnimalSpecies.SNAKE) {
            maxWeight = MAX_SNAKE_WEIGHT;
            minWeight = MIN_SNAKE_WEIGHT;
        } else if (species == AnimalSpecies.DOG) {
            maxWeight = MAX_DOG_WEIGHT;
            minWeight = MIN_DOG_WEIGHT;
        } else if (species == AnimalSpecies.CAT) {
            maxWeight = MAX_CAT_WEIGHT;
            minWeight = MIN_CAT_WEIGHT;
        } else {
            // rabbit or some other species
            maxWeight = MAX_RABBIT_WEIGHT;
            minWeight = MIN_RABBIT_WEIGHT;
        }

        // get random weight within bounds
        this.weight = random.nextDouble(maxWeight) + minWeight;
    }

    private static AnimalSpecies getRandomSpecies() {
        Random random = new Random();
        int i = random.nextInt(AnimalSpecies.values().length);
        return AnimalSpecies.values()[i];
    }

    public AnimalStatus getStatus() {
        return status;
    }

    /**
     * Update the adoption-related status of the Animal. This will print a log message when an Animal is adoptable.
     *
     * @param status The new status that the animal should have.
     */
    public void setStatus(AnimalStatus status) {
        this.status = status;
        if (status == AnimalStatus.AVAILABLE) Logger.log("ANIMAL", String.format("%s is available for adoption", name));

        // adopted animals no longer perform actions within the shelter
        if (status == AnimalStatus.ADOPTED) Clock.getInstance().detach(this);
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species.toString().toLowerCase();
    }

    /**
     * Process an event from a Subject that this Observer is attached to.
     * @param event String describing the source of the event.
     */
    @Override
    public void update(String event) {
        if (event.equals("minute") && makeNoiseCoin.flip())
            Logger.log("ANIMAL", String.format("%s says %s", name, getNoise()));
    }

    /**
     * Determine which noise the animal should make, based on its species.
     *
     * @return String representing an animal noise.
     */
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

    public double getWeight() {
        return this.weight;
    }

    public int getAge() {
        return this.age;
    }

    public String getHealth() {
        return health.toString().toLowerCase();
    }
}
