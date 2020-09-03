import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution1767_teach_Optim {

	// 판크기, 멕시노드판, 최대코어수, 최소전선길이, 처리할코어수
	public static int N, map[][], max, min, totalCnt;
	public static ArrayList<int[]> list;
	public static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			// 처리해야할 가장자리가 아닌 코어들을 저장할 리스트
			list = new ArrayList<>();
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 가장자리에 있는 코어는 pass
					if ((i == 0 || i == N - 1 || j == 0 || j == N - 1) && map[i][j] == 1) continue;
					// 가장자리에 있지 않은 코어 찾기
					if (map[i][j] == 1) {
						list.add(new int[] { i, j });
						++totalCnt;
					}
				}
			} // end input

			go(0, 0, 0);
			System.out.println("#" + tc + " " + min);

		} // end TC

	} // end main

	/**
	 * 
	 * @param idx 처리할 코어의 index
	 * @param cCnt 직전까지 포함된 코어 수
	 * @param lCnt 전선 길이의 합
	 */
	private static void go(int idx, int cCnt, int lCnt) {

		// 현재까지 연결된 코어수 + 앞으로 처리해야할 남은 코어수 : 기대할 수 있는 최대 코어 수
		// 기대할 수 있는 최대 코어수가 임시 해보자 작다면 진행이 의미 없음
		if(cCnt + totalCnt - idx < max) return;
		
		// 모든 코어를 다 고려했다면
		if(idx == totalCnt) {
			if(max < cCnt) {
				max = cCnt;
				min = lCnt;
			} else if(max == cCnt) { // 코어 갯수가 같다면 최소 길이의 전선으로
				min = Math.min(min, lCnt);
			}
			
			return;
		}
		
		int[] cur = list.get(idx);
		int r = cur[0];
		int c = cur[1];
		// 해당 코어 선택
		// 4방향의 직선으로 전선을 놓어보는 시도
		for (int d = 0; d < 4; d++) {
			// 해당 방향으로 전선 놓는게 가능한지 체크
			if(isAvailable(r, c, d)) {
				// 가능하다면 전선 놓기 : 멕시노스 판에 2로 셋팅
				int len = setStatus(r, c, d, 2);
				// 다음 코어로 넘어가기
				go(idx + 1, cCnt + 1, lCnt + len);
				// 놓았던 전선 지우기(되돌리기) : 멕시노드 판에 0으로 셋팅
				setStatus(r, c, d, 0);
			}
		}
		// 해당 코어 비선택
		// 아무런 전선도 놓지 않고 다음 코어로 넘어가기
		go(idx + 1, cCnt, lCnt);
	}
	
	// 현재 코어 위치에서 해당 방향으로 전선을 놓는게 가능한지 체크
	private static boolean isAvailable(int r, int c, int d) {
		int rr = r, cc = c;
		while(true) {
			rr += dr[d];
			cc += dc[d];
			// 범위를 벗어나는 경우, 가장자리까지 전선이 연결된 상황
			if(rr < 0 || cc < 0 || rr >= N || cc >= N) break;
			// 1: 코어, 2: 전선
			if(map[rr][cc] >= 1) return false; 
		}
		
		return true;
	}
	
	// 현재 코어 위치에서 해당 방향으로 전선을 놓거나(s=2) 지우는(s=0) 작업
	private static int setStatus(int r, int c, int d, int s) {
		int rr = r, cc = c, cnt = 0;
		while(true) {
			rr += dr[d];
			cc += dc[d];
			// 경계를 벗어나면 셋팅이 끝난 상황
			if(rr < 0 || cc < 0 || rr >= N || cc >= N) break;
			map[rr][cc] = s;
			++cnt;
		}
		
		return cnt;
	}
}
