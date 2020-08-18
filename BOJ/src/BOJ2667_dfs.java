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
		// 모든 좌표를 확인하면서
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 집이 있다면
				if (map[i][j] == 1) {
					cnt = 0;	// 해당 단지내 집의 수를 count하기 위한 변수
					mark++;		// 단지 번호
					dfs(i, j);
					// 단지내 집의 수를 list에 넣어주기
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
		cnt++;	// 단지내 집의 수를 count
		map[x][y] = mark;	// 단지번호 저장
		// 주변 땅을 확인
		for (int d = 0; d < 4; d++) {
			int xx = x + dx[d];
			int yy = y + dy[d];
			// 범위를 벗어나면 pass
			if (xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
			// 집이 없거나, 이미 단지가 정해진 집이라면 pass
			if (map[xx][yy] != 1) continue;
			// 단지를 정해주고 해당 좌표를 dfs에 넘겨주기
			dfs(xx, yy);
		}
	}
}
