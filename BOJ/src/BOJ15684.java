import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {

	static int N, M, H;
	static boolean ladder[][], isFinish;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로선 개수
		M = Integer.parseInt(st.nextToken()); // 가로선 개수
		H = Integer.parseInt(st.nextToken()); // 놓을 수 있는 선
		ladder = new boolean[H][N];

		// 가로선 정보
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			ladder[a][b] = true;
			ladder[a][b + 1] = true;
		}

		System.out.println(go());
	}

	private static int go() {
		
		int i = 0;
		// 놓을 수 있는 가로선 개수
		for (i = 0; i <= 3; i++) {

			// 0일 경우는 바로 확인 후 continue;
			if(i == 0) {
				// 조작 성공?!
				if(start()) break;
				else continue;
			}
			
			// 사타리를 놓아보자.
			findPosition(0, i, 0);
			// 조작 성공?!
			if(isFinish) break;
		}
		
		// 정답이 3보다 큰 값이면 -1을 출력
		if(i > 3) return -1;
		else return i;
		
	}

	private static void findPosition(int start, int n, int cnt) {

		if(isFinish) return;
		// 놓을 위치를 정했다면
		if (cnt == n) {
			// 사타리를 타고 내려가보자.
			if(start()) isFinish = true;
			return;
		}

		for (int i = start; i < N * H; i++) {
			int x = i / N;
			int y = i % N;
			// 가로선을 놓을 수 있는 범위를 넘어가면
			if(y >= N - 1) continue;
			// 가로선을 놓을 수 없는 자리면 pass
			if(ladder[x][y] || ladder[x][y + 1] || (y - 1 >= 0 && ladder[x][y - 1])) continue;
			
			// 여기에 가로 선을 놓아보자.
			setLine(x, y, true);
			findPosition(i + 1, n, cnt + 1);

			// 놓았을 경우 되돌려야지.
			setLine(x, y, false);
			
			// 여기에 가로 선을 안 놓을래
			findPosition(i + 1, n, cnt);
		}
	}

	private static void setLine(int x, int y, boolean state) {
		
		ladder[x][y] = state;
		ladder[x][y + 1] = state;
		
	}

	private static boolean start() {
		
		// i번부터 내려가보자.
		for (int c = 0; c < N - 1; c++) {
			
			int now = c;
			for (int r = 0; r < H; r++) {
				// 가로선이 없으면 내려가기
				if(!ladder[r][now]) continue;
				// 가로선이 있으면
				else {
					// 어느 방향(좌/우)에 연결되어있는지 확인
					// 왼쪽으로 연결?
					if(now - 1 >= 0 && ladder[r][now - 1]) now -= 1;
					// 오른쪽으로 연결?
					else if(ladder[r][now + 1]) now += 1;
				}
			}
			
			// 다 내려왔을 때 현재 위치 확인
			if(c != now) return false;
		}
		
		return true;
	}

}
