import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649_v2 {
	
	static int N, M;
	static int arr[];
	static boolean visited[];
	static int sel[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		arr = new int[N];
		visited = new boolean[N];
		sel = new int[M];
		for (int i = 0; i < N; i++) 
			arr[i] = i+1;
		
		process(0);
		System.out.println(sb);
	}
	
	public static void process(int cnt) {
		// M���� �ڿ����� ����ٸ� 
		if(cnt == M) {
			int x = 0;
			// ���õ� �ڿ����� buffer�� ����
			for (int j = 0; j < N; j++) {
				if(visited[j]) {
					sb.append(sel[x++] + " ");
				}
			}
			sb.append('\n');
			return;
		}
		for (int i = 0; i < N; i++) {
			// �ߺ��� ������� �����Ƿ�
			// ������ ���� �ڿ����� ���
			if(!visited[i]) {
				// �� �ڿ����� ����غ���.
				visited[i] = true;
				// ���� �ڿ����� �迭�� ����
				sel[cnt] = arr[i];
				
				// �� �ڿ����� ��������Ƿ� cnt + 1 ���ְ�, ����Լ��� �Ѱ�����.
				process(cnt+1);
				
				// �� �ڿ����� ������� ���� ���.
				visited[i] = false;
			}
		}
	}
}
