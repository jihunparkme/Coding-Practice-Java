import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9372 {

	static int N, M;
	static boolean visited[];
	static ArrayList<Integer> plane[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int res = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 국가의 수
			M = Integer.parseInt(st.nextToken());	// 비행기의 종류
			
			visited = new boolean[N+1];
			plane = new ArrayList[N+1];
			for (int i = 0; i <= N; i++) 
				plane[i] = new ArrayList<>();
			
			// 비행기편 입력
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				plane[a].add(b);
				plane[b].add(a);
			}
			
			// 가장 적은 종류의 비행기를 타고 모든 국가들을 여행
			Queue<Integer> q = new LinkedList<>();
			// 1번 국가부터 방문
			q.add(1);
			visited[1] = true;
			while(!q.isEmpty()) {
				int now = q.poll();
				
				for (int i = 0; i < plane[now].size(); i++) {
					int next = plane[now].get(i);
					// 이미 들린 국가라면 pass
					if(visited[next]) continue;
					// 들리지 않은 국가라면 떠나보자!
					q.add(next);
					visited[next] = true;
					res++;
				}
			}
			
			System.out.println(res);
		}
	}

}
