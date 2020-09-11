import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11399 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int[] dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			nums[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(nums);
		int res = nums[0];
		dp[0] = nums[0];
		for (int i = 1; i < N; i++) {
			dp[i] = dp[i-1] + nums[i];
			res += dp[i];
		}
		System.out.println(res);
	}

}
