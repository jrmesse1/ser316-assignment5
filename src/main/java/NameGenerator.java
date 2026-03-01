import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NameGenerator {
    private List<String> names;
    private int totalNamesUsed = 0;

    /**
     * Create a new NameGenerator from a list of names stored in a file. File should not contain duplicate names.
     *
     * @param nameFile The file path to load names from, relative to the current directory.
     */
    public NameGenerator(String nameFile) {
        try {
            // read all names from file and sort randomly, so we don't get the same names between runs
            names = Files.readAllLines(Paths.get(nameFile));
        } catch (IOException e) {
            System.err.println("Unable to read name file " + nameFile);
            names = new ArrayList<>();
        }
        Collections.shuffle(names);
    }

    /**
     * Get a random name from the nameFile.
     *
     * @return String containing a name.
     */
    public String getName() {
        String name = names.get(totalNamesUsed);
        totalNamesUsed = (totalNamesUsed + 1) % names.size();
        return name;
    }
}
