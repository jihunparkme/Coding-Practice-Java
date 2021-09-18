import java.io.*;
import java.util.*;

public class Main {

	FastScanner in;
	PrintWriter out;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int cntCase = 0, cntGrass;
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	static boolean[][] isRock;
	void solve() {
		int K = in.nextInt();
		isRock = new boolean[5][5];
		cntGrass = 25 - K;
		// 사각형을 시계 반대 방향으로 90도 돌려서 생각
		for (int i = 0; i < K; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			isRock[x - 1][y - 1] = true; // 돌의 위치 
		}
		
		// 시작 위치
		isRock[0][0] = true;
		isRock[4][4] = true;
		cntGrass-=2;
		start(0, 0, 4, 4);
		
		out.println(cntCase);
	}
	
	private void start(int bx, int by, int mx, int my) {
		
		// 잔디가 모두 없어졌을 경우
		if (cntGrass == -1) {
			// B와 M이 같은 공간에 위치
			if (bx == mx && by == my) {
				cntCase++;
			} else {
				return;
			}
		}
		
		// B와 M이 만났는데 잔디가 남았을 경우
		if (bx == mx && by == my && cntGrass > -1) return;

		// B와 M가 이동
		for (int d1 = 0; d1 < 4; d1++) {
			// B가 이동할 곳에 바위가 있거나 필드를 벗어나면 pass 
			if (!canMove(bx, by, d1)) continue;
			
			for (int d2 = 0; d2 < 4; d2++) {
				// M가 이동할 곳에 바위가 있거나 필드를 벗어나면 pass
				if (!canMove(mx, my, d2)) continue;
				
				Point nextBs = new Point(bx + dx[d1], by + dy[d1]);
				Point nextMd = new Point(mx + dx[d2], my + dy[d2]);
				
				// 해당 위치로 이동해서 잔디 식사
				cntGrass-=2;
				isRock[nextBs.x][nextBs.y] = true;
				isRock[nextMd.x][nextMd.y] = true;
				
				// 재귀
				start(nextBs.x, nextBs.y, nextMd.x, nextMd.y);
				
				// 돌아오면 잔디 다시 뱉기
				cntGrass+=2;
				isRock[nextBs.x][nextBs.y] = false;
				isRock[nextMd.x][nextMd.y] = false;
			}
		}
	}

	private Boolean canMove(int x, int y, int d) {
		
		int xx = x + dx[d];
		int yy = y + dy[d];
		// 필드를 벗어날 경우
		if (xx  > 4 || yy  > 4 || xx < 0 || yy < 0) return false;
		// 바위가 있는 경우
		if (isRock[xx][yy]) return false;
		
		return true;
	}

	/********************************** Input function **********************************/
	
	void run() {
		in = new FastScanner();
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	public static void main(String[] args) {
		new Main().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastScanner(String s) {
			try {
				br = new BufferedReader(new FileReader(s));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		String nextToken() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(nextToken());
		}

		long nextLong() {
			return Long.parseLong(nextToken());
		}

		double nextDouble() {
			return Double.parseDouble(nextToken());
		}
	}
}