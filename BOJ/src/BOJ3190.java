import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3190 {

	static int N, K, L, map[][];
	static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 }; // 동,남,서,북
	static Map<Integer, Character> oper;
	static Body head;
	static class Body {
		int x, y, dir;

		public Body(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + dir;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Body other = (Body) obj;
			if (dir != other.dir)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 보드의 크기
		map = new int[N][N];
		K = Integer.parseInt(br.readLine()); // 사과의 개수
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;
		}
		L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
		oper = new HashMap<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());

			oper.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}

		System.out.println(process());
	}

	private static int process() {

		int time = 0;
		Queue<Body> body = new LinkedList<>();
		// 시작 위치는  (0,0)
		body.add(new Body(0, 0, 0));
		head = new Body(0, 0, 0);
		
		while (true) {

			++time;
			Body nextHead = new Body(0, 0, 0);
			
			if (oper.containsKey(time)) {
				char d = oper.get(time);
				// 왼쪽(C가 'L') 또는 오른쪽(C가 'D')
				if(d == 'D') {
					nextHead.dir = (head.dir + 1) % 4; 
				} else {
					nextHead.dir = (head.dir + 3) % 4; 
				}
			}
			
			nextHead.x = head.x + dx[nextHead.dir];
			nextHead.y = head.y + dy[nextHead.dir];
			
			// 머리가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다
			if(body.contains(nextHead) ||
					nextHead.x < 0 || nextHead.x >= N || nextHead.y < 0 || nextHead.y >= N) return time;
			
			return time;
		}
	}

}

/*
 * 
 * 
 * 매 초 이동하는데 규칙 몸길이를 늘려 머리를 다음칸에 위치 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지
 * 않는다. 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
 * 
 */