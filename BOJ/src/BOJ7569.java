import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int dh[] = {-1, 1};
	static int N, M, H, cntZero, res, map[][][];
	static Queue<pnt> q;

	static public class pnt {
		int h;
		int x;
		int y;

		public pnt(int h, int x, int y) {
			this.h = h;
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // ����
		N = Integer.parseInt(st.nextToken()); // ����
		H = Integer.parseInt(st.nextToken());
		map = new int[H][N][M];
		q = new LinkedList<>();
		cntZero = 0;
		for (int h = 0; h < H; h++) {
			for (int x = 0; x < N; x++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int y = 0; y < M; y++) {
					map[h][x][y] = Integer.parseInt(st.nextToken());
					// ���� �丶���� ��ġ�� Queue�� add
					if (map[h][x][y] == 1) q.offer(new pnt(h, x, y));
					// ���� ���� �丶�� count
					if (map[h][x][y] == 0) cntZero++;
				}
			}
		}
		// ��� �丶�䰡 �;��ִ� ���¶��
		if(cntZero == 0) {
			System.out.println("0");
			return;
		}
		
		res = -1;
		bfs();
		
		// ���� �� ���� �丶�䰡 �ִٸ�
		if(cntZero != 0) System.out.println("-1");
		else System.out.println(res);
	}
	
	public static void bfs() {
		// ���� �丶���� ��ġ�� Queue���� �����鼭 �ֺ� �丶�並 ��������
		while (!q.isEmpty()) {
			// ���� Queue Size�� �̸� ����
			int tmpN = q.size();
			res++;
			for (int day = 0; day < tmpN; day++) {
				pnt tmp = q.poll();
				// ������ �� ���� �丶�� ã��(��,��,��,��)
				for (int i = 0; i < 4; i++) {
					int xx = tmp.x + dx[i];
					int yy = tmp.y + dy[i];
					// ������ ��� ��� pass
					if(xx < 0 || yy < 0 || xx >= N || yy>=M) continue;
					// ������ �丶�䰡 ���� �ʾҴٸ� �����ְ� Queue�� �ֱ�
					if(map[tmp.h][xx][yy] == 0) {
						map[tmp.h][xx][yy] = 1;
						cntZero--;
						q.offer(new pnt(tmp.h, xx, yy));
					}
				}
				// ������ �� ���� �丶�� ã��(��,�Ʒ�)
				for (int i = 0; i < 2; i++) {
					int hh = tmp.h + dh[i];
					// ������ ��� ��� pass
					if(hh < 0 || hh >= H) continue;
					// ������ �丶�䰡 ���� �ʾҴٸ� �����ְ� Queue�� �ֱ�
					if(map[hh][tmp.x][tmp.y] == 0) {
						map[hh][tmp.x][tmp.y] = 1;
						cntZero--;
						q.offer(new pnt(hh, tmp.x, tmp.y));
					}
				}
			}
		}
	}
}
