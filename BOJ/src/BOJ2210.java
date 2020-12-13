import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2210 {

	static int N = 5, map[][], res;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[N][N];
		selected = new boolean[1000001];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				process(i, j, 0, map[i][j]);
			}
		}
		System.out.println(res);
	}

	private static void process(int r, int c, int cnt, int num) {

		// 여섯 자리의 숫자가 만들어지면
		if(cnt == 5) {
			// 처음 만들어지는 숫자일 경우
			if(!selected[num]) {
				selected[num] = true;
				res++;
			}
			
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int rr = r + dr[d];
			int cc = c + dc[d];
			
			if(rr < 0 || cc < 0 || rr >= N || cc >= N) continue;
			
			process(rr, cc, cnt + 1, num * 10 + map[rr][cc]);
		}
	}

}
