import java.util.Stack;

public class Progrms_92335 {
    public static void main(String[] args) {
        System.out.println(solution(437674, 10));
    }

    /**
     * 양의 정수 n 을 k 진수로 바꿨을 때, 조건에 맞는 소수 개수 (10진수 기준)
     *
     * 0P0처럼 소수 양쪽에 0이 있는 경우
     * P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
     * 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
     * P처럼 소수 양쪽에 아무것도 없는 경우
     * 단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
     * 예를 들어, 101은 P가 될 수 없습니다.
     */
    public static int solution(int n, int k) {
        String s = Integer.toString(n, k);
        System.out.println(s);


        int answer = 0;

        Stack<Integer> mods = getMods(k, n);
        long num = 0;

        while (!mods.isEmpty()) {
            int pop = mods.pop();
            if (pop == 0) {
                if (isPrime(num)) answer++;
                num = 0;
            } else {
                num = (num * 10) + pop;
            }
        }

        if (isPrime(num)) answer++;

        return answer;
    }

    private static boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i*i <= num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    private static Stack<Integer> getMods(int k, int num) {
        Stack<Integer> result = new Stack<>();

        while (num > 0) {
            int mod = num % k;
            num /= k;
            result.push(mod);
        }

        return result;
    }
}
