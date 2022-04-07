import java.util.Arrays;

public class Lesson6_3 {

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{10,2,5,1,8,20}));
        System.out.println(solution(new int[]{10,50,5,1}));
    }

    /**
     * A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
     *
     * A[P] + A[Q] > A[R],
     * A[Q] + A[R] > A[P],
     * A[R] + A[P] > A[Q].
     *
     * returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.
     *
     * N is an integer within the range [0..100,000];
     * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
     *
     * https://app.codility.com/demo/results/trainingKQD9XK-P3S/
     */

    public static int solution(int[] A) {
        Arrays.sort(A);

        for (int i = 2; i < A.length; i++) {
            if (isTriangular(A[i-2], A[i-1], A[i])) {
                return 1;
            }
        }

        return 0;
    }

    private static boolean isTriangular(long p, long q, long r) {

        if (p + q > r) {
            return true;
        }

        return false;
    }
}
