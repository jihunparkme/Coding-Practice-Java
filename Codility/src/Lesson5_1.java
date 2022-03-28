
public class Lesson5_1 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0,1,0,1,1}));
    }

    /**
     * 0은 동쪽으로 여행하는 자동차, 1은 서쪽으로 여행하는 자동차, 지나가는 자동차를 계산
     * P가 동쪽으로 여행하고 Q가 서쪽으로 여행할 때 0 ≤ P < Q < N인 한 쌍의 자동차(P, Q)가 지나가고 있다고 말합니다.
     * 지나가는 자동차 쌍의 수가 1,000,000,000을 초과하면 함수는 -1을 반환
     *
     *   A[0] = 0 (1, 3, 4)     => (0 개수) * 1
     *   A[1] = 1               => 1 * 1 = 1
     *   A[2] = 0 (3, 4)
     *   A[3] = 1               => 2 * 1 = 2
     *   A[4] = 1               => 2 * 1 = 2
     *
     *   https://app.codility.com/demo/results/training44H9JD-NQH/
     */
    public static int solution(int[] A) {

        int cntZero = 0;
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) cntZero++;
            else result += cntZero;

            if (result > 1_000_000_000) return -1;
        }

        return result;
    }
}
