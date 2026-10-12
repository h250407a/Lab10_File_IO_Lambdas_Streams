// Uses Stream API to filter students scoring above average and sorts them alphabetically.
import java.util.Comparator;
import java.util.List;

record StudentMark(String name, double gpa) {}

public class StudentStreamDemo {
    public static void main(String[] args) {
        List<StudentMark> students = List.of(
            new StudentMark("Anesu", 3.9),
            new StudentMark("Blessing", 3.4),
            new StudentMark("Farai", 2.8),
            new StudentMark("Tariro", 3.7),
            new StudentMark("Kuda", 3.1),
            new StudentMark("Chenai", 3.8)
        );

        // 1. Calculate class average GPA
        double averageGpa = students.stream()
            .mapToDouble(StudentMark::gpa)
            .average()
            .orElse(0.0);

        System.out.printf("Class Average GPA: %.2f%n%n", averageGpa);

        // 2. Filter students with GPA > average and sort names alphabetically
        System.out.println("Students Performing Above Class Average (Alphabetical):");
        students.stream()
            .filter(s -> s.gpa() > averageGpa)
            .sorted(Comparator.comparing(StudentMark::name))
            .forEach(s -> System.out.printf("- %-10s (GPA: %.2f)%n", s.name(), s.gpa()));
    }
}
