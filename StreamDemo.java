// Demonstrates functional interfaces, method references, and declarative Stream processing.
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

record Employee(String name, String dept, double salary) {}

public class StreamDemo {
    public static void main(String[] args) {
        // ---- 1. Lambdas and functional interfaces ----
        Predicate<String> longWord = s -> s.length() > 4;
        Function<String, Integer> length = String::length; // Method reference
        Consumer<String> shout = s -> System.out.println(s.toUpperCase() + "!");
        Supplier<List<String>> maker = ArrayList::new;

        System.out.println(longWord.test("lambda") + " " + length.apply("stream"));
        shout.accept("hello");
        List<String> empty = maker.get();

        // ---- 2. Streams over collection data ----
        List<Employee> staff = List.of(
            new Employee("Ada", "IT", 4200),
            new Employee("Ben", "IT", 3800),
            new Employee("Cara", "HR", 3000),
            new Employee("Dan", "Finance", 5000),
            new Employee("Eve", "HR", 3400)
        );

        // Filter -> Map -> Collect
        List<String> highEarners = staff.stream()
            .filter(e -> e.salary() >= 3800)
            .map(Employee::name)
            .collect(Collectors.toList());
        System.out.println("High earners: " + highEarners);

        // Sorted with comparator and limit
        staff.stream()
            .sorted(Comparator.comparingDouble(Employee::salary).reversed())
            .limit(2)
            .forEach(e -> System.out.println("Top: " + e.name()));

        // Reduce / numeric summaries
        double total = staff.stream().mapToDouble(Employee::salary).sum();
        double average = staff.stream().mapToDouble(Employee::salary).average().orElse(0);
        System.out.printf("Total %.0f, average %.1f%n", total, average);

        // GroupingBy department
        Map<String, Long> countByDept = staff.stream()
            .collect(Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.counting()));
        System.out.println("Head count: " + countByDept);

        Map<String, Double> avgByDept = staff.stream()
            .collect(Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.averagingDouble(Employee::salary)));
        System.out.println("Average pay: " + avgByDept);

        // anyMatch and joining
        System.out.println("Anyone in Finance? " + staff.stream().anyMatch(e -> e.dept().equals("Finance")));
        System.out.println(staff.stream().map(Employee::name).collect(Collectors.joining(", ", "[", "]")));
    }
}
