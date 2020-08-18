import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2178_dfs {

	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static int N, M, res = 987654321, map[][];
	static boolean visited[][];
	
	public static void process(int x, int y, int sum) {
		// ���������� �������� �� �̵��Ÿ�
		if(x == N-1 && y == M-1) {
			res = Math.min(res, sum);
			return;
		}
		if(map[x][y] < sum) return;
		
		map[x][y] = sum;
		for (int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			// ���� �Ѿ�� �н�
			if(xx < 0 || yy<0 || xx>=N || yy>=M) continue;
			// ���̰ų� �̹� �湮�� ���̶�� pass
			if(map[xx][yy] == 0 || visited[xx][yy]) continue;
			// �������� ���� ���̶�� ��������
			visited[xx][yy] = true;
			process(xx, yy, sum + 1);
			visited[xx][yy] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int x = 0; x < N; x++) {
			String str = br.readLine();
			for (int y = 0; y < M; y++) {
				map[x][y] = ((str.charAt(y)) - '0') * 987654321;
			}
		}
	
		visited[0][0] = true;
		process(0, 0, 0);
		System.out.println(res + 1);
	}

}

// 1�� �̵��� �� �ִ� ĭ�� ��Ÿ����, 0�� �̵��� �� ���� ĭ