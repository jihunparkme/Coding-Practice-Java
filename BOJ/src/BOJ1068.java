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
		parents = new int[N];	// �θ� ������ ����
		childs = new ArrayList[N];	// �ڽ� ������ ����
		for (int i = 0; i < N; i++)	// ArrayList �ʱ�ȭ
			childs[i] = new ArrayList<Integer>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			// -1 �� �Է¹�����
			if(input == -1) {
				// ��Ʈ idx�� ����
				root = i;
				// �ڱ� �ڽ��� �θ�
				parents[i] = i;
			}
			else {
				// �θ� ����� ���� ����
				parents[i] = input;
				// �ڽ� ������ ������ �߰�
				childs[input].add(i);
			}
		}

		D = Integer.parseInt(br.readLine());
		// root�� �����Ǹ� ByeBye~!
		if(D == root) {
			System.out.println(cnt);
			return;
		}
		// ���� ��带 �����ϰ��ִ� �θ� ���� ����, ���� ����� ������ ����
		int delParent = parents[D];
		for (int i = 0; i < childs[delParent].size(); i++) {
			// �θ� ��忡�� ���� ��� ������ ã���� ��������
			if(childs[delParent].get(i) == D) {
				childs[delParent].remove(i);
				break;
			}
		}
		// ��Ʈ���� ���� ��带 ã�ƺ���.
		find(root);
		
		System.out.println(cnt);
	}
	
	public static void find(int node) {
		// �ڽ��� ������ ���� ���
		if(childs[node].size() == 0) {
			cnt++;
			return;
		}
		// �ڽ�(�� �ڽ���..) ������ ��� Ž��
		for (int i = 0; i < childs[node].size(); i++) 
			find(childs[node].get(i));
	}

}
