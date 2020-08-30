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
			N = Integer.parseInt(st.nextToken());	// ��� ��
			L = Integer.parseInt(st.nextToken());	// ���� Į�θ�

			material = new int[N][2];

			// ��� ����
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				material[i][0] = Integer.parseInt(st.nextToken()); // ����
				material[i][1] = Integer.parseInt(st.nextToken()); // Į�θ�
			}

			maxScore = 0;
			selectMaterial(0, 0, 0);

			System.out.println("#" + tc + " " + maxScore);
		}
	}

	public static void selectMaterial(int idx, int scr, int cal) {
		// Į�θ� �ʰ�
		if (cal > L) return;
		// �־��� ���� Į�θ� ������ ����
		if (cal <= L) maxScore = Math.max(maxScore, scr);
		// ��� ������ Ȯ��
		if (idx == N) return;
		
		// �� ��Ḧ ����غ���
		selectMaterial(idx + 1, scr + material[idx][0], cal + material[idx][1]);
		// �� ���� ������� ������
		selectMaterial(idx + 1, scr, cal);
	}

}
