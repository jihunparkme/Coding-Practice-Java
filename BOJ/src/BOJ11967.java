import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11967 {

	static int N, M;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 };
	static ArrayList<Point>[][] switchs;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		switchs = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				switchs[i][j] = new ArrayList<>();
			}
		}
		// 스위치 정보
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			switchs[x][y].add(new Point(a, b));
		}

		System.out.println(process());
	}

	private static int process() {

		boolean[][] visited = new boolean[N][N]; // 방문한 방
		boolean[][] isLight = new boolean[N][N]; // 불이 켜진 방
		boolean[][] isMove = new boolean[N][N]; // 이동할 수 있는 방
		
		int cnt = 1;
		// (0, 0)에서 출발
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		isLight[0][0] = true;
		visited[0][0] = true;
	
		while (!q.isEmpty()) {
			Point now = q.poll();
			// 현재 방에서 이동할 수 있는 방 상태를 체크
			for (int d = 0; d < 4; d++) {
				int rr = now.r + dr[d];
				int cc = now.c + dc[d];
				// 범위를 넘어갈 경우
				if(rr < 0 || cc < 0 || rr >= N || cc >= N) continue;
				
				isMove[rr][cc] = true;
			}
			
			// 스위치를 눌러볼까?
			for (Point p : switchs[now.r][now.c]) {
				// 불이 꺼져있는 방이라면
				if(!isLight[p.r][p.c]) {
					// 불을 켜고
					isLight[p.r][p.c] = true;
					cnt++;
					
					// 이동할 수 있는 방이라면 queue에 추가
					if(isMove[p.r][p.c] && !visited[p.r][p.c]) {
						visited[p.r][p.c] = true;
						q.add(new Point(p.r, p.c));
					}
				}
			}
			
			// 현재 위치에서 이동할 수 있는 방으로 이동
			for (int d = 0; d < 4; d++) {
				int rr = now.r + dr[d];
				int cc = now.c + dc[d];
				// 범위를 넘어갈 경우
				if(rr < 0 || cc < 0 || rr >= N || cc >= N) continue;
				// 이미 방문했거나 불이 꺼져있거나 이동할 수 없는 방이라면
				if(visited[rr][cc] || !isLight[rr][cc] || !isMove[rr][cc]) continue;

				visited[rr][cc] = true;
				q.add(new Point(rr, cc));
			}
		}

		return cnt;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

}
