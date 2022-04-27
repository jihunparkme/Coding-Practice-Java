import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Lesson11_2 {

    public static void main(String[] args) {
        System.out.println(Arrays.equals(solution(26, new int[]{1, 4, 16}, new int[]{26, 10, 20}), new int[]{10,4,0}));
        System.out.println(Arrays.equals(solution(26, new int[]{1}, new int[]{3}), new int[]{0}));
    }

    /**
     * [Redefine the problem + abstract]
     * 준소수 : 소수의 곱으로 이루어진 수
     * 주어진 범위의 수 사이에 있는 반소수의 개수 찾기
     * 
     * [Create solution plan]
     * 소수 구하기 (2, 3, 5, 7, 11 ..)
     * 소수를 돌면서 소수 끼리의 곱 구하기
     *  (4, 6, 10, 14, 22 ..
     *  9, 15, 21
     *
     *  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
     *        1   2     3 4           5  6                 7  8        9  10
     * 
     * [Prove the plan]
     * N is an integer within the range [1..50,000];
     * M is an integer within the range [1..30,000];
     * each element of arrays P and Q is an integer within the range [1..N];
     * P[i] ≤ Q[i].
     *
     * https://app.codility.com/demo/results/training5Q3M4M-A8Y/
     */
    public static int[] solution(int N, int[] P, int[] Q) {

        int[] cntSemiPrime = cntSemiPrime(N);
        int sizeOfArray = P.length;
        int[] result = new int[sizeOfArray];

        for (int i = 0; i < sizeOfArray; i++) {
            result[i] = cntSemiPrime[Q[i]] - cntSemiPrime[P[i] - 1];
        }
        
        return result;
    }

    private static int[] cntSemiPrime(int N) {
        boolean[] isSemiPrime = getSemiPrime(N);

        int[] cntSemiPrime = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            int add = isSemiPrime[i] ? 1 : 0;
            cntSemiPrime[i] = cntSemiPrime[i-1] + add;
        }
        return cntSemiPrime;
    }

    private static List<Integer> getPrimes(int N) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isChecked = new boolean[N + 1];
        for (int i = 2; i < N + 1; i++) {
            if (isChecked[i]) continue;

            primes.add(i);
            for (int j = i; j < N + 1; j+=i) {
                isChecked[j] = true;
            }
        }
        return primes;
    }

    private static boolean[] getSemiPrime(int N) {
        List<Integer> primes = getPrimes(N);
        int sieOfPrime = primes.size();
        boolean[] isSemiPrime = new boolean[N + 1];
        
        for (int i = 0; i < sieOfPrime; i++) {
            for (int j = 0; j < sieOfPrime; j++) {
                if (primes.get(i) * primes.get(j) > N) break;
                isSemiPrime[primes.get(i) * primes.get(j)] = true;
            }
        }

        return isSemiPrime;
    }
}



