import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142 {
	
	static final int max = Integer.MAX_VALUE;
	
	static int N, M, cntSpace, minTime, map[][];
	static List<Point> virusZone;
	static Point[] virus;
	static boolean[][] visited;
	static Queue<Point> q;
	static int[] dr= {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 연구소 크기
		M = Integer.parseInt(st.nextToken());	// 놓을 수 있는 바이러스의 개수
		map = new int[N][N];
		virus = new Point[M];
		virusZone = new ArrayList<>(); // 바이러스를 놓을 수 있는 칸
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 빈 공간일 경우
				if(map[i][j] != 1) cntSpace++;
				// 바이러스를 놓을 수 있는 공간일 경우
				if(map[i][j] == 2) virusZone.add(new Point(i, j));
			}
		}
		
		minTime = max;
		cntSpace -= M; // 바이러스를 놓은 공간은 제외 
		process(0, 0);
		
		// 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우
		if(minTime == max) System.out.println(-1);
		else System.out.println(minTime);
	}
	
	private static void process(int cnt, int start) {
		
		// 모든 바이러스를 설치
		if(cnt == M) {
			int time = spreadVirus();
			minTime = Math.min(minTime, time);
			
			return;
		}
				
		for (int i = start; i < virusZone.size(); i++) {
			// 이 구역에 바이러스를 설치
			virus[cnt] = virusZone.get(i);
			
			process(cnt + 1, i + 1);
		}
	}

	private static int spreadVirus() {
		
		int cntSpread = 0, time = 0, tmp = 0;
		visited = new boolean[N][N];
		q = new LinkedList<>();
		// 초기 설치된 바이러스의 위치
		for (int i = 0; i < M; i++) {
			q.add(virus[i]);
			visited[virus[i].r][virus[i].c] = true;
		}

		// 바이러스 확산
		while(!q.isEmpty()) {
			
			int size = q.size();
			boolean space = false;
			
			while(size-- > 0) {
				Point now = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int rr = now.r + dr[d];
					int cc = now.c + dc[d];
					// 범위를 벗어나면 pass
					if(rr < 0 || cc < 0 || rr >= N || cc >= N) continue;
					// 벽이거나 이미 방문한 곳이면 pass
					if(map[rr][cc] == 1 || visited[rr][cc]) continue;
					// 빈 칸이면 바이러스로 감염시키고 Queue에 add
					if(map[rr][cc] == 0) space = true;
					visited[rr][cc] = true;
					cntSpread++;
					q.add(new Point(rr,cc));
				}
			}
			
			if(!space) tmp++;
			else {
				time += ++tmp;
				tmp = 0;
			}
		}
		
		// 모든 빈 칸에 바이러스가 퍼졌다면
		if(cntSpread == cntSpace) return time;
		return max;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
}
