import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2580_v2 {

	static int N = 9, M, map[][];
	static boolean[][] row, col, squ; 
	static ArrayList<Point> blanks;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[N][N];
		row = new boolean[N][N + 1];
		col = new boolean[N][N + 1];
		squ = new boolean[N][N + 1];
		blanks = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 0) blanks.add(new Point(i, j));
				else {
					// i 행에서 map[i][j] 숫자가 사용
					row[i][map[i][j]] = true;
					// j 열에서 map[i][j] 숫자가 사용
					col[j][map[i][j]] = true;
					// 해당 좌표가 해당하는 3x3구역에서 map[i][j] 숫자가 사용
					squ[(i/3*3) + (j/3)][map[i][j]] = true;
				}
			}
		}
		
		M = blanks.size();
		process(0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static boolean process(int cnt) {

		// 모든 빈칸을 다 채웠을 경우
		if(cnt == M) return true;
		
		// 1~9까지 빈칸에 넣어보자.
		Point now = blanks.get(cnt);
		for (int i = 1; i <= 9; i++) {
			// 해당 숫자가 가로줄 or 세로줄 or 3x3구역에 포함되었다면 pass 
			if(row[now.r][i] || col[now.c][i] || squ[(now.r/3*3) + (now.c/3)][i]) continue;
			
			// 빈칸에 현재 숫자를 넣을 수 있다면
			row[now.r][i] = true;
			col[now.c][i] = true;
			squ[(now.r/3*3) + (now.c/3)][i] = true;
			map[now.r][now.c] = i;
			
			// 다음 빈칸으로
			if(process(cnt + 1)) return true;
			
			// 원상복구
			row[now.r][i] = false;
			col[now.c][i] = false;
			squ[(now.r/3*3) + (now.c/3)][i] = false;
			map[now.r][now.c] = 0;
		}
		
		return false;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
