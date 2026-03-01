import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NameGeneratorTest {

    @Test
    void getName() {
        NameGenerator nameGenerator = new NameGenerator("staff_names.txt");
        String staffName1 = nameGenerator.getName();
        String staffName2 = nameGenerator.getName();

        // names should not repeat
        assertNotEquals(staffName1, staffName2);

        // names should be non-empty strings
        assert staffName1.length() > 1;
        assert staffName2.length() > 1;
    }
}