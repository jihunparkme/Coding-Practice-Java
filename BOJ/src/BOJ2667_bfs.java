import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2667_bfs {

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static public class pos {
		int x;
		int y;

		public pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		String input;
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		int mark = 1, cnt;
		Queue<pos> q = new LinkedList<>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		// ��� ��ǥ�� Ȯ���ϸ鼭
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// ���� �ִٸ�
				if (map[i][j] == 1) {
					cnt = 1;	// �ش� ������ ���� ���� count�ϱ� ���� ����
					mark++;		// ���� ��ȣ
					q.add(new pos(i, j));
					map[i][j] = mark;

					while (!q.isEmpty()) {
						pos now = q.poll();

						for (int d = 0; d < 4; d++) {
							int xx = now.x + dx[d];
							int yy = now.y + dy[d];
							// ������ ����� pass
							if (xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
							// ���� ���ų�, �̹� ������ ������ ���̶�� pass
							if (map[xx][yy] != 1) continue;
							// ������ �����ְ� Queue�� �ֱ�
							map[xx][yy] = mark;
							cnt++;
							q.add(new pos(xx, yy));
						}
					}
					// ������ ���� ���� list�� �־��ֱ�
					res.add(cnt);
				}
			}
		}
		System.out.println(mark - 1);
		// ������ ���� ���� ������������ ����
		res.sort(null);
		for (int x : res) 
			sb.append(Integer.toString(x) + '\n');
		System.out.println(sb);
	}

}
