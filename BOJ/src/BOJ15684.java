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
		M = Integer.parseInt(st.nextToken()); // 기존에 놓인 가로선의 개수
		H = Integer.parseInt(st.nextToken()); // 가로 점선 개수
		ladder = new boolean[H][N];

		// 가로선 정보
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			// a번과 b번 사이에 가로선
			ladder[a][b] = true;
		}

		System.out.println(go());
	}

	private static int go() {
		
		int i = 0;
		// 추가로 놓을 수 있는 가로선 개수는 최대 3개
		for (i = 0; i <= 3; i++) {

			// 0일 경우는 바로 결과 확인
			if(i == 0) {
				// 조작 성공?!
				if(check()) break;
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

	private static void findPosition(int idx, int n, int cnt) {

		if(isFinish) return;
		// 놓을 위치를 정했다면
		if (cnt == n) {
			// 사타리를 타고 내려가보자.
			if(check()) isFinish = true;
			return;
		}

		for (int i = idx; i < H * N; i++) {
			int x = i / N;
			int y = i % N;
			// 가로선을 놓을 수 있는 범위를 넘어가면
			if(y >= N - 1) continue;
			// 가로선을 놓을 수 없는 자리면 pass
			// 좌 or 우측 방향에 가로선이 이미 있을 경우
			if((y - 1 >= 0 && ladder[x][y - 1]) || ladder[x][y]) continue;
			
			// 여기에 가로 선을 놓아보자.
			ladder[x][y] = true;
			findPosition(i + 1, n, cnt + 1);

			// 놓았을 경우 되돌려야지.
			ladder[x][y] = false;
		}
	}

	private static boolean check() {
		
		// c번부터 확인
		for (int c = 0; c < N; c++) {
			
			int now = c;
			
			for (int r = 0; r < H; r++) {
				// 양쪽에 가로선이 없으면 그냥 내려가기
				if(!(now - 1 >= 0 && ladder[r][now - 1]) && !ladder[r][now]) continue;
				// 가로선이 있으면
				else {
					// 어느 방향(좌/우)에 가로선이 있는지 확인
					// 왼쪽으로 연결?
					if(now - 1 >= 0 && ladder[r][now - 1]) now -= 1;
					// 오른쪽으로 연결?
					else if(ladder[r][now]) now += 1;
				}
			}
			
			// 다 내려왔을 때 현재 위치 확인
			if(c != now) return false;
		}
		
		return true;
	}

}
