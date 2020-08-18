import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4963_dfs {

	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int res, w, h, map[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			// 0 0 �� �ԷµǸ� ����
			if (w + h == 0)
				break;
			
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) 
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			res = 0;
			// ��� ��ǥ�� Ȯ���ϸ鼭
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					// ���̸�
					if(map[i][j] == 1) {
						dfs(i, j);
						res++;
					}					
				}
			}
			System.out.println(res);
		}
	}

	public static void dfs(int x, int y) {
		// �湮�����ϱ� �湮 ó��
		map[x][y] = 0;
	
		// �ֺ��� Ž��
		for (int d = 0; d < 8; d++) {
			int xx = x + dx[d];
			int yy = y + dy[d];
			// ���� ����� pass
			if (xx < 0 || yy < 0 || xx >= h || yy >= w) continue;
			// ���̸� pass
			if (map[xx][yy] == 0) continue;
			// �湮�غ� ���� �湮
			dfs(xx, yy);
		}
	}
}