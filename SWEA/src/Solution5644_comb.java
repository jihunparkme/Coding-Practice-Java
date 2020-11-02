import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5644_comb {

	static int M, aCnt; // 시간, 충전소개수
	static int[] pathA, pathB, playerA, playerB;
	static int[][] ap;
	static int[] dx = { 0, 0, 1, 0, -1 }, dy = { 0, -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		playerA = new int[2];
		playerB = new int[2];

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			aCnt = Integer.parseInt(st.nextToken());

			// 두 플레이어의 초기 위치 세팅
			playerA[0] = playerA[1] = 1;
			playerB[0] = playerB[1] = 10;

			pathA = new int[M + 1]; // 0초. 즉, 처음 출발 위치에서도 처리를 하도록
			pathB = new int[M + 1];
			ap = new int[aCnt][4];

			// 사용자 A의 이동 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				pathA[i] = Integer.parseInt(st.nextToken());
			}
			// 사용자 B의 이동 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				pathB[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < aCnt; i++) {
				st = new StringTokenizer(br.readLine());
				ap[i][0] = Integer.parseInt(st.nextToken()); // x
				ap[i][1] = Integer.parseInt(st.nextToken()); // y
				ap[i][2] = Integer.parseInt(st.nextToken()); // 충전가능 거리
				ap[i][3] = Integer.parseInt(st.nextToken()); // 충전량
				
			}
			
			System.out.println("#"+tc+" "+process());
		}

	}
	/* 매 시간마다 두 플레이어의 충전량의 합의 최댓값을 구하고
	 * 그 값을 모든 시간동안 누적
	 */
	private static int process() {
		
		ArrayList<Integer> apListA, apListB;
		int totalSum = 0;
		int time = 0;
		
		while(time <= M) {
			// 두 플레이어를 해당 시간의 이동정보에 맞게 이동
			playerA[0] += dx[pathA[time]];
			playerA[1] += dy[pathA[time]];
			
			playerB[0] += dx[pathB[time]];
			playerB[1] += dy[pathB[time]];
			
			// 두 플레이어의 자신의 위치 기준으로 충전가능한 충전소 리스트 가져오기
			apListA = getAp(playerA[0], playerA[1]);
			apListB = getAp(playerB[0], playerB[1]);
			
			totalSum += getCharge(apListA, apListB);
			time++;
		}
		
		return totalSum;
		
	}
	
	private static int getCharge(ArrayList<Integer> apListA, ArrayList<Integer> apListB) {
		
		int max = 0, tmp = 0;
		
		int aSize = apListA.size(), bSize = apListB.size();
		if(aSize == 0 && bSize == 0) return 0;
		else if(aSize == 0) return getMaxPower(apListB); // 플레이어 B만 충전 가능
		else if(bSize == 0) return getMaxPower(apListA); // 플레이어 A만 충전 가능
		
		// 플레이어 A, B 모두 충전가능한 상황은 다 조합을 고려
		for (Integer a : apListA) {
			for (Integer b : apListB) {
				if(a != b) tmp = ap[a][3] + ap[b][3];
				else tmp = Math.max(ap[a][3], ap[b][3]);
				
				if(max < tmp) max = tmp;
			}
		}
		
		return max;
	}
	
	private static int getMaxPower(ArrayList<Integer> apList) {
		int max = 0;
		for (Integer a : apList) {
			if(max < ap[a][3]) max = ap[a][3];
		}
		
		return max;
	}
	
	// 충전 가능한 곳 탐색
	private static ArrayList<Integer> getAp(int x, int y) {
		
		ArrayList<Integer> apList = new ArrayList<>();
		int d = 0;
		
		for (int a = 0; a < aCnt; a++) {
			d = Math.abs(ap[a][0] - x) + Math.abs(ap[a][1] - y);
			if(d <= ap[a][2]) apList.add(a);
		}
		
		return apList;
	}

}














