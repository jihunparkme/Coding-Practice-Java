import java.awt.Point;
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
		Fish[] fishPos = new Fish[17];
		// 공간 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken()); // 물고기 번호
				int b = Integer.parseInt(st.nextToken()) - 1; // 방향
				
				map[i][j] = a;
				fishPos[a] = new Fish(i, j, b);
			}
		}
		
		// 먼저 상어가 (0,0)공간에 들어간다.
		// (0,0)에 있는 물고기를 먹고
		res = map[0][0].num;
		// 먹힌 고기는 null
		fishPos[map[0][0].num] = null;
		// (0,0)에 들어간다.
		// 상어의 방향은 (0, 0)에 있던 물고기의 방향
		Shark shark = new Shark(0, 0, map[0][0].dir);
		// 상어를 공간에 위치 (상어는 -1 번)
		map[0][0] = new Fish(-1, -1);
		
		process(shark, map, fishPos, res);
		
		System.out.println(res);
		
	}

	private static void process(Shark shark, Fish[][] map, Point[] fishPos, int sum) {
		
		Fish[][] newMap = new Fish[N][N];
		for (int i = 0; i < N; i++) {
			newMap[i] = map[i].clone();
		}
		
		// 먼저 물고기가 이동
		for (int i = 1; i <= 16; i++) {
			
			// i번 물고기의 좌표
			Point now = fishPos[i];
			// 먹힌 물고기면
			if(now == null) continue;
			
			Fish fish = map[now.x][now.y];
			
			// 물고기는 한 칸을 이동
			int xx = now.x + dx[fish.dir];
			int yy = now.y + dy[fish.dir];
			
			boolean isMove = true;
			// 상어가 있거나, 공간을 넘어가면
			if(xx < 0 || xx >= N || yy < 0 || yy >= N || 
					(map[xx][yy] != null && map[xx][yy].num == -1)) {
				// 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전
				
				int initPos = fish.dir; 
				fish.dir = (fish.dir + 1) % 8; 
				isMove = false;
				
				while(fish.dir != initPos) {
					
					xx = now.x + dx[fish.dir];
					yy = now.y + dy[fish.dir];
					// 이동할 수 없다면 45도 반시계 회전
					if(xx < 0 || xx >= N || yy < 0 || yy >= N || 
							(map[xx][yy] != null && map[xx][yy].num == -1)) {
						fish.dir = (fish.dir + 1) % 8; 
						continue;
					}
					// 이동할 수 있다면
					isMove = true;
					break;
				}
			}
			// 이동할 수 있는 칸이 없으면 이동을 하지 않는다
			if(!isMove) continue;
			
			// 빈 칸, 다른 물고기가 있는 칸이라면 이동하고 위치를 바꿔주자
			// map과 물고기 위치 갱신
			Point ptmp = fishPos[i]; 
			fishPos[i] = new Point(xx, yy);
			// 빈 칸이 아니라면 
			if(map[xx][yy] != null)
				fishPos[map[xx][yy].num] = ptmp;
			
			Fish ftmp = map[xx][yy];
			newMap[xx][yy] = fish;			
			newMap[now.x][now.y] = ftmp;
		}
		
		System.out.println("물고기 이동 후");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n\n\n");
		
		// 상어가 이동
		// 초기 상어 위치
		int xx = shark.x;
		int yy = shark.y;
		boolean isMove = false;
		// 이동할 수 있을 때까지 다 이동해보자.
		while(true) {
			xx += dx[shark.dir];
			yy += dy[shark.dir];
			
			// 범위 넘어가면
			if(xx < 0 || xx >= N || yy < 0 || yy >= N) break;
			// 물고기가 없는 칸일 경우
			if(newMap[xx][yy] == null) continue;
			
			// 상어가 물고기가 있는 칸으로 이동했다면
			// 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 
			Fish fish = newMap[xx][yy];
			
			// 물고기 정보에서 삭제
			fishPos[fish.num] = null;
			// map 갱신
			newMap[xx][yy] = new Fish(-1, -1);
			// 원래 상어가 있던 위치는 null
			newMap[shark.x][shark.y] = null;

			isMove = true;
			
			System.out.println("상어 이동 후");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("\n\n\n");
			
			process(new Shark(xx, yy, fish.dir), newMap, fishPos, sum + fish.num);
			
			// 백트래킹

			// 물고기 정보 복구
			fishPos[fish.num] = new Point(xx, yy);
		}
		
		// 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
		if(!isMove) {
			// 결과 갱신
			res = Math.max(res, sum);
			
			System.out.println("이동 불가");
			return;
		}
		
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
*/