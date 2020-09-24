import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3307 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N+1];
			int[] LIS = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
					
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int res = 0;
			for (int i = 1; i <= N; i++) {
				LIS[i] = 1;
				
				for (int j = 0; j < i; j++) {
					if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1)
						LIS[i] = LIS[j] + 1;
				}
				
				res = Math.max(res, LIS[i]);
			}
			
			System.out.println("#" + tc + " " + res);
		}
		
	}
	
}
