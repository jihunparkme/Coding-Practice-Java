class Lesson12_2 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{15, 10, 3}, new int[]{75, 30, 5}));
        System.out.println(solution(new int[]{15,10,3}, new int[]{75,30,5}) == 1);
    }

    /**
     * [Redefine the problem + abstract]
     * N과 M의 소수 제수 집합이 동일한지 검증
     *
     * [Create solution plan]
     * A, B의 최대공약수 G를 구한 후,
     * A, B는 G와의 최대공약수가 1이 되어야 한다.
     *
     * [Prove the plan]
     * Z is an integer within the range [1..6,000];
     * each element of arrays A and B is an integer within the range [1..2,147,483,647].
     */
    public static int solution(int[] A, int[] B) {

        int result = 0;
        int N = A.length;
        for (int i = 0; i < N; i++) {
            int a = A[i];
            int b = B[i];
            int gcd = gcd(a, b);
            // a, b와 a,b의 최소공배수는 나누어 떨어져야 한다.
            if (check(a, gcd) && check(b, gcd)) {
                result++;
            }
        }

        return result;
    }

    private static boolean check(int a, int gcd) {
        if (a == 1) {
            return true;
        }
        if (gcd == 1) {
            return false;
        }

        gcd = gcd(a, gcd);
        a = a / gcd;

        return check(a, gcd);
    }

    private static int gcd(int n, int m) {
        if (m == 0) {
            return n;
        }

        return gcd(m, n % m);
    }
}



