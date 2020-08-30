import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ19535_v2 {

	static int N, D, G;
	static boolean[] isStart;
	static ArrayList<Integer>[] tree;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		isStart = new boolean[N+1];
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
				// nC3 = n! / 3!(n-3)! = n(n-1)(n-2) / 3 * 2
				int n = tree[i].size();
				G += (n * (n-1) * (n-2)) / (3 * 2);
			}
		}

		// ㄷ Tree 찾기
		for (int i = 1; i <= N; i++) {
			// 시작점
			isStart[i] = true;
			findDTree(-1, i, 0);	
		}

		if(G * 3 < D) System.out.println("D");
		else if(G * 3 > D) System.out.println("G");
		else System.out.println("DUDUDUNGA");
	}

	private static void findDTree(int parent, int child, int cnt) {
		
		// ㄷ Tree가 만들어졌을 경우 count
		if(cnt == 3) {
			// 시작점이었던 노드가 마지막지점이라면 pass
			if(!isStart[child]) D++;
			return;
		}
		
		for (int i = 0; i < tree[child].size(); i++) {
			// 부모 노드일 경우 pass
			if(tree[child].get(i) == parent) continue;
			findDTree(child, tree[child].get(i), cnt + 1);
		}
	}	
	
}
