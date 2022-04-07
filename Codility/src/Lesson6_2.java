import java.util.*;

public class Lesson6_2 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-3, 1, 2, -2, 5, 6}));
//        System.out.println(solution(new int[]{10, 10, 10}));
    }

    /**
     * 모든 삼중항의 최대 곱 값을 반환
     *
     * https://app.codility.com/demo/results/trainingFZDXBX-XXB/
     */
    public static int solution(int[] A) {

        Arrays.sort(A);
        int N = A.length;
        int case1 = A[N-1] * A[N-2] * A[N-3];
        int case2 = A[0] * A[1] * A[N-1];

        return case1 > case2 ? case1 : case2;
    }
}
