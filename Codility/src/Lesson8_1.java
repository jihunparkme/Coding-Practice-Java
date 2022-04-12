import java.util.HashMap;
import java.util.Map;

public class Lesson8_1 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 4, 3, 2, 3, -1, 3, 3}));
    }

    /**
     * 중복되는 숫자의 개수가 배열 사이즈의 절반보다 크다면
     * 해당 숫자가 위치한 아무 인덱스를, 아닐 경우 -1 return
     *
     * N is an integer within the range [0..100,000];
     * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
     *
     * https://app.codility.com/demo/results/trainingFJKJHQ-ES6/
     */
    public static int solution(int[] A) {

        int size = A.length;
        int maxCnt = -1;
        int maxCntIdx = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int cnt = map.getOrDefault(A[i], 0) + 1;
            map.put(A[i], cnt);

            if (maxCnt < cnt) {
                maxCnt = cnt;
                maxCntIdx = i;
            }
        }

        return maxCnt > (size / 2.0) ? maxCntIdx : -1;
    }
}
