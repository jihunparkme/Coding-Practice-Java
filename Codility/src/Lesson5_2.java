
public class Lesson5_2 {

    public static void main(String[] args) {
//        System.out.println(solution(100, 2_000_000_000, 2));
//        System.out.println(solution(0,1, 17)); // 6, 11, 2           11 / 2 = 5,   6 / 2 = 3
        System.out.println(solution(11, 345, 17)); // 6, 11, 2           11 / 2 = 5,   6 / 2 = 3
    }

    /**
     * [A..B] 범위 내에서 K로 나눌 수 있는 정수의 개수
     *
     * A and B are integers within the range [0..2,000,000,000];
     * K is an integer within the range [1..2,000,000,000];
     * A ≤ B.
     *
     * https://app.codility.com/demo/results/trainingPHP3SW-T64/
     *  - "A가 K로 나누어 떨어질 경우에만" -1 을 해주었어야 했는데, 놓치고 무조건 -1 해주고 있었네..
     */
    public static int solution(int A, int B, int K) {
        int a = B / K;
        int b = A%K==0 ? A/K-1 : A/K;
        return a - b;
    }
}
