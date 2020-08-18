import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3289 {

	static int parents[];
	
	public static int find(int x) {
		if (x != parents[x]) 
			parents[x] = find(parents[x]);
		return parents[x];
		
//		// 내가 대표라면 나를 return
//		if(x == parents[x])	return x;
//		// 내가 대표가 아니면 내 부모 idx를 저장
//		parents[x] = find(parents[x]);
//		return parents[x];
	}
	
	public static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		// 각 부모가 다르다면(다른 집합) 합치기
		if(px != py) parents[px] = py;
	}
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());	// 연산의 개수
			parents = new int[N+1];
			// Make Set
			for (int i = 1; i <= N; i++) 
				parents[i] = i;
			
			sb.append("#" + tc + " ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int oper = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				

				if(oper == 0) {	// Union 0
					union(a, b);
				}
				else { // Find 1
					if(find(a) == find(b))
						sb.append('1');
					else
						sb.append('0');
				}
			}
			
			System.out.println(sb);
			sb.setLength(0);
		}

	}

}
