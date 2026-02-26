import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class NameGenerator {
    private final List<String> names;
    private int totalNamesUsed = 0;

    public NameGenerator(String nameFile) {
        try {
            // read all names from file and sort randomly, so we don't get the same names between runs
            names = Files.readAllLines(Paths.get(nameFile));
            Collections.shuffle(names);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        String name = names.get(totalNamesUsed);
        totalNamesUsed = (totalNamesUsed + 1) % names.size();
        return name;
    }
}
