import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5215 {

	static int N, L, maxScore;
	static int[][] material;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			// ��� ���� ���� Į�θ�
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			material = new int[N][2];

			// ��� ���� �Է� : ������ Į�θ�
			for (int i = 0; i < N; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
				material[i][0] = Integer.parseInt(st2.nextToken()); // ����
				material[i][1] = Integer.parseInt(st2.nextToken()); // Į�θ�
			}

			maxScore = 0;
			makeScore(0, 0, 0);

			System.out.println("#" + tc + " " + maxScore);
		}
	}

	public static void makeScore(int idx, int scr, int cal) {
		// �־��� ���� Į�θ� ������ �����߿��� ���� ���� ���� ������ ���� �ܹ����� ����
		// ��� Ȯ���ϰų� Į�θ��� �Ѿ�� ���
		
		if(cal <= L) maxScore = Math.max(maxScore, scr);
		if (idx == N) return;
		if(cal > L) return;
		
		makeScore(idx + 1, scr + material[idx][0], cal + material[idx][1]);
		makeScore(idx + 1, scr, cal);
	}

}
