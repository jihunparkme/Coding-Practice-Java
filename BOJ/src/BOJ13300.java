import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13300 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 남학생(1)은 남학생끼리, 여학생(0)은 여학생끼리
		// 한 방에는 같은 학년의 학생들을 배정
		int[][] room = new int[2][7];
		
		int N = Integer.parseInt(st.nextToken());	// 학생 수
		int K = Integer.parseInt(st.nextToken());	// 한 방 최대 수용 인원 수
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			room[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]++;
		}
		
		int cnt = 0;
		for (int s = 0; s <= 1; s++) {
			for (int y = 1; y <= 6; y++) {
				int tmp = room[s][y];
				while(tmp > 0) {
					tmp -= K;
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}

}
