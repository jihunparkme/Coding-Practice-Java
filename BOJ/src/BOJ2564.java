import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2564 {

	static int R, C, K, res;
	static Pos[] stores;
	static Pos dong;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken()); // 가로
		R = Integer.parseInt(st.nextToken()); // 세로
		K = Integer.parseInt(br.readLine()); // 상점의 개수
		stores = new Pos[K + 1];
		dong = new Pos();
		int a = 0, b = 0;
		for (int i = 0; i <= K; i++) {
			stores[i] = new Pos();
			st = new StringTokenizer(br.readLine());

			// 첫째 수는 상점이 위치한 방향
			// 1 : 북쪽, 2 : 남쪽, 3 : 서쪽, 4 : 동쪽
			a = Integer.parseInt(st.nextToken());
			// 둘째 수는 상점이 블록의 북쪽 또는 남쪽에 위치한 경우 블록의 왼쪽 경계로부터의 거리
			// 상점이 블록의 동쪽 또는 서쪽에 위치한 경우 블록의 위쪽 경계로부터의 거리
			b = Integer.parseInt(st.nextToken());
			
			putInPosition(a, b, i);
		}

		// 마지막 줄에는 동근이의 위치가 상점의 위치와 같은 방식으로 주어진다.
		dong = stores[K];
				
		for (int i = 0; i < K; i++) {
			if(dong.dir == 1) {
				// 동근이가 북쪽에 위치
				if(stores[i].dir == 2) {
					int left = R + dong.y + stores[i].y;
					int right = R + (C - dong.y) + (C - stores[i].y);
					res += Math.min(left, right);
				}
				else res += Math.abs(stores[i].x - dong.x) + Math.abs(stores[i].y - dong.y); 	
			} else if(dong.dir == 2) {
				// 동근이가 남쪽에 위치
				if(stores[i].dir == 1) {
					int left = R + dong.y + stores[i].y;
					int right = R + (C - dong.y) + (C - stores[i].y);
					res += Math.min(left, right);
				}
				else res += Math.abs(stores[i].x - dong.x) + Math.abs(stores[i].y - dong.y); 	
			} else if(dong.dir == 4) {
				// 동근이가 동쪽에 위치
				if(stores[i].dir == 3) {
					int left = R + dong.y + stores[i].y;
					int right = R + (C - dong.y) + (C - stores[i].y);
					res += Math.min(left, right);
				}
				else res += Math.abs(stores[i].x - dong.x) + Math.abs(stores[i].y - dong.y); 	
			} else if(dong.dir == 3) {
				// 동근이가 서쪽에 위치
				if(stores[i].dir == 4) {
					int left = R + dong.y + stores[i].y;
					int right = R + (C - dong.y) + (C - stores[i].y);
					res += Math.min(left, right);
				}
				else res += Math.abs(stores[i].x - dong.x) + Math.abs(stores[i].y - dong.y); 	
			}
		}
		
		System.out.println(res);
	}

	private static void putInPosition(int a, int b, int i) {

		if (a == 1) {
			stores[i].x = 0;
			stores[i].y = b;
		} else if (a == 2) {
			stores[i].x = R;
			stores[i].y = b; 
		} else if (a == 3) {
			stores[i].x = b;
			stores[i].y = 0; 
		} else {
			stores[i].x = R;
			stores[i].y = b;
		}
		
		stores[i].dir = a;
	}
}

class Pos {
	int x, y;
	int dir;

	public Pos() {
		super();
	}

	public Pos(int x, int y, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

}