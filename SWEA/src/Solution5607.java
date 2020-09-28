import java.util.Scanner;

public class Solution5607 {

	static final int MOD = 1234567891;
	
	public static void main(String[] args) {
		
		// 미리 펙토리얼값을 구해놓자.
		long[] factorial = new long[1000001];
		factorial[1] = 1;
		for (int i = 2; i <= 1000000; i++) {
			factorial[i] = factorial[i-1] * i % MOD;
		}
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			int N = sc.nextInt();
			int R = sc.nextInt();
			// N! * (N-R! * R!) ^ (MOD-2)
			long ans = factorial[N] * power((factorial[N-R] * factorial[R]) % MOD, MOD - 2) % MOD;
			System.out.println("#" + tc + " " + ans);
			
		}
	}

	private static long power(long n, long m) {

		// 1승일 경우 자기 자신을 return
		if(m == 1) return n;
		
		// 그렇지 않을 경우 홀수/짝수승 나눠서 연산
		long res = power(n, m/2);
		// m이 짝수일 경우
		if(m % 2 == 0) return res * res % MOD;
		else return res * res % MOD * n % MOD;
		
	}

}
