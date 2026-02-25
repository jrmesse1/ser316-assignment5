import java.util.ArrayList;

public abstract class Animal {
    private String name;
    private final String identification;
    private final String species;
    private int age;
    private int weight;
    private ArrayList<String> healthConditions = new ArrayList<>();

    public Animal(String name, String ID, String species, int age, int weight) throws NullPointerException {
        this.name = name;
        this.identification = ID;
        this.species = species;

        if (age >= 0 && age < 200)
            this.age = age;
        else
            throw new NullPointerException("Invalid age input. Age must be non-negative and less than 200 years");

        if (weight > 0)
            this.weight = weight;
        else
            throw new NumberFormatException("Invalid weight input. Weight must be a positive value");
    }

    public int getWeight(){return weight;}
    public void setWeight(int weight){this.weight = weight;}

    public String getSpecies(){return species;}
    public String getIdentification(){return identification;}

    public void setAge(int age){this.age = age;}
    public int getAge(){return age;}

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

    public void addHealthConditions(String newHealthCondition) {
        this.healthConditions.add(newHealthCondition);
    }
    public ArrayList<String> getHealthConditions(){return new ArrayList<>(healthConditions);}


}
