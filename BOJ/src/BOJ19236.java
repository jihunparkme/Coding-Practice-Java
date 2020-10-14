import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19236 {

	static int N = 4, res;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static class Fish {
		int x, y, dir;

		public Fish(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[N][N];
		Fish[] fishes = new Fish[17];
		// 공간 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken()); // 물고기 번호
				int b = Integer.parseInt(st.nextToken()) - 1; // 방향
				
				map[i][j] = a;
				fishes[a] = new Fish(i, j, b);
			}
		}
		
		// 먼저 상어가 (0,0)공간에 들어간다.
		// (0,0)에 있는 물고기를 먹고
		res = map[0][0];
		// (0,0)에 들어간다.
		// 상어의 방향은 (0, 0)에 있던 물고기의 방향
		Fish shark = new Fish(0, 0, fishes[map[0][0]].dir);
		// 먹힌 고기는 null
		fishes[map[0][0]] = null;

		// 상어를 공간에 위치 (상어는 -1 번)
		map[0][0] = -1;
		
		process(map, shark, fishes, res);
		
		System.out.println(res);
		
	}

	private static void process(int[][] map, Fish shark, Fish[] fishes, int sum) {

		System.out.println("초기 이동 후");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n\n\n");
		
		// 데이터 복사
		int[][] origMap = new int[N][N];
		for (int i = 0; i < N; i++) 
			System.arraycopy(map[i], 0, origMap[i], 0, N);
		
		Fish origShark = new Fish(shark.x, shark.y, shark.dir);
		Fish[] origFishes = new Fish[17];
		System.arraycopy(fishes, 0, origFishes, 0, fishes.length); 
		
		// 먼저 물고기가 이동
		for (int f = 1; f <= 16; f++) {
			
			// i번 물고기의 좌표
			Fish now = fishes[f];
			// 먹힌 물고기면
			if(now == null) continue;
						
			// 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전
			int origDir = now.dir;
			do {
				// 물고기는 한 칸을 이동
				int xx = now.x + dx[now.dir];
				int yy = now.y + dy[now.dir];
				
				// 상어가 있거나, 공간을 넘어가면 다음 회전
				if(xx < 0 || xx >= N || yy < 0 || yy >= N || map[xx][yy] == -1) {
					now.dir = (now.dir + 1) % 8;
					continue;
				}
				
				// 빈 칸, 다른 물고기가 있는 칸이라면 이동하고 위치를 바꿔주자
				// map과 물고기 위치 갱신
				Fish ftmp = fishes[map[xx][yy]];
				fishes[map[xx][yy]] = fishes[f];
				fishes[f] = ftmp;
				
				int ntmp = map[xx][yy];
				map[xx][yy] = f;
				map[now.x][now.y] = ntmp;
				
				break;
				
			} while (now.dir != origDir);
			
			// 이동할 수 있는 칸이 없으면 이동을 하지 않는다
		}
		
		System.out.println("물고기 이동 후");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n\n");
		
		// 상어가 이동
		// 이동할 수 있을 때까지 다 이동해보자(최대 3칸)
		for (int d = 1; d <= 3; d++) {
			int xx = shark.x + dx[shark.dir] * d;
			int yy = shark.y + dy[shark.dir] * d;
			
			// 범위 넘어가면
			if(xx < 0 || xx >= N || yy < 0 || yy >= N) break;
			// 물고기가 없는 칸일 경우
			if(map[xx][yy] == 0) continue;
			
			// 상어가 물고기가 있는 칸으로 이동했다면
			// 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다.
			int num = map[xx][yy];
			Fish fish = fishes[map[xx][yy]];
			
			// 물고기 정보에서 삭제
			fishes[map[xx][yy]] = null;
			// 상어 정보 갱신
			shark = new Fish(xx, yy, fish.dir);
			// map 갱신
			map[xx][yy] = -1;
			// 원래 상어가 있던 위치는 비우자
			map[fish.x][fish.y] = 0;
			
			System.out.println("상어 이동 후");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("\n\n\n");
			
			process(map, shark, fishes, sum + num);
			
			// 백트래킹
	
			map[fish.x][fish.y] = -1;
			map[xx][yy] = num;
			shark = new Fish(origShark.x, origShark.y, origShark.dir);
			fishes[map[xx][yy]] = fish;
			
			// 물고기 정보 복구
//			for (int i = 0; i < N; i++) 
//				System.arraycopy(origMap[i], 0, map[i], 0, N);
//			System.arraycopy(origFishes, 0, fishes, 0, origFishes.length); 
		}
		
		// 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
		// 결과 갱신
		res = Math.max(res, sum);
		
	}

}

/*
각 물고기는 번호와 방향을 가지고 있다
	번호는 1보다 크거나 같고, 16보다 작거나 같은 자연수
	두 물고기가 같은 번호를 갖는 경우는 없다.
	
방향은 8가지 방향(상하좌우, 대각선) 중 하나

청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다. 
	상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동
	
	
물고기는 번호가 작은 물고기부터 순서대로 이동
	물고기는 한 칸을 이동
	이동할 수 있는 칸은 빈 칸, 다른 물고기가 있는 칸,
		물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동
	이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸
	
	각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전
	만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다
	외의 경우에는 그 칸으로 이동

물고기의 이동이 모두 끝나면 상어가 이동
	상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다
	상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 
	이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다.
	물고기가 없는 칸으로는 이동할 수 없다.
	상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
	
배열을 복사하는 이유는 매개변수로 들어오는 배열, 객체는 포인트를 가리키고 있어서 힙에 하나만 존재
그래서 복사를 해서 원본 데이터를 보존

*/