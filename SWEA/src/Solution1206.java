import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1206 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		int[] bds;
		for (int tc = 1; tc <= T; tc++) {
			
			int res = 0;
			int N = Integer.parseInt(br.readLine());
			bds = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) 
				bds[i] = Integer.parseInt(st.nextToken());
			
			for (int i = 2; i < N - 2; i++) {
				// 양옆 두 빌딩들보다 높은가?
				boolean isHigher = true;
				int maxBd = 0;
				for (int j = i - 2; j <= i + 2; j++) {
					if(j == i) continue;
					// 양 옆에 더 높은 빌딩이 있다면 pass
					if(bds[i] < bds[j]) {
						isHigher = false;
						break;
					}
					// 그중 가장 높은 빌딩
					if(maxBd < bds[j]) maxBd = bds[j];
				}
				// 가장 높다면
				if(isHigher) res += (bds[i] - maxBd);
			}
			
			System.out.println("#" + tc + " " + res);
		}

	}

}
