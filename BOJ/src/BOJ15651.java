import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15651 {
	
	static int N, M;
	static int arr[];
	static int sel[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		sel = new int[M];
		for (int i = 0; i < N; i++) 
			arr[i] = i+1;
		// ó���� 0���� �ڿ����� �� ���·� ����
		process(0);
		System.out.println(sb);
	}
	
	public static void process(int cnt) {
		// M���� �ڿ����� ��� ����ٸ�
		// �� �ڿ������� buffer�� ����
		if(cnt == M) {
			int x = 0;
			for (int j = 0; j < N; j++) {
				sb.append(sel[x++] + " ");
				if(x == M) break;
			}
			sb.append('\n');
			return;
		}
		for (int i = 0; i < N; i++) {
			// �ߺ� �����̹Ƿ� �ش� ���ڰ� ���Ǿ����� Ȯ���� �ʿ� ����
			// �׳� �ٷ� ����ع�����!
			sel[cnt] = arr[i];
			process(cnt+1);
		}
	}
}