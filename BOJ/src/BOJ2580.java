import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2580 {

	static int N = 9, M, map[][];
	static ArrayList<Point> blanks;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[N][N];
		blanks = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) blanks.add(new Point(i, j));
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

		// 모든  칸을 다 채웠을 경우
		if(cnt == M) return true;
		
		// 1~9까지 빈 칸에 넣어보자.
		Point now = blanks.get(cnt);
		for (int i = 1; i <= 9; i++) {
			map[now.r][now.c] = i;
			
			// 빈 칸에 현재 숫자를 넣을 수 있다면 다음 칸에 넣어보자.
			if(check(now.r, now.c, i)) {
				if(process(cnt + 1)) return true;
			}
		}
		
		// 원상복구
		map[now.r][now.c] = 0;
		
		return false;
	}

	private static boolean check(int r, int c, int num) {
		
		// 가로줄 확인
		for (int i = 0; i < N; i++) {
			if(i == c) continue;
			// 동일한 숫자가 있을 경우
			if(map[r][i] == num) return false;
		}
		
		// 세로줄 확인
		for (int i = 0; i < N; i++) {
			if(i == r) continue;
			// 동일한 숫자가 있을 경우
			if(map[i][c] == num) return false;
		}
		
		// 3x3 구역 확인
		int sr = r / 3 * 3, sc = c / 3 * 3;
		for (int i = sr; i < sr + 3; i++) {
			for (int j = sc; j < sc + 3; j++) {
				if(i == r && j == c) continue;
				// 동일한 숫자가 있을 경우
				if(map[i][j] == num) return false;
			}
		}
		
		return true;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
