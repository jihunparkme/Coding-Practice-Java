import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991 {

	static int W, H, cntDirty, map[][];
	static boolean visited[][][];
	static Point start;
	static Queue<Point> q;
	
	static int[] dr= {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken()); // 방의 가로
			H = Integer.parseInt(st.nextToken()); // 방의 세로
			if(W == 0 && H == 0) break;
			
			map = new int[H][W];
			visited = new boolean[1<<11][H][W]; // 더러운 칸의 개수는 10개를 넘지 않음
			cntDirty = 0;
			
			for (int i = 0; i < H; i++) {
				char tmp[] = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					map[i][j] = tmp[j];
					// 로봇의 초기 위치
					if(map[i][j] == 'o') {
						start = new Point(i, j, 0);
						map[i][j] = '.';
					}
					// 더러운 칸에 각 번호를 부여
					else if(map[i][j] == '*') {
						map[i][j] = ++cntDirty;
					}
				}
			}
			
			System.out.println(process());
		}
	}

	private static int process() {
		
		int time = 0;
		
		while(cntDirty-- > 0) {
			
			int res = clean();
			// 더러운 칸을 청소했다면 해당 위치에서 다시 시작
			if(res > 0) time += res;
			// 청소할 수 없는 더러운 칸이 있을 경우 
			else return -1;
		}
		
		return time;
	}

	private static int clean() {
		
		int time = 0;
		q = new LinkedList<>();
		q.add(start);
		visited[start.key][start.r][start.c] = true;
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			time++;
			
			while(size-- > 0) {
				
				Point now = q.poll();
				for (int d = 0; d < 4; d++) {
					int rr = now.r + dr[d];
					int cc = now.c + dc[d];
					// 범위 초과
					if(rr < 0 || cc < 0 || rr >= H || cc >= W) continue;
					// 이미 방문한 칸이거나 가구가 있을 경우
					if(visited[now.key][rr][cc] || map[rr][cc] == 'x') continue;
					
					// 더러운 칸을 발견했을 경우
					if(map[rr][cc] > 0 && map[rr][cc] <= 10) {
						// 청소 후 새로운 시작 위치를 설정
						int newKey = now.key | (1 << map[rr][cc]);
						visited[newKey][rr][cc] = true;
						start = new Point(rr, cc, newKey);
						map[rr][cc] = '.';
						
						return time;
					} 
					// 빈칸일 경우
					else {
						visited[now.key][rr][cc] = true;
						q.add(new Point(rr, cc, now.key));
					}
				}
			}
		}
		
		// 청소할 수 없는 더러운 칸이 존재 
		return -1;
	}

	static class Point {
		int r, c, key;

		public Point(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}
	}
	
}


/*
1. 각 칸(초기 로봇 위치 + 더러운 칸)간의 거리 구하기 O(10^2)
2. MST 알고리즘 적용
*/