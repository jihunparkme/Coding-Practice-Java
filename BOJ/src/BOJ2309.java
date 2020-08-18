import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;

public class BOJ2309 {

	static int N = 9, K = 100;
	static int child[] = new int[N];
	static int realChild[] = new int[7];
	static boolean ckEnd = false;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// �����̵��� Ű �Է�
		for (int i = 0; i < N; i++) 
			child[i] = Integer.parseInt(br.readLine());
		
		process(0, 0, 0);
	}
	/**
	 * �ϰ� �����̸� ���� �� �ִ� ��� ����� ��
	 * @param idx �������� ��ġ
	 * @param cnt ���� �������� ��
	 * @param sum ���� �����̵� Ű�� ��
	 */
	public static void process(int idx, int cnt, int sum) {
		// Ű�� ���� 100�� �ϰ� �����̰� �� ���� ���¸�
		// ���̻� ����� ���� Ȯ���� �ʿ䰡 �����Ƿ� return
		if(ckEnd) return;
		// �ϰ� �����̰� ������
		if(cnt == 7) {
			// ���� �����̵��� Ű�� ���� 100�� �Ǵ��� Ȯ��
			if(sum == K) {
				// Ű�� ���� 100�̶�� �����̵��� Ű�� ������������ ���� �� ���
				Arrays.sort(realChild);
				for (int i = 0; i < 7; i++) 
					System.out.println(realChild[i]);
				ckEnd = true;
			}
			return;
		}
		if(idx >= N) return;
		// �ϰ� �����̰� �����⵵ ���� Ű�� ���� 100�� �ʰ��ϸ� return
		if(sum > K) return;
		
		// �� �����̰� ��¥? �ѹ� �̾ƺ���?
		realChild[cnt] = child[idx];
		process(idx + 1, cnt + 1, sum + child[idx]);
		// �� �����̴� �� �̰� �Ѿ��
		process(idx + 1, cnt, sum);
	}
	
}
