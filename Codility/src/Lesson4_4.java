
public class Lesson4_4 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1*-1, 3*-1}));
    }

    /**
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [−1,000,000..1,000,000].
     *
     * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
     * Given A = [1, 2, 3], the function should return 4.
     * Given A = [−1, −3], the function should return 1.
     *
     * https://app.codility.com/demo/results/trainingPAXPZM-7GQ/
     */
    public static int solution(int[] A) {

        boolean[] checked = new boolean[1000001];

        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) checked[A[i]] = true;
        }

        for (int i = 1; i < 1000000; i++) {
            if (!checked[i]) return i;
        }

        return 1;
    }
}
