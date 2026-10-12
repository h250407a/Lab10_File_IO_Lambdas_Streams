// Demonstrates modern Java NIO text file reading, writing, and exception handling.
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileDemo {
    public static void main(String[] args) {
        Path path = Path.of("students.txt");

        // ---- WRITE ----
        List<String> lines = List.of(
            "Tariro,101,3.8",
            "Farai,102,3.1",
            "Rudo,103,3.9"
        );

        try {
            Files.write(path, lines); // Creates or overwrites file
            Files.writeString(path, "Kuda,104,3.2\n", StandardOpenOption.APPEND);
            System.out.println("File written: " + Files.size(path) + " bytes");
        } catch (IOException e) {
            System.out.println("Write failed: " + e.getMessage());
            return;
        }

        // ---- READ (streaming buffered reader) ----
        try (var reader = Files.newBufferedReader(path)) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.printf("%-8s roll=%s gpa=%s%n", parts[0], parts[1], parts[2]);
                count++;
            }
            System.out.println(count + " records read");
        } catch (IOException e) {
            System.out.println("Read failed: " + e.getMessage());
        }

        // ---- MISSING FILE ERROR HANDLING ----
        try {
            Files.readAllLines(Path.of("does-not-exist.txt"));
        } catch (NoSuchFileException e) {
            System.out.println("Missing file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error");
        }
    }
}
