import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16235 {

	static int N, M, K, food[][], add[][];
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }, dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static PriorityQueue<Integer>[][] map; 
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // �� ũ��
		M = Integer.parseInt(st.nextToken()); // ������ ����
		K = Integer.parseInt(st.nextToken()); // ��� ��
			
		food = new int[N][N]; // ��� ����
		add = new int[N][N]; // �߰� ���
		map = new PriorityQueue[N][N]; // ���� ����
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// ���� ó���� ����� ��� ĭ�� 5��ŭ
				food[i][j] = 5;
				add[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = new PriorityQueue<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken());
			
			map[x][y].add(a);
		}
		
		System.out.println(process());
	}

	private static int process() {
		
		// K�� ��
		while(K-- > 0) {
			springToSummer();
			fall();
			winter();
		}
		
		return countTree();
	}

	private static int countTree() {
		
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnt += map[i][j].size();
			}
		}
		
		return cnt;
	}

	private static void winter() {
		
		// �ܿ￡�� S2D2�� ���� ���ƴٴϸ鼭 ���� ����� �߰�
		// �� ĭ�� �߰��Ǵ� ����� ���� A[r][c]�̰�, �Է����� �־�����.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				food[i][j] += add[i][j];
			}
		}
		
	}

	private static void fall() {
		
		// �������� ������ �����Ѵ�.
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				// �ɾ��� ������ ��� Ȯ��
				for (int a : map[x][y]) {
					// �����ϴ� ������ ���̰� 5�� ����̾�� �ϸ�,
					if(a % 5 == 0) {
						// ������ 8���� ĭ�� ���̰� 1�� ������ �����.
						for (int d = 0; d < 8; d++) {
							int xx = x + dx[d];
							int yy = y + dy[d];
							// ������ �����
							if(xx < 0 || xx >= N || yy < 0 || yy >= N) continue;
							
							map[xx][yy].add(1);
						}
					}
				}
				
			}
		}
		
	}

	private static void springToSummer() {
		
		// ������ ������ �ڽ��� ���̸�ŭ ����� �԰�, ���̰� 1 ����
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// ������ �ִٸ�
				int die = 0;
				if(map[i][j].size() >= 1) {
					ArrayList<Integer> older = new ArrayList<>();
					
					// �ϳ��� ĭ�� ���� ���� ������ �ִٸ�, ���̰� � �������� ����� �Դ´�.
					while(!map[i][j].isEmpty()) {
						int age = map[i][j].poll();
						// ����, ���� ����� ������ �ڽ��� ���̸�ŭ ����� ���� �� ���� ������ ����� ���� ���ϰ� ��� �״´�.
						if(food[i][j] - age < 0) {
							// ������ ���� �������� ���̸� 2�� ���� ���� ������ �ִ� ĭ�� ������� �߰��ȴ�
							die += age / 2;
						} else {
							food[i][j] -= age;
							older.add(age + 1);
						}
					}
					
					// ���̸��� �������� �ٽ� map�� �Ű�����.
					for (Integer t : older) {
						map[i][j].add(t);
					}
					
					// �������� ���� ���� ������ ������� ���ϰ� �ȴ�
					food[i][j] += die;			
					
				}
				
			}
		}
		
	}
	
}
/*

���� ó���� ����� ��� ĭ�� 5��ŭ

������ ������ �ڽ��� ���̸�ŭ ����� �԰�, ���̰� 1 ����
	������ ������ ������ �ִ� 1��1 ũ���� ĭ�� �ִ� ��и� ���� �� �ִ�. 
	�ϳ��� ĭ�� ���� ���� ������ �ִٸ�, ���̰� � �������� ����� �Դ´�. 
	����, ���� ����� ������ �ڽ��� ���̸�ŭ ����� ���� �� ���� ������ ����� ���� ���ϰ� ��� �״´�.
	
�������� ���� ���� ������ ������� ���ϰ� �ȴ�
	������ ���� �������� ���̸� 2�� ���� ���� ������ �ִ� ĭ�� ������� �߰��ȴ�. �Ҽ��� �Ʒ��� ������.
	
�������� ������ �����Ѵ�.
	. �����ϴ� ������ ���̰� 5�� ����̾�� �ϸ�, ������ 8���� ĭ�� ���̰� 1�� ������ �����. 
	� ĭ (r, c)�� ������ ĭ�� 8 
	���� ���� ����� ĭ���� ������ ������ �ʴ´�.
	
�ܿ￡�� S2D2�� ���� ���ƴٴϸ鼭 ���� ����� �߰�
	�� ĭ�� �߰��Ǵ� ����� ���� A[r][c]�̰�, �Է����� �־�����.

*/