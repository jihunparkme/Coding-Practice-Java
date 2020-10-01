import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11401 {

	static final long MOD = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		
		// 미리 팩토리얼값을 구해놓자.
		long[] factorial = new long[4000001];
		factorial[0] = 1;
		factorial[1] = 1;
		for (int i = 2; i <= 4000000; i++) {
			factorial[i] = factorial[i-1] * i % MOD;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// N! * (N-R! * R!) ^ (MOD-2)
		long ans = factorial[N] * power((factorial[N-K] * factorial[K]) % MOD, MOD - 2) % MOD;
		
		System.out.println(ans);
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
