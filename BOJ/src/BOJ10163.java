import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10163 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] paper = new int[101][101];
		int[] cnt = new int[N + 1];
		
		int R, C, W, H;
		for (int n = 1; n <= N; n++) {

			st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			for (int w = R; w < W + R; w++) {
				for (int h = C; h < H + C; h++) {
					if(paper[w][h] == 0) {
						paper[w][h] = n;
						cnt[n]++;
					}
					else {
						cnt[paper[w][h]]--;
						paper[w][h] = n;
						cnt[n]++;
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.println(cnt[i]);
		}
	}
}
