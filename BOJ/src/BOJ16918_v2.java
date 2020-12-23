import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918_v2 {

	static int R, C, N;
	static char map[][][];
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 짝수일 경우 무조건 폭탄이 모두 설치된 상태
		if(N % 2 == 0) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append('O');
				}
				sb.append("\n");
			}
			System.out.println(sb);
			return;
		}
		
		map = new char[2][R][C];
		for (int i = 0; i < R; i++) {
			map[0][i] = br.readLine().toCharArray();
		}
		
		if(N / 2 > 4) N = (N / 2 - 4) % 2 + 4;
		else N /= 2;
		
		int idx = process();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[idx][i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int process() {

		int cur = 0, nxt = 1, time = 0;
		
		while(time < N) {
			
			++time;
			// 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[cur][i][j] == '.') {
						map[nxt][i][j] = 'O';
					} else {
						// 전에 설치된 폭탄일 경우 폭탄이 있던 칸이 파괴, 
						map[nxt][i][j] = '.';
					}
				}
			}
			
			// 전에 설치된 폭탄이 모두 폭발
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if(map[cur][r][c] == '.') continue; 
					// 인접한 네 칸도 함께 파괴
					for (int d = 0; d < 4; d++) {
						int rr = r + dr[d];
						int cc = c + dc[d];
						if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
						map[nxt][rr][cc] = '.';
					}
				}
			}
			
			cur ^= 1;
			nxt ^= 1;
		}
		
		return cur;
	}

}