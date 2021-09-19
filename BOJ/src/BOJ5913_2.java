import java.io.*;
import java.util.*;

public class Main {

	FastScanner in;
	PrintWriter out;

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
		cntGrass--;
		start(0, 0);
		
		out.println(cntCase);
	}
	
	private void start(int x, int y) {
		
		// Mildred의 위치에 도달 
		if (x == 4 && y == 4) {
			// 모든 잔디를 섭취
			if (cntGrass == 0) cntCase++;
			else return;
		}
		
		// Mildred의 위치에 도달하지 않았는데 남은 잔디가 없을 경우
		if (cntGrass == 0 && !(x == 4 && y == 4)) return;

		// Bessie가 이동
		for (int d = 0; d < 4; d++) {
			//Bessie가 이동할 곳에 바위가 있거나 필드를 벗어나면 pass 
			if (!canMove(x, y, d)) continue;
			
			int xx = x + dx[d];
			int yy = y + dy[d];
			
			// 해당 위치로 이동해서 잔디 식사
			cntGrass--;
			isRock[xx][yy] = true;
			
			// 재귀
			start(xx, yy);
			
			// 돌아오면 잔디 다시 뱉기
			cntGrass++;
			isRock[xx][yy] = false;
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