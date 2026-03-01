import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalTest {

    @Test
    void getSpecies() {
        Animal animal = new Animal(0, "Tester", AnimalSpecies.BIRD);
        assertEquals("bird", animal.getSpecies());
    }

    @Test
    void getNoise() {
        Animal animal = new Animal(0, "Tester", AnimalSpecies.BIRD);
        assertEquals("chirp", animal.getNoise());
        animal = new Animal(0, "Tester", AnimalSpecies.CAT);
        assertEquals("meow", animal.getNoise());
        animal = new Animal(0, "Tester", AnimalSpecies.DOG);
        assertEquals("woof", animal.getNoise());
        animal = new Animal(0, "Tester", AnimalSpecies.SNAKE);
        assertEquals("hissss", animal.getNoise());
        animal = new Animal(0, "Tester", AnimalSpecies.RABBIT);
        assertEquals("munch munch", animal.getNoise());
    }
}