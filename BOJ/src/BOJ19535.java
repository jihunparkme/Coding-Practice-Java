import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ19535 {

	static int N, D, G;
	static boolean[] checked;
	static ArrayList<Integer>[] tree;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		checked = new boolean[N+1];
		for (int i = 1; i <= N; i++) 
			tree[i] = new ArrayList<>();
			
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}

		// ㅈ Tree 찾기
		for (int i = 1; i <= N; i++) {
			if(tree[i].size() >= 3) {
				// nC3 = n!/3!(n-3)! = n(n-1)(n-2) / 3 * 2
				int n = tree[i].size();
				G += (n * (n-1) * (n-2)) / (3 * 2);
			}
		}

		// ㄷ Tree 찾기
		for (int parent = 1; parent <= N; parent++) {
			for (int child = 0; child < tree[parent].size(); child++) {
				// 이미 확인한 노드일 경우
				if(checked[tree[parent].get(child)]) continue;
				// (현재 노드의 간선 - 1) * (연결된 노드의 간선 - 1) 
				D += (tree[parent].size() - 1) * (tree[tree[parent].get(child)].size() - 1);
				// check!
				checked[tree[parent].get(child)] = true;
			}
		}

		if(G * 3 < D) System.out.println("D");
		else if(G * 3 > D) System.out.println("G");
		else System.out.println("DUDUDUNGA");
	}
	
}
