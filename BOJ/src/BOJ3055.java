import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055 {

	static int R, C;
	static int[] dr = {1, 0, -1, 0}, dc = {0, -1, 0, 1};
	static char map[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		Queue<Point> sQ = new LinkedList<>();
		Queue<Point> waterQ = new LinkedList<>();
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				// 고슴도치의 위치
				if(map[i][j] == 'S') {
					sQ.add(new Point(i, j));
				} else if(map[i][j] == '*') {
					waterQ.add(new Point(i, j));
				}
			}
		}

		int res = process(sQ, waterQ);
		// 비버의 굴로 이동할 수 없다면
		if(res == 0) System.out.println("KAKTUS");
		else System.out.println(res);
	}

	private static int process(Queue<Point> sQ, Queue<Point> wQ) {

		int time = 0, size = 0;
		
		while(!sQ.isEmpty()) {

			++time;
			// 물의 확장
			size = wQ.size();
			for (int i = 0; i < size; i++) {
				Point water = wQ.poll();
				
				for (int d = 0; d < 4; d++) {
					int rr = water.r + dr[d];
					int cc = water.c + dc[d];
					// 범위를 초과하거나 이미 방문
					if(rr < 0 || rr >= R || cc < 0 || cc >= C || map[rr][cc] == '*') continue;
					// 돌이거나 비버의 소굴일 경우
					if(map[rr][cc] == 'X' || map[rr][cc] == 'D') continue;
					
					map[rr][cc] = '*';
					wQ.add(new Point(rr, cc));
				}
			}
			
			// 고슴도치의 이동
			size = sQ.size();
			for (int i = 0; i < size; i++) {
				Point hedgehog = sQ.poll();
				
				for (int d = 0; d < 4; d++) {
					int rr = hedgehog.r + dr[d];
					int cc = hedgehog.c + dc[d];
					// 범위를 초과하거나 이미 방문
					if(rr < 0 || rr >= R || cc < 0 || cc >= C || map[rr][cc] == 'S') continue;
					// 돌이거나 물로 차있을 경우
					if(map[rr][cc] == 'X' || map[rr][cc] == '*') continue;
					// 비버의 소굴로 이동할 수 있다면
					if(map[rr][cc] == 'D') return time;
					
					map[rr][cc] = 'S';
					sQ.add(new Point(rr, cc));
				}
			}
		}
		
		return 0;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
}

