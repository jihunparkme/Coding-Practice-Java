package test_20220419;

public class Problem01 {
    public static void main(String[] args) {
        System.out.println(solution(28) == 37);
        System.out.println(solution(734) == 743);
        System.out.println(solution(1990) == 2089);
        System.out.println(solution(1000) == 10000);
        System.out.println(solution(1) == 10);
        System.out.println(solution(100) == 1000);
        System.out.println(solution(12_345) == 12354);
        System.out.println(solution(50_000));
    }

    /**
     * N보다 큰 가장 작은 정수 반환하고, 그 숫자의 합은 N의 숫자 합과 같다
     */
    public static int solution(int N) {

        int compare = sumDigits(N);
        for (int i = N + 1; i <= 50_000 ; i++) {
            if (sumDigits(i) == compare) return i;
        }

        return N;
    }

    private static int sumDigits(int num) {

        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
