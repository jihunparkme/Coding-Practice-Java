import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2178_dfs {

	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static int N, M, res = 987654321, map[][];
	static boolean visited[][];
	
	public static void process(int x, int y, int sum) {
		// 도착지점에 도달했을 때 이동거리
		if(x == N-1 && y == M-1) {
			res = Math.min(res, sum);
			return;
		}
		if(map[x][y] < sum) return;
		
		map[x][y] = sum;
		for (int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			// 범위 넘어가면 패스
			if(xx < 0 || yy<0 || xx>=N || yy>=M) continue;
			// 벽이거나 이미 방문한 곳이라면 pass
			if(map[xx][yy] == 0 || visited[xx][yy]) continue;
			// 지나가지 않은 길이라면 지나가자
			visited[xx][yy] = true;
			process(xx, yy, sum + 1);
			visited[xx][yy] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int x = 0; x < N; x++) {
			String str = br.readLine();
			for (int y = 0; y < M; y++) {
				map[x][y] = ((str.charAt(y)) - '0') * 987654321;
			}
		}
	
		visited[0][0] = true;
		process(0, 0, 0);
		System.out.println(res + 1);
	}

}

// 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸