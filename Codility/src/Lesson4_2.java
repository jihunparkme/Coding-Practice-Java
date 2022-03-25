import java.util.HashSet;
import java.util.Set;

public class Lesson4_2 {

    public static void main(String[] args) {
        System.out.println("Answer : " + solution(new int[]{2,3}));
    }

    /**
     * 배열 A가 순열인지 확인
     * 배열 A가 순열이면 1을 반환하고 그렇지 않으면 0을 반환
     *
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [1..1,000,000,000].
     *
     * https://app.codility.com/demo/results/trainingG5DRD7-3CZ/
     * - 1 ~ N 을 하나씩만 포함해야 하므로 Set 자료형을 사용
     * - N이 Set의 크기와 같고, 배열 A의 크기와 같다면 순열.
     */
    public static int solution(int[] A) {

        int last = 0;
        Set<Integer> nums = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            nums.add(A[i]);
            last = last < A[i] ? A[i] : last;
        }

        if (nums.size() == last && last == A.length) return 1;
        else return 0;
    }
}
