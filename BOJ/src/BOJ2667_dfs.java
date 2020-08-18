import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2667_dfs {

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int N, cnt, mark = 1;
	static int map[][];
	static ArrayList<Integer> res = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String input;
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		// ��� ��ǥ�� Ȯ���ϸ鼭
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// ���� �ִٸ�
				if (map[i][j] == 1) {
					cnt = 0;	// �ش� ������ ���� ���� count�ϱ� ���� ����
					mark++;		// ���� ��ȣ
					dfs(i, j);
					// ������ ���� ���� list�� �־��ֱ�
					res.add(cnt);
				}
			}
		}
		
		System.out.println(mark - 1);
		res.sort(null);
		for (int x : res) 
			sb.append(Integer.toString(x) + '\n');
		System.out.println(sb);
	}

	public static void dfs(int x, int y) {
		cnt++;	// ������ ���� ���� count
		map[x][y] = mark;	// ������ȣ ����
		// �ֺ� ���� Ȯ��
		for (int d = 0; d < 4; d++) {
			int xx = x + dx[d];
			int yy = y + dy[d];
			// ������ ����� pass
			if (xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
			// ���� ���ų�, �̹� ������ ������ ���̶�� pass
			if (map[xx][yy] != 1) continue;
			// ������ �����ְ� �ش� ��ǥ�� dfs�� �Ѱ��ֱ�
			dfs(xx, yy);
		}
	}
}
