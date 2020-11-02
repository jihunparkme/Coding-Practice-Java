import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5644_v2 {

	static int M, A;
	static int[] moveA, moveB;
	static Point personA, personB;
	static int[] dx = { 0, 0, 1, 0, -1 }, dy = { 0, -1, 0, 1, 0 };
	static AP[] Aps;
	static class AP {
		// c: 충전범위, p: 성능
		int x, y, scope, perfom;

		public AP(int x, int y, int scope, int perfom) {
			this.x = x;
			this.y = y;
			this.scope = scope;
			this.perfom = perfom;
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 총 이동시간
			A = Integer.parseInt(st.nextToken()); // BC의 개수
			moveA = new int[M + 1]; // 초기 위치부터 충전 가능
			moveB = new int[M + 1];
			Aps = new AP[A];
			
			// 사용자 A의 이동 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			// 사용자 B의 이동 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			// AP 정보
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				Aps[i] = new AP(x, y, c, p);
			}
			
			// A, B의 초기 위치 세팅
			personA = new Point(1, 1);
			personB = new Point(10, 10);
			
			System.out.println("#" + tc + " " + process());
		}

	}

	private static int process() {

		int time = 0, sum = 0;
		ArrayList<Integer> apListA, apListB;
		
		while(time <= M) {
			
			// 이동 (초기 위치부터 충전 가능)
			personA = new Point(personA.x + dx[moveA[time]], personA.y + dy[moveA[time]]);
			personB = new Point(personB.x + dx[moveB[time]], personB.y + dy[moveB[time]]);
			
			// 이동한 A, B 각 위치를 기준으로 충전 가능한 충전소 리스트
			apListA = getAp(personA.x, personA.y);
			apListB = getAp(personB.x, personB.y);
			
			sum += getCharge(apListA, apListB);
			++time;
		}
		
		return sum;
	}

	private static int getCharge(ArrayList<Integer> apListA, ArrayList<Integer> apListB) {
		
		int max = 0, tmp = 0;
		
		int aSize = apListA.size(), bSize = apListB.size();
		// A, B 모두 AP를 이용할 수 있는 위치에 없다면
		if(aSize == 0 && bSize == 0) return 0;
		// B만 충전할 수 있는 위치에 있다면
		else if(aSize == 0) return getMaxPower(apListB);
		// A만 충전할 수 있는 위치에 있다면
		else if(bSize == 0) return getMaxPower(apListA);
		
		// A, B 모두 충전할 수 있는 위치에 있다면
		for (Integer a: apListA) {
			for (Integer b : apListB) {
				// A, B 가 이용하는 AP가 다를 경우
				if(a != b) tmp = Aps[a].perfom + Aps[b].perfom;
				// 같은 충전소를 사용한다면 최대 성능 저장 
				// (한 사람은 충전소를 이용하지 않을 경우도 고려)
				else tmp = Math.max(Aps[a].perfom, Aps[b].perfom);
				
				max = Math.max(max, tmp);
			}
		}
		
		return max;
	}

	private static int getMaxPower(ArrayList<Integer> apList) {
		
		int max = 0;
		// 이용할 수 있는 AP 중 가장 성능이 좋은 AP를 이용
		for (Integer a : apList) {
			max = Math.max(max, Aps[a].perfom);
		}
		
		return max;
		
	}

	private static ArrayList<Integer> getAp(int x, int y) {
		
		ArrayList<Integer> apList = new ArrayList<>();
		int dist = 0;
		
		for (int n = 0; n < A; n++) {
			// 현재 위치와 AP와의 거리 측정
			dist = Math.abs(Aps[n].x - x) + Math.abs(Aps[n].y - y);
			// AP의 범위 안에 위치하여 충전할 수 있다면 list에 추가
			if(dist <= Aps[n].scope) apList.add(n);
		}
		
		return apList;
	}

}
