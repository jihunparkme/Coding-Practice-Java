import java.util.Arrays;

public class Lesson6_4 {

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{1,5,2,1,4,0}));
        System.out.println(solution(new int[]{1, 2147483647, 0}));
    }

    /**
     * 교차하는 디스크의 (순서 없는) 쌍의 수를 반환
     * 교차 쌍의 수가 10,000,000을 초과하면 -1을 반환
     *
     * N is an integer within the range [0..100,000];
     * each element of array A is an integer within the range [0..2,147,483,647].
     *
     * https://app.codility.com/demo/results/training9VWEWS-23V/
     */
    public static int solution(int[] A) {

        int size = A.length;
        long[] left = new long[size];
        long[] right = new long[size];
        for (int i = 0; i < size; i++) {
            left[i] = i - (long) A[i];
            right[i] = i + (long)  A[i];
        }

        Arrays.sort(left);
        Arrays.sort(right);

        int cnt = 0;
        int result = 0;
        int rIdx = 0;
        for (long lt : left) {

            while (lt > right[rIdx]) {
                cnt--;
                rIdx++;
            }
            result += cnt;
            cnt++;

            if (result > 10_000_000) return -1;
        }

        return result;
    }
}
