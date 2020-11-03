import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1953_dfs {

	static int N, M, R, C, L, map[][];
	static int visited[][];
	static int[] dr = {-1, 0, 0, 1}, dc = {0, -1, 1, 0};
	// 구조물 타입이 연결된 방향
	// 상,좌,우,하 : 0,1,2,3
	static String[] type = {
			null,
			"0312", // 1
			"03", // 2
			"12", // 3
			"02", // 4
			"32", // 5
			"31", // 6
			"01" // 7
	};

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지하 터널의 크기
			M = Integer.parseInt(st.nextToken()); 
			R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑의 위치
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken()); // 소요된 시간
			
			map = new int[N][M];
			visited = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 언제(time) 방문했는지 체크하기 위한 visited 배열을 MAX 값으로 초기화
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			
			process(R, C, 1);
			System.out.println("#" + tc + " " + getCount());
		}
		
	}
	
	private static void process(int r, int c, int time) {
		
		// 방문 시간 저장
		visited[r][c] = time;
		// 소요 시간에 도달하면 종료
		if(time == L) return;
		
		// 구조물의 방향 정보
		String info = type[map[r][c]];
		int dir, rr, cc;
		for (int d = 0; d < info.length(); d++) {
			// 연결된 방향
			dir = info.charAt(d) - '0';
			// 연결된 다음 좌표
			rr = r + dr[dir];
			cc = c + dc[dir];
			// 범위를 벗어나거나 구조물이 없는 곳이라면 pass
			if(rr < 0 || rr >= N || cc < 0 || cc >= M || map[rr][cc] == 0) continue;
			// 다음 좌표의 구조물이 현재 방향과 연결되어있고 최근에 방문하지 않은 곳이면
			if(type[map[rr][cc]].contains(Integer.toString(3 - dir)) && visited[rr][cc] > time) {
				process(rr, cc, time + 1);
			}
		}
		
	}
	
	private static int getCount() { 
		
		int cnt = 0;
		// L 시간동안 지나온 모든 위치의 개수 count
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j] != Integer.MAX_VALUE) cnt++;
			}
		}
		
		return cnt;
	}
	
}