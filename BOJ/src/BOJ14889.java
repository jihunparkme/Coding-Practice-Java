import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {

	static int T, N, res = Integer.MAX_VALUE, ability[][], sum[];
	static boolean isSTeam[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		ability = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		isSTeam = new boolean[N];
		setTeam(0, 0);
		
		System.out.println(res);
	}

	private static void setTeam(int idx, int cnt) {
		// 나올 수 있는 최소값이 이미 나왔으면 더이상의 조합을 확인할 필요가 없음
		if (res == 0) return;
		// 모든 직원을 다 확인했는데, 스타트팀이 만들어지지 않았다면
		if (idx == N) return;

		// 스타트팀이 N/2명의 팀원을 모두 선택했다면 나머지는 링크팀
		if (cnt == N / 2) {
			int STeam = 0, LTeam = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 두 직원 모두 스타트 팀이라면
					if (isSTeam[i] && isSTeam[j]) STeam += ability[i][j];
					// 링크팀 소속일 경우
					if (!isSTeam[i] && !isSTeam[j]) LTeam += ability[i][j];
					
				}
			}

			res = Math.min(res, Math.abs(STeam - LTeam));
			return;
		}

		// 이 직원을 스타트팀으로 선택해보자.
		isSTeam[idx] = true;
		setTeam(idx + 1, cnt + 1);
		// 그냥 선택 안 할래.
		isSTeam[idx] = false;
		setTeam(idx + 1, cnt);
	}

}