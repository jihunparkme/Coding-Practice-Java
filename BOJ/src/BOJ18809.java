import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18809 {

	static int N, M, G, R, res, allDrop, map[][];
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	static ArrayList<Point> area;
	static State[] select;
	static class State {
		int x, y;
		int color;	// 1: 초록, 2: 빨강
		
		public State(int x, int y, int color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}

		@Override
		public String toString() {
			return "State [x=" + x + ", y=" + y + ", color=" + color + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());	// 행
		M = Integer.parseInt(st.nextToken());	// 열
		G = Integer.parseInt(st.nextToken());	// 초록색 배양액 개수
		R = Integer.parseInt(st.nextToken());	// 빨간색 배양액 개수
		allDrop = G + R;
		map = new int[N][M];
		select = new State[allDrop];
		area = new ArrayList<>();
		// 정원 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 배양액을 뿌릴 수 있는 땅을 저장
				if(map[i][j] == 2) area.add(new Point(i, j));
			}
		}
		
		go(0, 0, 0);
		System.out.println(res);
	}

	private static void go(int cnt, int cntG, int cntR) {
		if(cntG + cntR > area.size() - cnt) return;
		if(cnt == area.size()) return;
		// 모든 배양액을 사용했을 경우
		if(cnt == allDrop) {
			// 배양액이 퍼져나간다.
			// 피울 수 있는 꽃의 최대 개수
			res = Math.max(res, spread());
//			System.out.println(Arrays.toString(select));
			return;
		}
		
		Point now = area.get(cnt);
		
		// 여기에 초록색 배양액 뿌리기
		if(cntG < G) {
			select[cnt] = new State(now.x, now.y, 1);
			go(cnt + 1, cntG + 1, cntR);
		}
		
		// 여기에 빨간색 배양액 뿌리기
		if(cntR < R) {
			select[cnt] = new State(now.x, now.y, 2);
			go(cnt + 1, cntG, cntR + 1);
		}
		
		// 여기에 아무것도 뿌리지 않기
		go(cnt + 1, cntG, cntR);
	}

	private static int spread() {
		
		int[][] visitedGrn = new int[N][M];
		int[][] visitedRed = new int[N][M];
		int cnt = 0, time = 1;
		// 큐에 배양액에 뿌려져있는 좌표를 넣고
		Queue<State> q = new LinkedList<>();
		for (int i = 0; i < allDrop; i++) {
			State tmp = select[i];
			q.add(new State(tmp.x, tmp.y, tmp.color));
			// 배양액에 뿌려진 곳은 1로 초기화
			if(tmp.color == 1) visitedGrn[tmp.x][tmp.y] = time;
			else visitedRed[tmp.x][tmp.y] = time;
		}
		
		// 배양액이 모두 퍼질 때까지
		while(!q.isEmpty()) {
			
			int size = q.size();
			time++;
			
			for (int s = 0; s < size; s++) {
				State now = q.poll();
				
				// 4방 탐색
				for (int d = 0; d < 4; d++) {
					int xx = now.x + dx[d];
					int yy = now.y + dy[d];
					// 범위를 벗어날 경우, 호수가 있을 경우 pass
					if(xx < 0 || yy < 0 || xx >= N || yy >= M || map[xx][yy] == 0) continue;
					// 이미 배양액이 도달한 적이 있다면 pass
					if((now.color == 1 && visitedGrn[xx][yy] >= 1) ||
							(now.color == 2 && visitedRed[xx][yy] >= 1)) continue;
					
					// 초록색 배양액과 빨간색 배양액이 동일한 시간에 도달한 땅에서는 두 배양액이 합쳐져서 꽃이 피어난다.
					// 꽃이 피어난 땅에서는 배양액이 사라지기 때문에 더 이상 인접한 땅으로 배양액을 퍼트리지 않는다.
					if((now.color == 1 && time == visitedRed[xx][yy]) ||
							(now.color == 2 && time == visitedGrn[xx][yy])) {
						cnt++;
						visitedGrn[xx][yy] = 100;
						visitedRed[xx][yy] = 100;
						continue;
					}

					// 해당 색 배양액이 도달한 적이 없다면 퍼진다
					// queue에 ass
					visitedGrn[xx][yy] = time;
					q.add(new State(xx, yy, now.color));
				}
			}
		}

		return cnt;
	}
}


// 0은 호수, 1은 배양액을 뿌릴 수 없는 땅, 2는 배양액을 뿌릴 수 있는 땅