import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution3124 {

	public static class node implements Comparable<node> {
		int s;
		int e;
		int w;

		public node(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(node o) {
			return this.w - o.w;
		}

	}

	static int cnt, parents[];
	static long res;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 정점 초기화
			int V = Integer.parseInt(st.nextToken());
			parents = new int[V+1];
			for (int i = 1; i <= V; i++) 
				parents[i] = i;
			
			int E = Integer.parseInt(st.nextToken());
			ArrayList<node> info = new ArrayList<>();
			// 각 간선에 대한 정보(간선의 가중치)
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				info.add(new node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())));
			}
			// 가중치 기준 오름차순 정렬
			Collections.sort(info);
			
			res = 0;
			cnt = 0;
			// 정렬된 간선정보를 하나씩 꺼내서 연결해보기
			for (int i = 0; i < E; i++) {
				if(cnt == V-1) break;
				node tmp = info.get(i);
				union(tmp.s, tmp.e, tmp.w);
			}

			System.out.println("#" + tc + " " + res);
		}
	}
	
	public static int find(int x) {
		if(x != parents[x]) parents[x] = find(parents[x]);
		return parents[x];
	}
	
	public static void union(int x, int y, int w) {
		int px = find(x);
		int py = find(y);
		// 사이클이 생성되지 않는다면
		if(px != py) {
			parents[px] = py;
			res += w;
			cnt++;
		}
	}

}
