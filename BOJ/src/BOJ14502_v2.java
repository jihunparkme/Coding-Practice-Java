import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_v2 {
	
	static int N, M, wallCnt = 0, res = 0, map[][];
	static boolean visited[][];
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static Queue<Point> q;
	static ArrayList<Point> virus;
	
	public static void setupWall(int cnt) {
		// 벽을 세 개 모두 설치했다면
		if(cnt == 3) {
			// 바이러스를 퍼뜨려보자.
			spreadVirus();
			return;
		}
			
		for (int i = 0; i < N*M; i++) {
			// 빈 칸일 경우
			if(map[i/M][i%M] == 0) {
				// 벽을 세워볼까
				map[i/M][i%M] = 1;
				setupWall(cnt+1);
				// 벽을 그냥 안 세울래
				map[i/M][i%M] = 0;
			}
		}
	}
	
	public static void spreadVirus() {
		int virusCnt = 0;
		visited = new boolean[N][M];
		q = new LinkedList<>();
		// 바이러스의 좌표들을 하나씩 확인하면서
		for (int i = 0; i < virus.size(); i++) {
			Point v = virus.get(i);
			// Queue에 넣어주고
			q.add(new Point(v.x, v.y));
			// 바이러스를 퍼뜨려보자.
			while(!q.isEmpty()) {
				Point now = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int xx = now.x + dx[d];
					int yy = now.y + dy[d];
					// 범위를 벗어나면 pass
					if(xx < 0 || yy < 0 || xx >= N || yy >= M) continue;
					// 빈곳이 아니거나 이미 방문한 곳이면 pass
					if(map[xx][yy] != 0 || visited[xx][yy]) continue;
					// 빈 칸이면 바이러스로 감염시키고 Queue에 add
					visited[xx][yy] = true;
					virusCnt++;
					q.add(new Point(xx,yy));
				}
			}
		}
		// 바이러스가 모두 퍼졌을 때, 안전 영역의 크기를 count
		res = Math.max(res, (N * M) - wallCnt - 3 - virusCnt - virus.size());
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 세로
		M = Integer.parseInt(st.nextToken());	// 가로
		map = new int[N][M];
		virus = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 벽 개수
				if(map[i][j] == 1) wallCnt++;
				// 바이러스의 위치 저장
				if(map[i][j] == 2) virus.add(new Point(i, j));
			}
		}
		
		// 재귀로 3개의 벽을 설치해보자.
		setupWall(0);

		System.out.println(res);
	}
}
