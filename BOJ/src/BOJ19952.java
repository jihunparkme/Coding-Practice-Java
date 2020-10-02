import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19952 {

	static int T, H, W, O, F, sX, sY, eX, eY, map[][];
	static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
	static class Info {
		int x, y, p;

		public Info(int x, int y, int p) {
			super();
			this.x = x;
			this.y = y;
			this.p = p;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); // 세로
			W = Integer.parseInt(st.nextToken()); // 가로
			O = Integer.parseInt(st.nextToken()); // 장애물 개수
			F = Integer.parseInt(st.nextToken()); // 초기 힘
			sX = Integer.parseInt(st.nextToken()) - 1; // 출발 행
			sY = Integer.parseInt(st.nextToken()) - 1; // 출발 열
			eX = Integer.parseInt(st.nextToken()) - 1; // 목적지 행
			eY = Integer.parseInt(st.nextToken()) - 1; // 목적지 열
			
			map = new int[H][W];
			
			// 장애물 정보 입력
			for (int i = 0; i < O; i++) {
				
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int l = Integer.parseInt(st.nextToken());
				
				map[x][y] = l;
			}
			
			if(isArrive()) System.out.println("잘했어!!");
			else System.out.println("인성 문제있어??");
		}

	}

	private static boolean isArrive() {
		
		boolean[][] visited = new boolean[H][W];
		Queue<Info> q = new LinkedList<>();
		
		visited[sX][sY] = true;
		q.add(new Info(sX, sY, F));
		while(!q.isEmpty()) {
			
			Info now = q.poll();
			// 남은 힘이 0이하인 경우 움직이지 못함
			if(now.p == 0) continue;
			
			for (int d = 0; d < 4; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];
				// 범위 확인
				if(xx < 0 || xx >= H || yy < 0 || yy >= W) continue;
				// 이미 방문
				if(visited[xx][yy]) continue;
				// 점프할 수 있는지 확인(이동할 곳의 높이 - 현재 위치한 곳의 높이)
				// 남아있는 힘이 점프해야 하는 높이보다 크거나 같으면 이동
				if(now.p < map[xx][yy] - map[now.x][now.y]) continue;
				
				// 목적지에 도착
				if(xx == eX && yy == eY) return true;
				
				// 이동이 완료될 시 남은 힘은 1씩 차감
				visited[xx][yy] = true;
				q.add(new Info(xx, yy, now.p - 1));
			}
			
		}
		
		return false;
	}

}
