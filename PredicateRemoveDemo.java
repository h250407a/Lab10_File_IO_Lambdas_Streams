// Filters out all even integers from a collection using Predicate and removeIf.
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateRemoveDemo {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        System.out.println("Original numbers list: " + numbers);

        // Predicate defining the condition for even numbers
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // removeIf evaluates the predicate and safely removes matches without ConcurrentModificationException
        numbers.removeIf(isEven);

        System.out.println("List after removing even numbers: " + numbers);
    }
}
