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
		
		N = Integer.parseInt(st.nextToken()); // 땅 크기
		M = Integer.parseInt(st.nextToken()); // 구매할 나무
		K = Integer.parseInt(st.nextToken()); // 몇년 후
			
		food = new int[N][N]; // 양분 정보
		add = new int[N][N]; // 추가 양분
		map = new PriorityQueue[N][N]; // 나무 정보
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// 가장 처음에 양분은 모든 칸에 5만큼
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
		
		// K년 후
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
		
		// 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가
		// 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				food[i][j] += add[i][j];
			}
		}
		
	}

	private static void fall() {
		
		// 가을에는 나무가 번식한다.
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				// 심어진 나무를 모두 확인
				for (int a : map[x][y]) {
					// 번식하는 나무는 나이가 5의 배수이어야 하며,
					if(a % 5 == 0) {
						// 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
						for (int d = 0; d < 8; d++) {
							int xx = x + dx[d];
							int yy = y + dy[d];
							// 범위를 벗어나면
							if(xx < 0 || xx >= N || yy < 0 || yy >= N) continue;
							
							map[xx][yy].add(1);
						}
					}
				}
				
			}
		}
		
	}

	private static void springToSummer() {
		
		// 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 나무가 있다면
				int die = 0;
				if(map[i][j].size() >= 1) {
					ArrayList<Integer> older = new ArrayList<>();
					
					// 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
					while(!map[i][j].isEmpty()) {
						int age = map[i][j].poll();
						// 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
						if(food[i][j] - age < 0) {
							// 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다
							die += age / 2;
						} else {
							food[i][j] -= age;
							older.add(age + 1);
						}
					}
					
					// 나이먹은 나무들을 다시 map에 옮겨주자.
					for (Integer t : older) {
						map[i][j].add(t);
					}
					
					// 여름에는 봄에 죽은 나무가 양분으로 변하게 된다
					food[i][j] += die;			
					
				}
				
			}
		}
		
	}
	
}
/*

가장 처음에 양분은 모든 칸에 5만큼

봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가
	각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다. 
	하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다. 
	만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
	
여름에는 봄에 죽은 나무가 양분으로 변하게 된다
	각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
	
가을에는 나무가 번식한다.
	. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다. 
	어떤 칸 (r, c)와 인접한 칸은 8 
	상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
	
겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가
	각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.

*/