// Reads a text file and computes line, word, and character counts.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWordCountDemo {
    public static void main(String[] args) {
        Path path = Path.of("sample_text.txt");

        // Prepare sample file for demonstration
        List<String> content = List.of(
            "Computer Science is the study of computation, automation, and information.",
            "Object-Oriented Programming models systems as cooperating objects.",
            "Java is statically typed and runs on the JVM platform."
        );

        try {
            Files.write(path, content);

            // Read lines and calculate metrics
            List<String> lines = Files.readAllLines(path);
            long lineCount = lines.size();
            long wordCount = 0;
            long charCount = 0;

            for (String l : lines) {
                charCount += l.length();
                String trimmed = l.trim();
                if (!trimmed.isEmpty()) {
                    String[] words = trimmed.split("\\s+");
                    wordCount += words.length;
                }
            }

            System.out.println("--- Text File Statistics for " + path + " ---");
            System.out.println("Total Lines     : " + lineCount);
            System.out.println("Total Words     : " + wordCount);
            System.out.println("Total Characters: " + charCount);

        } catch (IOException e) {
            System.err.println("File read failed: " + e.getMessage());
        }
    }
}
