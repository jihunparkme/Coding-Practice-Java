import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ19535 {

	static int N, res, ans, D, G, leaf;
	static ArrayList<Integer>[] tree;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) 
			tree[i] = new ArrayList<>();
			
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			if(tree[i].size() == 3) G++;
			else if(tree[i].size() > 3) {
				ans = 0;
				countG(tree[i].size(), 0, 0);
				G += ans;
			}
		}
				
		// 리프 노드 찾기
		findLeaf(-1, 1);
		
		// 리프노드부터 ㄷ Tree 를 찾으러 가보자.
		findDTree(-1, leaf, 0);
		go(-1, leaf);
		
		if(G * 3 < D) System.out.println("D");
		else if(G * 3 > D) System.out.println("G");
		else System.out.println("DUDUDUNGA");
	}

	private static void countG(int R, int cnt, int start) {
		if (cnt == R) {
			ans++;
		}
		// 현자리에 시작위치 수부터 끝위치 수까지 시도
		for (int i = start; i < N; i++) {
			countG(R, cnt + 1, i + 1);
		}
		
	}

	private static void go(int parent, int child) {
		
		for (int i = 0; i < tree[child].size(); i++) {
			// 내 부모 노드면 pass
			if(tree[child].get(i) == parent) continue;
			findDTree(-1, tree[child].get(i), 0);
		}
		
	}

	private static void findDTree(int parent, int child, int cnt) {
		
		if(cnt == 3) { 
			D++;
			return;
		}
		
		for (int i = 0; i < tree[child].size(); i++) {
			// 내 부모 노드면 pass
			if(tree[child].get(i) == parent) continue;
			findDTree(child, tree[child].get(i), cnt + 1);
		}
		
	}

	private static void findLeaf(int parent, int child) {
		// leafNode를 찾았으면
		if(leaf != 0) return;
		// leafNode 발견!
		if(tree[child].size() == 1) {
			leaf = child;
			return;
		}
		
		for (int i = 0; i < tree[child].size(); i++) {
			// 내 부모 노드면 pass
			if(tree[child].get(i) == parent) continue;
			findLeaf(child, tree[child].get(i));
		}
		
	}
	
}
