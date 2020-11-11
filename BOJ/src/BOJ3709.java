import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3709 {

	static int N, R, resR, resC;
	static boolean map[][];
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			resR = 0;
			resC = 0;
			
			st = new StringTokenizer(br.readLine());
		
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			map = new boolean[N + 2][N + 2];

			int a = 0, b = 0;
			// 거울 배치
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());

				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				map[a][b] = true; 
			}
			// 레이저 배치
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			int dir = 0;
			if(a == 0) dir = 2;
			else if(a == N + 1) dir = 0;
			else if(b == 0) dir = 1;
			else if(b == N + 1) dir = 3; 
			
			process(a, b, dir, 0);
			
			System.out.println(resR + " " + resC);
		}

	}

	private static boolean process(int r, int c, int d, int cnt) {
		
		
		if(cnt > N * N ) return true;
		
		int rr = r + dr[d];
		int cc = c + dc[d];
		// 레이저가 보드를 벗어날 경우
		if(rr < 1 || rr > N || cc < 1 || cc > N) {
			resR = rr;
			resC =cc;
			return true;
		}
		
		// 거울이 없을 경우
		if(!map[rr][cc]) process(rr, cc, d, cnt);
		// 거울이 있을 경우 오른쪽으로 각도를 변경
		else process(rr, cc, (d + 1) % 4, cnt + 1);
		
		return false;
	}

}
