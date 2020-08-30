import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1247 {

	static int T, N, res;
	static Point customers[], Office, home;
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			customers = new Point[N];
			visited = new boolean[N];
			
			Office = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// ȸ�� ��ǥ
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// �� ��ǥ
					
			// ������ �� ��ǥ
			for (int i = 0; i < N; i++) 
				customers[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			res = 987654321;
			process(0, Office, 0);
			
			System.out.println("#" + tc + " " + res);
		}
	}
	
	public static void process(int cnt, Point prev, int dist) {
		// ���ݱ����� �Ÿ��� �̹� ������ ��κ��� ũ�� ���̻� Ȯ���� �ʿ䰡 ����
		if(dist >= res) return;
		// ������ �ϼ��Ǿ��ٸ� �Ÿ� ���
		if(cnt == N) {
			// ������ �鸰 �� ������ �� �������� �Ÿ�
			dist += Math.abs(prev.x - home.x) + Math.abs(prev.y - home.y);
			res = Math.min(res, dist);
		}
		
		// ���� ���ϱ�
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				process(cnt + 1, customers[i], dist + Math.abs(prev.x - customers[i].x) + Math.abs(prev.y - customers[i].y));
				visited[i] = false;
			}
		}
	}

}

// �� ��ġ (x1, y1)�� (x2, y2) ������ �Ÿ��� |x1-x2| + |y1-y2|
// ��� �湮�ϰ� ���� ���ư��� ��� �� �� �̵��Ÿ��� ���� ª�� ���