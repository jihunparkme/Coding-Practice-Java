import java.util.HashMap;
import java.util.Map;

public class Lesson9_1 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{23171, 21011, 21123, 21366, 21013, 21367}));
        System.out.println(solution(new int[]{}));
    }

    /**
     * 얻을 수 있는 최대 이익
     * 가장 싸게 사서 가장 비싸게 팔기
     *
     * N is an integer within the range [0..400,000];
     * each element of array A is an integer within the range [0..200,000].
     *
     * https://app.codility.com/demo/results/trainingYNT8K9-N35/
     */
    public static int solution(int[] A) {

        if (A.length == 0) return 0;

        int minPrice = A[0];
        int maxProfit = 0;
        for (int i = 1; i < A.length; i++) {
            int profit = A[i] - minPrice;
            minPrice = Math.min(minPrice, A[i]);
            maxProfit = Math.max(profit, maxProfit);
        }

        return maxProfit;
    }
}
