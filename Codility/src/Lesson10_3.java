public class Lesson10_3 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,5,3,4,3,4,1,2,3,4,6,2}));
    }

    /**
     * 정상에 설치할 수 있는 최대 깃발 K 수
     * K >= |P − Q|
     *
     * N is an integer within the range [1..400,000];
     * each element of array A is an integer within the range [0..1,000,000,000].
     *
     * https://app.codility.com/demo/results/trainingMAFQV6-QTZ/
     */
    private static int N;
    public static int solution(int[] A) {

        N = A.length;
        boolean[] isPeak = createPeaks(A);
        int result = 0;

        int left = 0;
        int right = N-1;
        int mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if (check(mid, isPeak)) {
                result  = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private static boolean[] createPeaks(int[] A) {

        boolean[] isPeak = new boolean[N];
        for (int i = 1; i < N - 1; i++) {
            if (A[i] > Math.max(A[i-1], A[i+1])) {
                isPeak[i] = true;
            }
        }
        return isPeak;
    }

    private static boolean check(int k, boolean[] isPeak) {

        int idx = 0;
        int flag = k;
        while(idx < N && flag > 0) {
            if (isPeak[idx]) {
                idx+=k;
                flag--;
            } else {
                idx++;
            }
        }

        return flag == 0 ? true : false;
    }
}
