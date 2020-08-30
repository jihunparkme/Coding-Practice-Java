import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5215 {

	static int N, L, maxScore;
	static int[][] material;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 재료 수
			L = Integer.parseInt(st.nextToken());	// 제한 칼로리

			material = new int[N][2];

			// 재료 정보
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				material[i][0] = Integer.parseInt(st.nextToken()); // 점수
				material[i][1] = Integer.parseInt(st.nextToken()); // 칼로리
			}

			maxScore = 0;
			selectMaterial(0, 0, 0);

			System.out.println("#" + tc + " " + maxScore);
		}
	}

	public static void selectMaterial(int idx, int scr, int cal) {
		// 칼로리 초과
		if (cal > L) return;
		// 주어진 제한 칼로리 이하의 조합
		if (cal <= L) maxScore = Math.max(maxScore, scr);
		// 모두 조합을 확인
		if (idx == N) return;
		
		// 이 재료를 사용해보자
		selectMaterial(idx + 1, scr + material[idx][0], cal + material[idx][1]);
		// 이 재료는 사용하지 않을래
		selectMaterial(idx + 1, scr, cal);
	}

}
