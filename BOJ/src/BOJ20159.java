import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20159 {

	static int N, cards[];
	
	public static void main(String[] args) throws IOException {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		cards = new int[N];		

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(process());
	}
	
	private static int process() {

		// 홀수, 짝수의 누적합(0: 짝수, 1: 홀수)
		int[][] sum = new int[2][N/2+1];
		for (int i = 0; i < N; i++) {
			// 짝수일 경우 (zero base)
			if((i + 1) % 2 == 0) sum[0][i/2+1] = sum[0][i/2] + cards[i]; 
			// 홀수일 경우
			else sum[1][i/2+1] = sum[1][i/2] + cards[i]; 
		}
		
		int max = 0;
		int[] res = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int idx = i / 2 + 1;
			// 짝수일 경우
			if(i % 2 == 0) res[i] = sum[1][idx -1] + (sum[0][N/2-1] - sum[0][idx-2]);  
			// 홀수일 경우
			else res[i] = sum[1][idx-1] + (sum[0][N/2-1] - sum[0][idx-1]) + cards[N - 1];
				
			max = Math.max(max, res[i]);
		} 
		
		return max;
	}

}
