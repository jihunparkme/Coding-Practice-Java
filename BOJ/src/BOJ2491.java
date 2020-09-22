import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2491 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			nums[i] = Integer.parseInt(st.nextToken());
		
		int res = 0, asc = 1, desc = 1;
		for (int i = 1; i < N; i++) {
			
			// 오름차순
			if(nums[i-1] <= nums[i]) {
				asc++;
			} else {
				res = Math.max(res, asc);
				asc = 1;
			}
			
			// 내림차순
			if(nums[i-1] >= nums[i]) {
				desc++;
			} else {
				res = Math.max(res, desc);
				desc = 1;
			}
		}
		
		System.out.println(Math.max(Math.max(res, asc), Math.max(res, desc)));
	}

}
