import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1984 {

	static int nums[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			nums = new int[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 10; i++) 
				nums[i] = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			Arrays.sort(nums);
			for (int i = 1; i < 9; i++) 
				sum += nums[i];
			
			System.out.println("#" + tc + " " + (int)((float)sum/8+0.5));
		}

	}

}
