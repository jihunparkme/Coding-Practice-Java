import java.util.ArrayList;
import java.util.List;

public class Lesson10_4 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,2,3,4,3,4,1,2,3,4,6,2}) == 3);
        System.out.println(solution(new int[]{5}) == 0);
        System.out.println(solution(new int[]{1,3,1}) == 1);
        System.out.println(solution(new int[]{1,3,2,1}) == 1);
        System.out.println(solution(new int[]{1,3,3}) == 0);
    }

    /**
     * [Redefine the problem + abstract]
     * 블록으로 나누기 (같은 개수의 요소를 포함하고, 적어도 하나의 peak 포함)
     * 배열 A를 나눌 수 있는 최대 블록 수
     * 블록으로 나눌 수 없을 경우 0
     *
     * [Create solution plan]
     * 1.
     * 각 블록에 적어도 하나의 peak를 포함하고, 같은 개수의 요소를 갖으려면 peak의 개수와 N의 약수를 먼저 떠올려야 한다.
     * 블록이 아무리 많더라도 peak의 개수를 초과한다면 조건에 맞지 않으므로 볼 필요도 없다.
     *
     * 2.
     * peak의 개수만큼 확인 블록을 나누어보는데, peak가 N의 약수일 경우만 확인하면 된다.
     * (N의 약수가 아니면 블록의 각 요소 개수가 다르다)
     *
     * 3.
     * 각 블록 구간과 peak_idx를 확인하면서 각 블록에 peak가 포함되어 있는지를 확인하자.
     * (left, right idx 를 활용해서 블록 구간을 잡고 각 peak idx 를 확인)
     *
     * [Prove the plan]
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [0..1,000,000,000].
     *
     * https://app.codility.com/demo/results/training6Y2X56-NGU/
     */
    private static int N;
    public static int solution(int[] A) {

        N = A.length;
        // N이 3보다 작으면 peak가 존재하지 않음.
        if (N < 3) return 0;

        List<Integer> peakIdxList = getPeakIdx(A);;
        // peak 개수는 나눌 수 있는 블록의 최대 수.
        for (int i = peakIdxList.size(); i >= 1; i--) {
            // N의 약수일 경우에만 같은 개수를 갖는 블록으로 나눌 수 있음.
            if (N % i == 0) {
                if (check(peakIdxList, i)) return i;
            }
        }

        return 0;
    }

    private static boolean check(List<Integer> peakIdxList, int blockSize) {
        int K = N / blockSize;
        int cnt = 0;
        for (int j = 0; j < peakIdxList.size(); j++) {
            int left = cnt * K;
            int right = (cnt + 1) * K;
            Integer peak = peakIdxList.get(j);
            //해당 블록에 peak가 존재하는지 확인
            if (peak >= left && peak < right) cnt++;
        }

        return blockSize == cnt;
    }

    private static List<Integer> getPeakIdx(int[] A) {
        List<Integer> peakIdxList = new ArrayList<>();
        for (int i = 1; i < N - 1; i++) {
            if (A[i] > Math.max(A[i-1], A[i+1])) peakIdxList.add(i);
        }

        return peakIdxList;
    }
}
