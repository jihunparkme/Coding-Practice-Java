class Lesson12_1 {

    public static void main(String[] args) {
        System.out.println(solution(10, 4) == 5);
        System.out.println(solution(1, 1) == 1);
        System.out.println(solution(1000000000, 1) == 1000000000);
    }

    /**
     * [Redefine the problem + abstract]
     * 원에 있는 초콜릿을 먹는다.
     * 0에 있는 초콜릿 부터 먹기 시작. (M-1 이나 빈 포장지는 생략)
     *
     * [Create solution plan]
     * 빈 포장지를 만날 때까지 반복하면서 초콜릿 먹기
     *
     * [Prove the plan]
     * N and M are integers within the range [1..1,000,000,000].
     * timeout : https://app.codility.com/demo/results/training2JSDR4-FVE/
     * Euclidean algorithm : https://app.codility.com/demo/results/trainingF99GT7-PYH/
     */
    public static int solution(int N, int M) {
        int gcd = gcd(N, M);

        return N/gcd;
    }

    private static int gcd(int n, int m) {
        if (m == 0) {
            return n;
        }

        return gcd(m, n%m);
    }
}



