import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * PQ를 사용하지 않은 방법
 */
public class BOJ1753 {

	static class Edge {
		int v, weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		int K = Integer.parseInt(br.readLine()) - 1;	// 시작 정점(번호를 1부터 input)
		final int INF = Integer.MAX_VALUE;
		
		// 인접 리스트 준비
		List<Edge>[] adj = new ArrayList[V];
		for (int i = 0; i < V; i++) 
			adj[i] = new ArrayList<>();
		
		// 간선 정보 입력
		for (int i = 0; i < E; i++)  {
			st = new StringTokenizer(br.readLine());
			adj[Integer.parseInt(st.nextToken()) - 1].add(new Edge(Integer.parseInt(st.nextToken()) - 1, 
					Integer.parseInt(st.nextToken())));
		}
		
		// dist 배열
		int[] dist = new int[V];
		boolean[] checked = new boolean[V];
		
		// dist 배열을 INF로 초기화
		Arrays.fill(dist, INF);
		// 시작점은 0으로 변경
		dist[K] = 0;
		
		for (int i = 0; i < V; i++) {
			// check되지 않았으면서,
			// 현재(i)정점으로부터 dist 값이 제일 작은 정점(j)의 번호 찾기
			int min = INF, cur = -1;
			for (int j = 0; j < V; j++) {
				if(!checked[j] && dist[j] < min) {
					min = dist[j];
					cur = j;
				}
			}

			// 현재 정점으로부터 dist 값이 제일 작은 정점을 못 찾았으면
			if(cur == -1) break;
			
			// 찾은 정점(j)으로부터 갈 수 있는 경로가 이미 알고 있는 dist보다 작다면 갱신
			// index가 가지고 있는 모든 간선을 검사
			for (Edge next : adj[cur]) {
				// check되지 않았으면서 다음 노드 까지의 거리가
				// 나까지 거리 + 나로부터 다음 노드까지 거리보다 작다면 갱신 
				if(!checked[next.v] && dist[next.v] > dist[cur] + next.weight) {
					dist[next.v] = dist[cur] + next.weight;
				}
			}
			
			// 체크 완료
			checked[cur] = true;
		}
		
		for (int i = 0; i < V; i++) {
			if(dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
	}
	
}
