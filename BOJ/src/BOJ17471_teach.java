import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ17471_teach {
	static int N; //선거구의 수
	static int[] voters; //각 선거구별 유권자의 수
	static int[][] adj; //각 선거구간 인접정보
	static boolean[] sel; //각 선거구가 A팀 혹은 B팀 배정된 정보( 부분집합에서 활용 )
	public static void main(String[] args) {
		//input
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		voters = new int[N+1];//선거구가 1번부터 시작하므로 한칸 더 크게 만들게요
		sel = new boolean[N+1];
		//각 선거구별 유권자의 수 입력
		for(int i = 1; i <= N; i++)
			voters[i] = sc.nextInt();
		adj = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			int cnt = sc.nextInt();//인접한 선거구의 수
			for(int j = 0; j < cnt; j++) {
				adj[i][sc.nextInt()] = 1;
			}
		}
		powerSet(1);
		System.out.println(ans==987654321?-1:ans);
	}
	static int ans = 987654321;//정답을 저장할 변수
	//부분집합
	static void powerSet(int idx) {
		if(idx == N+1) {
			//모든 선거구가 팀배정이 완료됨
			if( check() ) {
				int sumA = 0;
				int sumB = 0;
				//각 선거구들을 검사해가면서
				for(int i = 1; i <= N; i++) {
					//선택된 애는 A팀. 아닌 애는 B팀
					if(sel[i])
						sumA += voters[i];
					else
						sumB += voters[i];
				}
				ans = Math.min(ans, Math.abs(sumA - sumB));
			}
			return;
		}
		sel[idx] = true;
		powerSet(idx + 1);
		sel[idx] = false;
		powerSet(idx + 1);
	}
	static boolean check() {
		boolean[] visited = new boolean[N+1];
		//A팀 중 하나를 골라서 출발지 삼아서 인접한 선거구를 방문해가며 visited에 true로 변경
		//B팀 중 하나를 골라서 출발지 삼아서 인접한 선거구를 방문해가며 visited에 true로 변경
		
		//A팀 출발지를 고르자.
		int teamA = -1;
		for(int i = 1; i <= N; i++) {
			if(sel[i]) {
				teamA = i;
				break;
			}
		}
		//teamA에는 A팀의 첫번째 선거구가 들어가있겠당..ㅎ
		int teamB = -1;
		for(int i = 1; i <= N; i++) {
			if(!sel[i]) {
				teamB = i;
				break;
			}
		}
		//teamB에는 B팀의 첫번째 선거구가 들어가있겠당..ㅎ
		
		//teamA나 teamB가 선거구를 찾지 못하고 -1로 남아있다는건 해당 팀에 선거구가 없다는 것.
		if(teamA == -1 || teamB == -1)
			return false;
		
		//각 팀의 선거구를 teamA와 teamB를 출발지 삼아서 탐색을 시작하는데, 방법은 dfs혹은 bfs.
		Queue<Integer> queue = new LinkedList<>();
		
		//teamA탐색.
		//teamA의 출발주자를 큐에 넣고, visited에 트루로 체크하고 while돌자
		queue.add(teamA);
		visited[teamA] = true;
		while(!queue.isEmpty()) {
			int node = queue.poll();
			//모든 선거구들 중에서 나와 같은 팀이면서. 나와 연결이 있으면서. 방문하지 않았다면 큐에 삽입
			for(int i = 1; i <= N; i++) {
				//같은 팀이 아니면 재껴 우리팀은 sel[i]가 true야
				if(!sel[i])
					continue;
				//방문했던 친구도 재껴
				if(visited[i])
					continue;
				//경로가 없어도 재껴
				if(adj[node][i] == 0)
					continue;
				visited[i] = true;
				queue.add(i);
			}
		}
		//teamB의 출발주자를 큐에 넣고, visited에 트루 체크하고 while돌자
		queue.add(teamB);
		visited[teamB] = true;
		while(!queue.isEmpty()) {
			int node = queue.poll();
			//모든 선거구들 중에서 나와 같은 팀이면서. 나와 연결이 있으면서. 방문하지 않았다면 큐에 삽입
			for(int i = 1; i <= N; i++) {
				//같은 팀이 아니면 재껴 우리팀은 sel[i]가 false야
				if(sel[i])
					continue;
				//방문했던 친구도 재껴
				if(visited[i])
					continue;
				//경로가 없어도 재껴
				if(adj[node][i] == 0)
					continue;
				visited[i] = true;
				queue.add(i);
			}
		}
		
		//visited의 1부터 N까지 중에 false가 발견되면 글러먹었다. return false
		for(int i = 1; i <= N; i++) {
			if(!visited[i])
				return false;
		}
		//return false를 당한 적이 없다... return true;
		return true;
	}
}