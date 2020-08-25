import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686 {

	static int N, M, res = 987654321;
	static ArrayList<Point> chicken, home;
	static Point[] selected;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		selected = new Point[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				// 치킨집 목록
				if(val == 2) chicken.add(new Point(i, j));
				// 집 목록
				if(val== 1) home.add(new Point(i, j));
			}
		}

		selectChicken(0, 0);
		System.out.println(res);
	}

	private static void selectChicken(int start, int cnt) {
		// cnt개의 치킨집을 골랐을 때
		if(cnt == M) {
			// 모든 집에 대한 정보
			int all = 0;
			// 모든 집들부터
			for (int h = 0; h < home.size(); h++) {
				int dist = 987654321; // 현재 집에 대한 정보
				// 골라진 치킨집들과 거리를 계산하면서
				for (int c = 0; c < M; c++) {
					int tmp = Math.abs(home.get(h).x - selected[c].x);
					tmp += Math.abs(home.get(h).y - selected[c].y);
					// 가장 가까운 거리를 찾자
					dist = Math.min(dist, tmp);
				}
				// 현재 집과 가장 가까운 치킨집과의 거리를 누적
				all += dist;
			}
			// 모든 집에 대한 선택된 치킨집들로부터 가장 가까운 거리의 합
			res = Math.min(res, all);
			return;
		}
		// 더이상 고를게 없을 경우
		if(start == chicken.size()) return;
			
		// 조합 구하기
		selected[cnt] = chicken.get(start);
		// start idx에 있는 치킨집을 선택할 경우
		selectChicken(start + 1, cnt + 1);
		// start idx에 있는 치킨집을 선택하지 않을 경우
		selectChicken(start + 1, cnt);
	}
}

/*
0 빈칸
1 집
2 치킨집
*/