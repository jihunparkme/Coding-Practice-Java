import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1824_bfs {

	static int R, C;
	static char oper[][];
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0}; // 동,남,서,북
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
			
			// 이미 방문한 곳이면 pass
			if(visited[now.dir][now.mem][now.x][now.y]) continue;
			// 현재 명령
			char c = oper[now.x][now.y];
			visited[now.dir][now.mem][now.x][now.y] = true;
			
			// 0~9	메모리에 문자가 나타내는 값을 저장한다.
			if(c >= '0' && c <= '9') {
				now.mem = c - '0';
			} 
			// < 이동 방향을 왼쪽으로 바꾼다.
			else if(c == '<') {
				now.dir = 2;
			}
			// >	이동 방향을 오른쪽으로 바꾼다.
			else if(c == '>') {
				now.dir = 0;
			}
			// ^	이동 방향을 위쪽으로 바꾼다.
			else if(c == '^') {
				now.dir = 3;
			}
			// v	이동 방향을 아래쪽으로 바꾼다.
			else if(c == 'v') {
				now.dir = 1;
			}
			// _	메모리에 0이 저장되어 있으면 이동 방향을 오른쪽으로 바꾸고, 아니면 왼쪽으로 바꾼다.
			else if(c == '_') {
				now.dir = (now.mem == 0) ? 0 : 2;
			}
			// |	메모리에 0이 저장되어 있으면 이동 방향을 아래쪽으로 바꾸고, 아니면 위쪽으로 바꾼다.
			else if(c == '|') {
				now.dir = (now.mem == 0) ? 1 : 3;
			}
			// ?	이동 방향을 상하좌우 중 하나로 무작위로 바꾼다. 방향이 바뀔 확률은 네 방향 동일하다.
			else if(c == '?') {
				for (int d = 0; d < 4; d++) {
					int nextX = now.x + dx[d];
					int nextY = now.y + dy[d];
					// 2차원 격자의 바깥으로 이동하는 방향이면, 반대편에 있는 위치로 이동
					if(nextX < 0) nextX = R - 1;
					else if(nextX >= R) nextX = 0;
					else if(nextY < 0) nextY = C - 1;
					else if(nextY >= C) nextY= 0;
					
					q.add(new Info(nextX, nextY, d, now.mem));
				}
				
				continue;
			}
			// @	프로그램의 실행을 정지한다.
			else if(c == '@') {
				return true;
			}
			// +	메모리에 저장된 값에 1을 더한다. 만약 더하기 전 값이 15이라면 0으로 바꾼다.
			else if(c == '+') {
				now.mem = (now.mem == 15) ? 0 : now.mem + 1;
			}
			// -	메모리에 저장된 값에 1을 뺀다. 만약 빼기 전 값이 0이라면 15로 바꾼다.
			else if(c == '-') {
				now.mem = (now.mem == 0) ? 15 : now.mem - 1;
			}
			// .	아무 것도 하지 않는다.
			// 이동
			now.x += dx[now.dir];
			now.y += dy[now.dir];
			// 2차원 격자의 바깥으로 이동하는 방향이면, 반대편에 있는 위치로 이동
			if(now.x < 0) now.x = R - 1;
			else if(now.x >= R) now.x = 0;
			else if(now.y < 0) now.y = C - 1;
			else if(now.y >= C) now.y = 0;
			
			q.add(now);
		}
		
		return false;
	}
}