import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1824_bfs {

	static int R, C;
	static char oper[][];
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0}; // ��,��,��,��
	static class Info {
		int x, y, dir, mem;

		public Info(int x, int y, int dir, int mem) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.mem = mem;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			oper = new char[R][C]; 
			
			for (int i = 0; i < R; i++) {
				oper[i] = br.readLine().toCharArray();
			}

			System.out.println("#" + tc + " " + (process() ? "YES" : "NO"));
		}
		
	}

	private static boolean process() {
		
		boolean[][][][] visited = new boolean[4][16][R][C];
		Queue<Info> q = new LinkedList<>();
		q.add(new Info(0, 0, 0, 0));
		
		while(!q.isEmpty()) {
			
			Info now = q.poll();
			
			// �̹� �湮�� ���̸� pass
			if(visited[now.dir][now.mem][now.x][now.y]) continue;
			// ���� ���
			char c = oper[now.x][now.y];
			visited[now.dir][now.mem][now.x][now.y] = true;
			
			// 0~9	�޸𸮿� ���ڰ� ��Ÿ���� ���� �����Ѵ�.
			if(c >= '0' && c <= '9') {
				now.mem = c - '0';
			} 
			// < �̵� ������ �������� �ٲ۴�.
			else if(c == '<') {
				now.dir = 2;
			}
			// >	�̵� ������ ���������� �ٲ۴�.
			else if(c == '>') {
				now.dir = 0;
			}
			// ^	�̵� ������ �������� �ٲ۴�.
			else if(c == '^') {
				now.dir = 3;
			}
			// v	�̵� ������ �Ʒ������� �ٲ۴�.
			else if(c == 'v') {
				now.dir = 1;
			}
			// _	�޸𸮿� 0�� ����Ǿ� ������ �̵� ������ ���������� �ٲٰ�, �ƴϸ� �������� �ٲ۴�.
			else if(c == '_') {
				now.dir = (now.mem == 0) ? 0 : 2;
			}
			// |	�޸𸮿� 0�� ����Ǿ� ������ �̵� ������ �Ʒ������� �ٲٰ�, �ƴϸ� �������� �ٲ۴�.
			else if(c == '|') {
				now.dir = (now.mem == 0) ? 1 : 3;
			}
			// ?	�̵� ������ �����¿� �� �ϳ��� �������� �ٲ۴�. ������ �ٲ� Ȯ���� �� ���� �����ϴ�.
			else if(c == '?') {
				for (int d = 0; d < 4; d++) {
					int nextX = now.x + dx[d];
					int nextY = now.y + dy[d];
					// 2���� ������ �ٱ����� �̵��ϴ� �����̸�, �ݴ��� �ִ� ��ġ�� �̵�
					if(nextX < 0) nextX = R - 1;
					else if(nextX >= R) nextX = 0;
					else if(nextY < 0) nextY = C - 1;
					else if(nextY >= C) nextY= 0;
					
					q.add(new Info(nextX, nextY, d, now.mem));
				}
				
				continue;
			}
			// @	���α׷��� ������ �����Ѵ�.
			else if(c == '@') {
				return true;
			}
			// +	�޸𸮿� ����� ���� 1�� ���Ѵ�. ���� ���ϱ� �� ���� 15�̶�� 0���� �ٲ۴�.
			else if(c == '+') {
				now.mem = (now.mem == 15) ? 0 : now.mem + 1;
			}
			// -	�޸𸮿� ����� ���� 1�� ����. ���� ���� �� ���� 0�̶�� 15�� �ٲ۴�.
			else if(c == '-') {
				now.mem = (now.mem == 0) ? 15 : now.mem - 1;
			}
			// .	�ƹ� �͵� ���� �ʴ´�.
			// �̵�
			now.x += dx[now.dir];
			now.y += dy[now.dir];
			// 2���� ������ �ٱ����� �̵��ϴ� �����̸�, �ݴ��� �ִ� ��ġ�� �̵�
			if(now.x < 0) now.x = R - 1;
			else if(now.x >= R) now.x = 0;
			else if(now.y < 0) now.y = C - 1;
			else if(now.y >= C) now.y = 0;
			
			q.add(now);
		}
		
		return false;
	}
}