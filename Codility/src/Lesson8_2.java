import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class Lesson8_2 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{4,3,4,4,4,2}));
//        System.out.println(solution(new int[]{4, 4, 2, 5, 3, 4, 4, 4}));
    }

    /**
     * S 기준의 두 시퀀스에서 리더(중복이 가장 많은 수)의 개수가 시퀀스 크기 절반보다 큰 경우
     * 0 ≤ S < N − 1
     *
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
     *
     * https://app.codility.com/demo/results/trainingSQAFQ3-SD5/
     */
    public static int solution(int[] A) {

        int N = A.length;
        int maxNum = -1;
        int maxCnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int cnt = map.getOrDefault(A[i], 0) + 1;
            map.put(A[i], cnt);

            if (maxCnt < cnt) {
                maxCnt = cnt;
                maxNum = A[i];
            }
        }

        int[] cntArr = new int[N];
        cntArr[0] = A[0] == maxNum ?  1 : 0;
        for (int i = 1; i < N; i++) {
            int cnt = cntArr[i-1];
            if (A[i] == maxNum) cnt++;
            cntArr[i] = cnt;
        }

        int result = 0;
        for (int S = 0; S < N; S++) {
            if (cntArr[S] > ((S + 1) / 2.0) &&
                    (cntArr[N - 1] - cntArr[S]) > ((N - S - 1) / 2.0)) {
                result++;
            }

        }

        return result;
    }
}
