import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5644 {

	static int M, A;
	static int[] moveA, moveB;
	static Point personA, personB;
	static int[] dx = { 0, 0, 1, 0, -1 }, dy = { 0, -1, 0, 1, 0 };
	static AP[] aps;
	static class AP {
		// c: 충전범위, p: 성능
		int x, y, c, p;

		public AP(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
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
			aps = new AP[A];
			
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
				
				aps[i] = new AP(x, y, c, p);
			}
			
			// A, B의 초기 위치 세팅
			personA = new Point(1, 1);
			personB = new Point(10, 10);
			
			System.out.println("#" + tc + " " + process());
		}

	}

	private static int process() {

		int time = 0, sum = 0;
		while(time <= M) {
			
			// 이동 (초기 위치부터 충전 가능)
			personA = new Point(personA.x + dx[moveA[time]], personA.y + dy[moveA[time]]);
			personB = new Point(personB.x + dx[moveB[time]], personB.y + dy[moveB[time]]);
			
			// 이동한 장소에서 충전한 양의 합의 최댓값
			sum += getCharge();
			
			++time;
		}
		
		return sum;
	}

	private static int getCharge() {
		
		int max = 0;
		
		for (int a = 0; a < A; a++) {
			for (int b = 0; b < A; b++) {
				int sum = 0;
				// 해당 AP에서 충전이 가능한지 확인
				int amountA = check(a, personA.x, personA.y);
				int amountB = check(b, personB.x, personB.y);
				// A, B가 서로 다른 충전소를 사용한다면 각 충전소 성능의 합 저장
				if(a != b) sum = amountA + amountB;
				// 같은 충전소를 사용한다면 최대 성능 저장
				// (한 사람은 충전소를 이용하지 않을 경우도 고려)
				else sum = Math.max(amountA, amountB);
				
				max = Math.max(max, sum);
			}
		}
		
		return max;
	}

	private static int check(int n, int x, int y) {
		
		// 충전 가능한 범위 내에 있다면 충전소의 성능을 return, else 0
		return Math.abs(aps[n].x - x) + Math.abs(aps[n].y - y) <= aps[n].c ? aps[n].p : 0;
		
	}

}
