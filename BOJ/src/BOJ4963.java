import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4963 {

	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

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
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			// 0 0 �� �ԷµǸ� ����
			if (w + h == 0)
				break;
			
			int map[][] = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) 
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int res = 0;
			Queue<pos> q = new LinkedList<>();
			// ��� ��ǥ�� Ȯ���ϸ鼭
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					// ���̸� pass
					if(map[i][j] == 0) continue;
					// ���̸� Queue�� add
					q.add(new pos(i, j));
					while (!q.isEmpty()) {
						pos land = q.poll();
						// �湮�����ϱ� 0���� ����
						map[land.x][land.y] = 0;
						// �ֺ��� Ž��
						for (int d = 0; d < 8; d++) {
							int xx = land.x + dx[d];
							int yy = land.y + dy[d];
							// ���� ����� pass
							if (xx < 0 || yy < 0 || xx >= h || yy >= w) continue;
							// ���̸� pass
							if (map[xx][yy] == 0) continue;
							// �� �湮�� ���� �����Ƿ� ���̸� �湮�ߴٰ� �̸� 0���� ǥ�����ְ�
							map[xx][yy] = 0;
							// �湮�غ� ���� Queue�� add
							q.add(new pos(xx, yy));
						}
					}
					res++;
				}
			}
			System.out.println(res);
		}
	}

}
