import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559 {

	static int R = 12, C = 6;
	static char map[][];
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static ArrayList<Point> list;
	static boolean[][] checked;
	static Queue<Point> q;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
	
		System.out.println(process());
	}

	private static int process() {

		int cnt = 0;
		while(true) {
			
			// 같은 색으로 연결된 뿌요들을 없애보자.
			boolean flag = false;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j] == '.') continue;
					if(burst(i, j, map[i][j])) flag = true;
				}
			}
			
			// map에 변화가 없다면
			if(!flag) return cnt;
			
			// 중력의 영향을 받아 뿌요들이 떨어짐
			drop();
			
			cnt++;
		}
	}

	private static void drop() {
		
		for (int c = 0; c < C; c++) {
			int empty = -1; // 빈 공간의 행 번호
			
			for (int r = R - 1; r >= 0; r--) {
				// 뿌요가 있는 자리이고, 빈 공간이 없다면
				if(map[r][c] != '.' && empty == -1) continue;
				// 처음 발견한 빈 공간
				else if(map[r][c] == '.' && empty == -1) empty = r;
				// 뿌요가 있는 자리인데, 빈 공간이 있을 경우
				else if(map[r][c] != '.' && empty != -1) {
					map[empty][c] = map[r][c];
					map[r][c] = '.';
					empty--;
				}
			}
		}
		
	}

	private static boolean burst(int r, int c, char color) {
		
		list = new ArrayList<>();
		checked = new boolean[R][C];
		q = new LinkedList<>();
		
		list.add(new Point(r, c));
		checked[r][c] = true;
		q.add(new Point(r, c));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int rr = now.r + dr[d];
				int cc = now.c + dc[d];
				// 범위 이탈
				if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
				// 이미 확인한 구간
				if(checked[rr][cc]) continue;
				// 같은 색 뿌요일 경우
				if(map[rr][cc] == color) {
					list.add(new Point(rr, cc));
					q.add(new Point(rr, cc));
					checked[rr][cc] = true;
				}
			}
		}
		
		// 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있다면
		if(list.size() >= 4) {
			// 연결된 같은 색 뿌요들이 한꺼번에 없어짐
			for (Point p : list) map[p.r][p.c] = '.';
			return true;
		} else {
			// 4개 이상 연결되지 않았다면
			return false;
		}
	}
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
