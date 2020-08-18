import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1068 {

	static int N, D, root, cnt = 0;
	static int parents[];
	static ArrayList<Integer> childs[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		parents = new int[N];	// 부모 정보를 저장
		childs = new ArrayList[N];	// 자식 정보를 저장
		for (int i = 0; i < N; i++)	// ArrayList 초기화
			childs[i] = new ArrayList<Integer>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			// -1 을 입력받으면
			if(input == -1) {
				// 루트 idx를 저장
				root = i;
				// 자기 자신이 부모
				parents[i] = i;
			}
			else {
				// 부모 노드의 정보 저장
				parents[i] = input;
				// 자식 노드들의 정보에 추가
				childs[input].add(i);
			}
		}

		D = Integer.parseInt(br.readLine());
		// root가 삭제되면 ByeBye~!
		if(D == root) {
			System.out.println(cnt);
			return;
		}
		// 지울 노드를 저장하고있는 부모 노드로 가서, 지울 노드의 정보를 삭제
		int delParent = parents[D];
		for (int i = 0; i < childs[delParent].size(); i++) {
			// 부모 노드에서 지울 노드 정보를 찾으면 지워주자
			if(childs[delParent].get(i) == D) {
				childs[delParent].remove(i);
				break;
			}
		}
		// 루트부터 리프 노드를 찾아보자.
		find(root);
		
		System.out.println(cnt);
	}
	
	public static void find(int node) {
		// 자식이 없으면 리프 노드
		if(childs[node].size() == 0) {
			cnt++;
			return;
		}
		// 자식(의 자식의..) 노드들을 모두 탐색
		for (int i = 0; i < childs[node].size(); i++) 
			find(childs[node].get(i));
	}

}
