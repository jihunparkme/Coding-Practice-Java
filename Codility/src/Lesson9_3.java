public class Lesson9_3 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,2,6,-1,4,5,-1,2}));
        System.out.println(solution(new int[]{1, 2, 3, 4}));
    }

    /**
     * find the maximal sum of any double slice.
     * 양방향 부분합
     *
     * The sum of double slice (X, Y, Z) is the total of
     * A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
     *
     * N is an integer within the range [3..100,000];
     * each element of array A is an integer within the range [−10,000..10,000].
     *
     * https://app.codility.com/demo/results/trainingQCP92S-42F/
     */

    public static int solution(int[] A) {

        int N = A.length;
        int[] prefixSum = new int[N];
        int[] suffixSum = new int[N];
        for (int i = 1; i < N - 1; i++) {
            prefixSum[i] = Math.max(0, prefixSum[i - 1] + A[i]);
        }
        for (int i = N - 2; i > 0; i--) {
            suffixSum[i] = Math.max(0, suffixSum[i + 1] + A[i]);
        }

        int result = 0;
        for (int i = 1; i < N - 1; i++) {
            result = Math.max(result, prefixSum[i - 1] + suffixSum[i + 1]);
        }

        return result;
    }
}
