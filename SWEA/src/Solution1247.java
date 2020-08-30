import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1247 {

	static int T, N, res;
	static Point customers[], Office, home;
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			customers = new Point[N];
			visited = new boolean[N];
			
			Office = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 회사 좌표
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 집 좌표
					
			// 고객들의 집 좌표
			for (int i = 0; i < N; i++) 
				customers[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			res = 987654321;
			process(0, Office, 0);
			
			System.out.println("#" + tc + " " + res);
		}
	}
	
	public static void process(int cnt, Point prev, int dist) {
		// 지금까지의 거리가 이미 구해진 경로보다 크면 더이상 확인할 필요가 없음
		if(dist >= res) return;
		// 순열이 완성되었다면 거리 계산
		if(cnt == N) {
			// 마지막 들린 고객 집에서 내 집까지의 거리
			dist += Math.abs(prev.x - home.x) + Math.abs(prev.y - home.y);
			res = Math.min(res, dist);
		}
		
		// 순열 구하기
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				process(cnt + 1, customers[i], dist + Math.abs(prev.x - customers[i].x) + Math.abs(prev.y - customers[i].y));
				visited[i] = false;
			}
		}
	}

}

// 두 위치 (x1, y1)와 (x2, y2) 사이의 거리는 |x1-x2| + |y1-y2|
// 모두 방문하고 집에 돌아가는 경로 중 총 이동거리가 가장 짧은 경로