import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1868_dfs {

	static int N, res, mCnt[][];
	static char map[][];
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }, dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			res = 0;
			map = new char[N][N];
			mCnt = new int[N][N];
			// ���ڹ� �Է�
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			// �ֺ� ���� ���� ����
			countMine();

			// �ֺ��� ���ڰ� ���� ������ ��������
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// �ֺ��� ���ڰ� ���� �� ��ġ�� ���ڰ� �ƴ϶��
					if(mCnt[i][j] == 0 && map[i][j] != '*') {
						click(i, j);
						res++;
					}
				}
			}
			
			// ���� ������ ���� ���� �ִٸ� ��������.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// �ֺ��� ���ڰ� ������ �� ������ ���ڰ� �ƴ϶��
					if(mCnt[i][j] > 0 && map[i][j] != '*') {
						res++;
					}
				}
			}
			
			System.out.println("#" + tc + " " + res);
		}

	}

	private static void click(int x, int y) {

		int now = mCnt[x][y]; 
		// �湮 ó��
		mCnt[x][y] = -1;

		// �ֺ��� ���ڰ� ���� ��ǥ���
		if(now == 0) {
			// �ֺ� Ž��
			for (int d = 0; d < 8; d++) {
				int xx = x + dx[d];
				int yy = y + dy[d];
				// ������ ����ų� ���� ���̰ų� ���ڶ�� pass
				if(xx < 0 || xx >= N || yy < 0 || yy >= N 
						|| mCnt[xx][yy] == -1 || map[xx][yy] == '*') continue;

				click(xx, yy);
			}
		}
		
	}
	
	private static void countMine() {
		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				// Ŭ���� �� �ִ� ���̶�� �ֺ��� ���ڰ� � �ִ��� ������.
				if(map[x][y] == '.') {
					int cnt = 0;
					for (int d = 0; d < 8; d++) {
						int xx = x + dx[d];
						int yy = y + dy[d];
						// ������ ����ų� ���ڰ� �ƴϸ� pass
						if(xx < 0 || xx >= N || yy < 0 || yy >= N || map[xx][yy] != '*') continue;
						cnt++;
					}
					
					mCnt[x][y] = cnt;
				}
			}
		}
		
	}

}