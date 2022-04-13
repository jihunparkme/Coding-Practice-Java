public class Lesson9_2 {

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{3, 2, -6, 4, 0})); //5
        System.out.println(solution(new int[]{-1, 1})); //5
    }

    /**
     * 최대합 구하기
     *
     * N is an integer within the range [1..1,000,000];
     * each element of array A is an integer within the range [−1,000,000..1,000,000];
     * the result will be an integer within the range [−2,147,483,648..2,147,483,647].
     *
     * https://app.codility.com/demo/results/trainingMEMQTD-J3V/
     * https://app.codility.com/demo/results/trainingTD9DRB-M87/
     */

    public static int solution(int[] A) {

        int size = A.length;
        int[] dp = new int[size];
        int maxSum = A[0];
        dp[0] = A[0];
        for (int i = 1; i < size; i++) {
            if (dp[i-1] + A[i] > A[i]) dp[i] = dp[i-1] + A[i];
            else dp[i] = A[i];
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    public static int solution2(int[] A) {

        int size = A.length;
        int[] dp = new int[size];
        int maxSum = A[0];
        dp[0] = A[0];
        for (int i = 1; i < size; i++) {
            dp[i] = Math.max(0, dp[i-1]) + A[i];
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }
}
