import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1861 {

	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static class pos {
		int x, y;

		public pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			int map[][] = new int[N][N];
			int chk[] = new int[N*N+1];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxCnt = 0;
			Queue<pos> q = new LinkedList<pos>();
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					// ���� ���ڸ� ���� Queue�� �ְ�
					q.add(new pos(x, y));
					int cnt = 1;
					
					while(!q.isEmpty()) {
						pos now = q.poll();
						
						for (int d = 0; d < 4; d++) {
							int xx = now.x + dx[d];
							int yy = now.y + dy[d];
							// ������ �Ѿ�� pass
							if(xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
							// �̵��Ϸ��� �濡 ���� ���ڰ� ���� �濡 ���� ���ں��� ��Ȯ�� 1 �� ũ�� �ʴٸ�
							if(map[xx][yy] != map[now.x][now.y] + 1) continue;
							// ��Ȯ�� 1 �� ū ���� �����Ѵٸ�
							cnt++;
							q.add(new pos(xx, yy));
						}
					}
					
					chk[map[x][y]] = cnt;
					maxCnt = maxCnt < cnt ? cnt : maxCnt;
				}
			}	
			// ó�� � ���� ���� �濡�� �־�� ���� ���� ������ ���� �̵��� �� �ִ���
			sb.append("#" + tc + " ");
			for (int i = 1; i <= N*N; i++) {
				if(chk[i] == maxCnt) {
					sb.append(i + " ");
					break;
				}
			}
			sb.append(maxCnt);
			System.out.println(sb);
		}
	}
}
