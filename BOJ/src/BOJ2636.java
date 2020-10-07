import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {

	static int R, C, map[][], time, res, total;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 초기 치즈 칸을 count
				if(map[i][j] == 1) total++;
			}
		}
		
		go();
		System.out.println(time + "\n" + res);
	}

	private static void go() {

		res = total;
		// 치즈가 모두 없어질 때까지 반복
		while(total != 0) {
			++time;
			
			// 공기가 퍼지면서 치즈와 닿으면 녹여주자.
			melt();
			
			// 남은 치즈 칸 개수 갱신 
			if(total != 0) res = total;
		}
		
	}

	private static void melt() {
		
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		// (0,0)은 무조건 공기
		q.add(new Point(0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];
				// 범위를 벗어나면
				if(xx < 0 || xx >= R || yy <0 || yy >= C) continue;
				// 이미 방문한 좌표
				if(visited[xx][yy]) continue;
				// 치즈가 있을 경우
				if(map[xx][yy] == 1) {
					// 치즈를 녹여주자
					map[xx][yy] = 0;
					visited[xx][yy] = true;
					total--;
					continue;
				} else { // 공기일 경우에만 queue에 add
					visited[xx][yy] = true;
					q.add(new Point(xx, yy));
				}
			}
		}
		
	}

}

