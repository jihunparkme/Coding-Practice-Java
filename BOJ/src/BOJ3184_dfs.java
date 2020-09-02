import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3184_dfs {

	static int O, V, R, C, cntO, cntV;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) 
			map[i] = br.readLine().toCharArray();
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 울타리일경우 pass
				if(map[i][j] == '#') continue;
				// 울타리가 아니면 해당 영역을 확인해보자.
				cntO = cntV = 0;
				Find(i, j);
				// 양의 수가 더 많으면 양이 살아남고,
				// 늑대 수가 더 많으면 늑대가 살아남는다.
				if(cntO > cntV) O += cntO;
				else V += cntV;
			}
		}
		
		System.out.println(O + " " + V);
	}

	// 울타리로 채우면서 양의 수와 늑대의 수를 센다
	private static void Find(int r, int c) {
		
		// 늑대라면
		if(map[r][c] == 'o') cntO++;
		// 양이라면
		if(map[r][c] == 'v') cntV++;
		map[r][c] = '#';

		for (int d = 0; d < 4; d++) {
			int rr = r + dr[d];
			int cc = c + dc[d];
			// 범위를 벗어날 경우
			if(rr < 0 || cc < 0 || cc >= C || rr >= R) continue;
			// 울타리가 있을 경우
			if(map[rr][cc] == '#') continue;
			
			Find(rr, cc);
		}
	}

}
