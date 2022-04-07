import java.util.HashSet;
import java.util.Set;

public class Lesson6_1 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 1, 1, 2, 3, 1}));
    }

    /**
     * https://app.codility.com/demo/results/trainingVE4MNV-C9U/
     */
    public static int solution(int[] A) {
        Set<Integer> distinct = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            distinct.add(A[i]);
        }

        return distinct.size();
    }
}
