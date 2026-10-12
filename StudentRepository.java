// Persists and reloads Learner records to/from CSV using java.nio.file.
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

record Learner(String name, int roll, double gpa) {
    String toCsv() {
        return name + "," + roll + "," + gpa;
    }

    // Static factory parsing a comma-separated line back into a Learner record
    static Learner fromCsv(String line) {
        String[] p = line.split(",");
        return new Learner(p[0].trim(), Integer.parseInt(p[1].trim()), Double.parseDouble(p[2].trim()));
    }
}

public class StudentRepository {
    private final Path file;

    public StudentRepository(String fileName) {
        this.file = Path.of(fileName);
    }

    public void saveAll(List<Learner> learners) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Learner l : learners) {
            lines.add(l.toCsv());
        }
        Files.write(file, lines);
    }

    public List<Learner> loadAll() throws IOException {
        List<Learner> result = new ArrayList<>();
        if (!Files.exists(file)) {
            return result; // First run: file does not exist yet
        }
        for (String line : Files.readAllLines(file)) {
            if (!line.isBlank()) {
                result.add(Learner.fromCsv(line));
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        StudentRepository repo = new StudentRepository("learners.csv");
        repo.saveAll(List.of(
            new Learner("Ada", 1, 3.9),
            new Learner("Kuda", 2, 3.2),
            new Learner("Anesu", 3, 3.85)
        ));

        System.out.println("--- Loaded Records from learners.csv ---");
        for (Learner l : repo.loadAll()) {
            System.out.println(l);
        }
    }
}
