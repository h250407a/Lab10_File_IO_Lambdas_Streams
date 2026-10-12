// Groups learners by grade band with Collectors.groupingBy and writes separate report files.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

record StudentProfile(String name, int roll, double gpa) {
    String getGradeBand() {
        if (gpa >= 3.6) return "Band_A";
        if (gpa >= 3.0) return "Band_B";
        return "Band_C";
    }

    static StudentProfile fromCsv(String line) {
        String[] parts = line.split(",");
        return new StudentProfile(parts[0].trim(), Integer.parseInt(parts[1].trim()), Double.parseDouble(parts[2].trim()));
    }
}

public class GradeReportDemo {
    public static void main(String[] args) throws IOException {
        Path csvPath = Path.of("learners_data.csv");

        // Write initial CSV records
        List<String> rawData = List.of(
            "Anesu,101,3.9",
            "Chenai,102,3.7",
            "Kuda,103,3.2",
            "Farai,104,2.9",
            "Tariro,105,3.8",
            "Nyasha,106,3.4",
            "Tendai,107,2.5"
        );
        Files.write(csvPath, rawData);

        // Read and parse learners
        List<StudentProfile> learners = Files.readAllLines(csvPath).stream()
            .filter(line -> !line.isBlank())
            .map(StudentProfile::fromCsv)
            .collect(Collectors.toList());

        // Group students by grade band using Collectors.groupingBy
        Map<String, List<StudentProfile>> grouped = learners.stream()
            .collect(Collectors.groupingBy(StudentProfile::getGradeBand, TreeMap::new, Collectors.toList()));

        // Write report files for each band
        System.out.println("--- Generating Grade Band Reports ---");
        for (Map.Entry<String, List<StudentProfile>> entry : grouped.entrySet()) {
            String bandName = entry.getKey();
            List<StudentProfile> groupList = entry.getValue();

            Path reportFile = Path.of("report_" + bandName.toLowerCase() + ".txt");
            List<String> reportContent = new ArrayList<>();
            reportContent.add("=== Academic Report: " + bandName + " ===");
            for (StudentProfile sp : groupList) {
                reportContent.add(String.format("Roll: %-4d | Name: %-10s | GPA: %.2f", sp.roll(), sp.name(), sp.gpa()));
            }

            Files.write(reportFile, reportContent);
            System.out.println("Generated " + reportFile + " with " + groupList.size() + " student records.");
        }
    }
}
