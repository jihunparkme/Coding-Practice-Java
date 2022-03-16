import java.util.Arrays;

class Lesson3_2 {
    public static void main(String[] args) {
        System.out.println("Answer : " + solution(new int[]{1}));
    }

    /**
     * 배열에는 [1..(N + 1)] 범위의 정수
     * 누락된 요소 찾기
     *
     * - 첫 번째 혹은 마지막 번째 숫자가 누락인 경우
     * - 범위 제대로 보자ㅠ 가능하면 그냥 크게 잡기
     *
     *  https://app.codility.com/demo/results/trainingGZ864D-PP8/
     */
    public static int solution(int[] A) {
        int last = 0;
        Boolean[] check = new Boolean[100100];
        Arrays.fill(check, false);
        for (int i = 0; i < A.length; i++) {
            check[A[i]] = true;
            last = last < A[i] ? A[i] : last;
        }

        for (int i = 1; i <= last + 1 ; i++) {
            if (!check[i]) return i;
        }

        return 1;
    }
}



