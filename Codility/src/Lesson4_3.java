
public class Lesson4_3 {

    public static void main(String[] args) {
        int[] solution = solution(5, new int[]{6, 6, 6, 6, 6, 6, 6});
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
    }

    /**
     * if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X)(counter X is increased by 1),
     * if A[K] = N + 1 then operation K is max counter(all counters are set to the maximum value of any counter).
     *
     * N and M are integers within the range [1..100,000];
     * each element of array A is an integer within the range [1..N + 1].
     *
     * N + 1 일 경우 result 초기화 및 당시 최대 counter 저장
     * - 초기화는 max 가 0보다 큰 경우에만.. 안 그러면 TIMEOUT ERROR 시간초과 발생
     * - 생각해보니 계속 배열 초기화를 해주면 N * M 가 되는군..
     * 1 ≤ X ≤ N 일 경우, X 위치 count 후 최대 counter 와 비교
     * - 최대 카운터와의 비교는 마지막 max counter value 도 포함
     *
     * https://app.codility.com/demo/results/training4JDYWY-E9U/
     */
    public static int[] solution(int N, int[] A) {
        int[] result = new int[N];
        int max = 0;
        int last_max = 0;

        for (int i = 0; i < A.length; i++) {
            int num = A[i];

            if (num == N + 1) {
                last_max = max;
                if (max > 0) result = new int[N];
            } else {
                result[num - 1]++;
                max = max < result[num - 1] + last_max ? result[num - 1] + last_max : max;
            }
        }

        for (int i = 0; i < result.length; i++) {
            result[i] += last_max;
        }

        return result;
    }
}
