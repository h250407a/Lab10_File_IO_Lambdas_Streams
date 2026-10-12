# Lab 10: File I/O, Lambdas, and Streams

**Student Details:**
- **Name:** Anesu B Kahanda
- **Registration Number:** H250407A
- **Department:** Computer Science
- **Course:** Object-Oriented Programming in Java

---

## Overview
This laboratory examines file persistence with `java.nio.file` and declarative processing with lambdas and the Stream API:
1. File operations: writing, reading, appending, and robust `IOException` / `NoSuchFileException` handling.
2. Object serialization and deserialization using CSV (Comma Separated Values) format.
3. Functional interfaces: `Predicate<T>`, `Function<T, R>`, `Consumer<T>`, `Supplier<T>`, and method references.
4. Stream processing pipelines: `filter`, `map`, `sorted`, `collect`, `mapToDouble`, `average`, `summaryStatistics`.
5. Grouping and aggregation with `Collectors.groupingBy()`.

---

## Files in this Directory

| File Name | Description |
|---|---|
| `FileDemo.java` | Program 10.1: Writing and streaming text lines with `Files.newBufferedReader` and missing file handling. |
| `StudentRepository.java` | Program 10.2: Encapsulated CSV persistence repository saving and restoring `Learner` records. |
| `StreamDemo.java` | Program 10.3: Demonstrates core lambdas, salary filtering, department aggregation, and joining strings. |
| `SaveLinesDemo.java` | Exercise 1: Saves lines to a file on disk and reads them back. |
| `PredicateRemoveDemo.java` | Exercise 2: Employs `Predicate<Integer>` and `List.removeIf` to eliminate even numbers safely. |
| `FileWordCountDemo.java` | Exercise 3: Computes total line, word, and character metrics from a text file. |
| `StudentStreamDemo.java` | Exercise 4: Filters learners performing above the cohort mean GPA and sorts names alphabetically. |
| `GradeReportDemo.java` | Exercise 5: Reads CSV data, partitions records into grade bands with `Collectors.groupingBy`, and exports discrete report files. |

---

## How to Compile and Run

To compile all files:
```bash
javac *.java
```

To run individual programs:
```bash
java FileDemo
java StudentRepository
java StreamDemo
java SaveLinesDemo
java PredicateRemoveDemo
java FileWordCountDemo
java StudentStreamDemo
java GradeReportDemo
```

---

## Expected Output Samples

### `StreamDemo.java`
```
true 6
HELLO!
High earners: [Ada, Ben, Dan]
Top: Dan
Top: Ada
Total 19400, average 3880.0
Head count: {Finance=1, HR=2, IT=2}
Average pay: {Finance=5000.0, HR=3200.0, IT=4000.0}
Anyone in Finance? true
[Ada, Ben, Cara, Dan, Eve]
```

### `StudentStreamDemo.java`
```
Class Average GPA: 3.45

Students Performing Above Class Average (Alphabetical):
- Anesu      (GPA: 3.90)
- Chenai     (GPA: 3.80)
- Tariro     (GPA: 3.70)
```
