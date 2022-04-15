public class Lesson10_2 {

    public static void main(String[] args) {
        System.out.println(solution(30));
        System.out.println(solution(1));
        System.out.println(solution(1_000_000_000));
    }

    /**
     * 면적이 N인 최소 둘레 찾기
     * N is an integer within the range [1..1,000,000,000].
     *
     * https://app.codility.com/demo/results/trainingP9TZJK-6CZ/
     */
    public static int solution(int N) {

        int result = Integer.MAX_VALUE;
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) {
                int area = 2 * ((N / i) + i);
                result = Math.min(result, area);
            }
        }

        return result;
    }
}
