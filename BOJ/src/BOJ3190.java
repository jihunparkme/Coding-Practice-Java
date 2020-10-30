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
	static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 }; // ��,��,��,��
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
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Body other = (Body) obj;
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
		N = Integer.parseInt(br.readLine()); // ������ ũ��
		map = new int[N][N];
		K = Integer.parseInt(br.readLine()); // ����� ����
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;
		}
		L = Integer.parseInt(br.readLine()); // ���� ���� ��ȯ Ƚ��
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
		// ���� ��ġ�� (0,0)
		body.add(new Body(0, 0, 0));
		head = new Body(0, 0, 0);

		while (true) {

			time++;
			Body nextHead = new Body(head.x, head.y, head.dir);

			nextHead.x += dx[nextHead.dir];
			nextHead.y += dy[nextHead.dir];

			// �Ӹ��� �� �Ǵ� �ڱ��ڽ��� ���� �ε����� ������ ������
			if (body.contains(nextHead) 
					|| nextHead.x < 0 || nextHead.x >= N || nextHead.y < 0 || nextHead.y >= N)
				return time;

			// �̵��� ĭ�� ����� �ִٸ�, �� ĭ�� �ִ� ����� �������� �� �Ӹ���
			if (map[nextHead.x][nextHead.y] == 1) {
				map[nextHead.x][nextHead.y] = 0;
			}
			// �̵��� ĭ�� ����� ���ٸ�, �� ���̸� �ٿ��� ������ ��ġ�� ĭ�� ����ش�.
			else {
				body.poll();
			}

			// ���� ��ȯ
			if (oper.containsKey(time)) {
				char d = oper.get(time);
				// ����(C�� 'L') �Ǵ� ������(C�� 'D')
				if (d == 'D') {
					nextHead.dir = (nextHead.dir + 1) % 4;
				} else {
					nextHead.dir = (nextHead.dir + 3) % 4;
				}
			}
			
			// �Ӹ��� ���� �߰�
			body.add(nextHead);
			// �Ӹ� ���� ����
			head = new Body(nextHead.x, nextHead.y, nextHead.dir);
		}
	}
}