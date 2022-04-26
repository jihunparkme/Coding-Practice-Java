import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Lesson11_1 {

    public static void main(String[] args) {
        System.out.println(Arrays.equals(solution(new int[]{3, 1, 2, 3, 6}), new int[]{2, 4, 3, 2, 0}));
        System.out.println(Arrays.equals(solution(new int[]{1}), new int[]{0}));
    }

    /**
     * [Redefine the problem + abstract]
     * 배열 A에서 A[i] 의 약수를 제외한 숫자의 개수
     *
     * [Create solution plan]
     * 배열 cntNum에 A[i] 개수 저장. (~ 2 * N)
     * 배열 A를 돌면서 에라토스테네스의 체를 적용하여 배열 A에서의 A[i]의 약수 개수 확인
     * (N - 배열에서 약수 개수) =
     *
     * [Prove the plan]
     * N is an integer within the range [1..50,000];
     * each element of array A is an integer within the range [1..2 * N].
     *
     * [Result]
     * https://app.codility.com/demo/results/trainingBYFHT4-SE8/
     */
    private static int N;
    public static int[] solution(int[] A) {
        N = A.length;
        int[] cntDivisors = getCountByDivisors(A);
        int[] result   = new int[N];

        for (int i = 0; i < N; i++) {
            result[i] = N - cntDivisors[A[i]];
        }

        return result ;
    }

    private static int[] getCountByDivisors(int[] A) {
        int[] cntElement = getCountByElement(A);
        int[] cntDivisors   = new int[2 * N + 1];
        for (int num = 1; num < 2 * N + 1; num++) {
            if (cntElement[num] == 0) continue;
            for (int j = num; j < 2 * N + 1; j+=num) {
                cntDivisors[j] += cntElement[num];
            }
        }
        return cntDivisors;
    }

    private static int[] getCountByElement(int[] A) {
        int[] cntElement = new int[2 * N + 1];
        for (int i = 0; i < N; i++) {
            cntElement[A[i]]++;
        }
        return cntElement;
    }
}



