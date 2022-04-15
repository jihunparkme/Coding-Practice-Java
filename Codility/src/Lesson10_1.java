public class Lesson10_1 {

    public static void main(String[] args) {
        System.out.println(solution(24));
        System.out.println(solution(1));
        System.out.println(solution(4_999_696));
    }

    /**
     * 약수 개수 구하기
     *
     * https://app.codility.com/demo/results/trainingFXZYV5-WER/
     */
    public static int solution(int N) {

        int result = 0;
        for (int i = 1; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                result++;
                if (i * i < N) result++;
            }
        }
        
        return result;
    }

    public static int solution2(int N) {

        int result = 0;
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) {
                result++;
                if (i * i < N) result++;
            }
        }

        return result;
    }
}
