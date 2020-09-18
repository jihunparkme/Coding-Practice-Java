import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2578 {

	static int map[][], nums[][], N = 5;
	static int[] dx = {1, 0, 0, 0, -1, 1, -1, 1}, dy = {0, 0, -1, 1, 1, -1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[N][N];
		nums = new int[N][N];
		// 빙고판 작성
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 부를 번호
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		out:for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				++cnt;
				erase(nums[i][j]);
				// 3빙고을 했을 경우
				if(bingo() >= 3) break out;
			}	
		}
		
		System.out.println(cnt);
	}

	private static int bingo() {
		int cntBingo = 0;
		boolean isBingo;
		
		// 가로줄 확인
		for (int i = 0; i < N; i++) {
			isBingo = true;
			
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 0) {
					isBingo = false;
					break;
				}
			}
			// 빙고!
			if(isBingo) cntBingo++;
		}
		
		// 세로줄 확인
		for (int i = 0; i < N; i++) {
			isBingo = true;
			
			for (int j = 0; j < N; j++) {
				if(map[j][i] != 0) {
					isBingo = false;
					break;
				}
			}
			// 빙고!
			if(isBingo) cntBingo++;
		}
		
		// 하향 대각선 확인
		isBingo = true;
		for (int i = 0; i < N; i++) {
			if(map[i][i] != 0) {
				isBingo = false;
				break;
			}			
		}
		// 빙고!
		if(isBingo) cntBingo++;
		
		// 상향 대각선 확인
		isBingo = true;
		for (int i = N - 1; i >= 0; i--) {
			if(map[i][(N - 1) - i] != 0) {
				isBingo = false;
				break;
			}			
		}
		// 빙고!
		if(isBingo) cntBingo++;
		
		return cntBingo;
	}

	private static void erase(int a) {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == a) {
					map[i][j] = 0;
					return;
				}
			}
		}
		
	}

}
