import java.util.Arrays;

public class Lesson5_3 {

    public static void main(String[] args) {
        int[] solution = solution("CAGCCTA", new int[]{2, 5, 0}, new int[]{4, 5, 6});
//        int[] solution = solution("AC", new int[]{0, 0, 1}, new int[]{0, 1, 1});
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }

    /**
     * N is an integer within the range [1..100,000]; -> S length
     * M is an integer within the range [1..50,000]; -> P, Q size
     * each element of arrays P and Q is an integer within the range [0..N - 1];
     * P[K] ≤ Q[K], where 0 ≤ K < M;
     * string S consists only of upper-case English letters A, C, G, T.
     *
     * DNA 서열에 포함된 뉴클레오티드의 최소 영향 인자 구하기
     *
     * 3 ~ 5 까지의 부분 합은 sum[5] - sum[2]
     *
     * https://app.codility.com/demo/results/training8GWU79-FDT/
     */
    private static int[] DNA;
    private static int N;
    private static int M;
    public static int[] solution(String S, int[] P, int[] Q) {

        N = S.length();
        M = P.length;
        int[] result = new int[M];
        DNA = new int[128];

        setDNA();

        int[] sum = {0, 0, 0, 0};
        int[][] prefixSum = new int[N + 1][4];
        for (int i = 0; i < N; i++) {
            sum[DNA[S.charAt(i)] - 1]++;
            for (int j = 0; j < 4; j++) {
                prefixSum[i + 1][j] = sum[j];
            }
        }

        for (int i = 0; i < M; i++) {
            if (P[i] == Q[i]) result[i] = DNA[S.charAt(P[i])];
            else {
                int pst = P[i] > 1 ? P[i] : 0;
                result[i] = findMinElement(prefixSum[pst], prefixSum[Q[i] + 1]);
            }
        }

        return result;
    }

    private static int findMinElement(int[] p, int[] q) {
        for (int i = 0; i < 4; i++) {
            if (Math.abs(q[i] - p[i]) > 0) return i + 1;
        }

        return 0;
    }

    private static void setDNA() {
        DNA['A'] = 1;
        DNA['C'] = 2;
        DNA['G'] = 3;
        DNA['T'] = 4;
    }
}
