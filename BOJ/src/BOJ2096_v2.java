import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2096_v2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] min = new int[2][3];
		int[][] max = new int[2][3];

		int a, b, c;
		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			// 최소 구하기
			min[1][0] = a + Math.min(min[0][0], min[0][1]);
			min[1][1] = b + Math.min(min[0][0], Math.min(min[0][1], min[0][2]));
			min[1][2] = c + Math.min(min[0][1], min[0][2]);
			System.arraycopy(min[1], 0, min[0], 0, 3);
			
			max[1][0] = a + Math.max(max[0][0], max[0][1]);
			max[1][1] = b + Math.max(max[0][0], Math.max(max[0][1], max[0][2]));
			max[1][2] = c + Math.max(max[0][1], max[0][2]);
			System.arraycopy(max[1], 0, max[0], 0, 3);
		}
		
		Arrays.sort(min[1]);
		Arrays.sort(max[1]);
		
		System.out.println(max[1][2] + " " + min[1][0]);
	}
}