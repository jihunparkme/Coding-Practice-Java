import java.util.Scanner;

public class Solution7393 {
	static final int MOD = 1_000_000_000;
	public static void main(String[] args) {
		int[][][] dp = new int[101][10][1 << 10];
		for(int i = 1; i < 10; i++) {
			dp[1][i][ 1 << i ] = 1;
		}
		//각 자리수 별로..
		for(int i = 2; i <= 100; i++) {
			for(int j = 0; j <= 9; j++) {
				for(int k = 0; k < 1024; k++) {
					if( j == 0 ) {
						dp[i][j][k | (1 << j)] += dp[i-1][j+1][k];
						dp[i][j][k | (1 << j)] %= MOD;
					}
					else if( j == 9 ) {
						dp[i][j][k | (1 << j)] += dp[i-1][j-1][k];
						dp[i][j][k | (1 << j)] %= MOD;
					}
					else {
						dp[i][j][k | (1 << j)] += dp[i-1][j-1][k];
						dp[i][j][k | (1 << j)] %= MOD;
						dp[i][j][k | (1 << j)] += dp[i-1][j+1][k];
						dp[i][j][k | (1 << j)] %= MOD;
					}
				}
			}
		}
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int ans = 0;
			for(int i = 0; i < 10; i++) {
				//1023은 모든 비트가 1. 모든 비트가 1이 된 경우만 성공이므로, 그거만 센다.
				ans = (ans+dp[N][i][1023]) % MOD;
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}