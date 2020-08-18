import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1247 {

	static int T, N, res;
	static Point houses[], OfficePnt, homePnt;
	static boolean visit[];
	static int sel[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			houses = new Point[N];
			visit = new boolean[N];
			sel = new int[N];
			
			OfficePnt = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			homePnt = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
					
			// ��ǥ �Է� [0] : ��������, [1] : ��������
			for (int i = 0; i < N; i++) 
				houses[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			res = 987654321;
			process(0, 0);
			
			System.out.println("#" + tc + " " + res);
		}
	}
	
	public static void process(int cnt, int idx) {
		if(cnt == N) {
			int sum = 0;
			// ������ ù ��° ��
			sum += Math.abs(OfficePnt.x - houses[sel[0]].x) + Math.abs(OfficePnt.y - houses[sel[0]].y);
			for (int i = 1; i < N; i++) {
				sum += Math.abs(houses[sel[i]].x - houses[sel[i-1]].x) + Math.abs(houses[sel[i]].y - houses[sel[i-1]].y);
			}
			sum += Math.abs(homePnt.x - houses[sel[N-1]].x) + Math.abs(homePnt.y - houses[sel[N-1]].y);
			
			res = Math.min(res, sum);
		}
		
		for (int i = 0; i < N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				sel[idx] = i;
				process(cnt + 1, idx + 1);
				visit[i] = false;
			}
		}
	}

}

// ���� ���س��� �Ÿ� ���
// �Ÿ� ����� �ذ��� ���� �������� �� ����

// �� ��ġ (x1, y1)�� (x2, y2) ������ �Ÿ��� |x1-x2| + |y1-y2|
// ��� �湮�ϰ� ���� ���ư��� ��� �� �� �̵��Ÿ��� ���� ª�� ���