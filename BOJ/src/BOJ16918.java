import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918 {

	static int R, C, N;
	static char map[][][];
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[2][R][C];
		for (int i = 0; i < R; i++) {
			map[0][i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j <C; j++) {
				map[1][i][j] = map[0][i][j]; 
			}
		}
		
		int idx = 0;
		if(N != 1) idx = process();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[idx][i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int process() {

		int idx = 1, rr = 0, cc = 0, time = 1;
		
		while(true) {
			
			copy(map[idx^1], map[idx]);
			
			// 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[idx][i][j] == 'O') continue;
					map[idx][i][j] = 'O';
				}
			}
			if(++time == N) return idx;
			
			// 전에 설치된 폭탄이 모두 폭발
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if(map[idx^1][r][c] == '.') continue; 
					// 전에 설치된 폭탄일 경우 폭탄이 있던 칸이 파괴, 
					map[idx][r][c] = '.';
					// 인접한 네 칸도 함께 파괴
					for (int d = 0; d < 4; d++) {
						rr = r + dr[d];
						cc = c + dc[d];
						if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
						map[idx][rr][cc] = '.';
					}
				}
			}
			if(++time == N) return idx;
			
			idx ^= 1;
		}
	}

	private static void copy(char[][] src, char[][] dst) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				dst[i][j] = src[i][j];
			}
		}
	}
	
	

}