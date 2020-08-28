import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution4012 {

	static int T, N, res, map[][], sum[];
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = 987654321;
			// 식재료들을 각각 N / 2개씩 나누어 두 개의 요리를 진행
			visited = new boolean[N];

			selectIngredient(0, 0);
			System.out.println("#" + tc + " " + res);
		}

	}

	private static void selectIngredient(int start, int cnt) {
		// A가 N/2개의 식재료를 모두 골랐다면 나머지는 B의 식재료
		if(cnt == N/2) {
			ArrayList<Integer> A, B;
			A = new ArrayList<>();
			B = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				// A의 식재료
				if(visited[i]) A.add(i);
				else B.add(i);
			}
			
			int sumA = 0, sumB = 0;
			// A 시너지
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < N/2; j++) {
					if(i == j) continue;
					sumA += map[A.get(i)][A.get(j)];
				}
			}
			
			// B 시너지
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < N/2; j++) {
					if(i == j) continue;
					sumB += map[B.get(i)][B.get(j)];
				}
			}

			// 맛의 차이가 2보다 작을 수 없다.
			int ans = Math.abs(sumA - sumB);
			res = Math.min(res, ans);
			return;
		}
		if(start >= N) return;
		
		// A먼저 재료를 선택
		for (int i = start; i < N; i++) {
			// 이 식재료를 사용해보자.
			visited[i] = true;
			selectIngredient(i+1, cnt+1);
			// 그냥 사용 안할래
			visited[i] = false;
		}
		
	}

}

/*
식재료 i는 식재료 j와 같이 요리하게 되면 궁합이 잘 맞아 시너지 Sij가 발생한다. (1 ≤ i ≤ N, 1 ≤ j ≤ N, i ≠ j)
각 음식의 맛은 음식을 구성하는 식재료들로부터 발생하는 시너지 Sij들의 합이다.

식재료 i를 식재료 j와 같이 요리하게 되면 발생하는 시너지 Sij의 정보가 주어지고, 
가지고 있는 식재료를 이용해 A음식과 B음식을 만들 때,
두 음식 간의 맛의 차이가 최소가 되는 경우를 찾고 그 최솟값을 정답으로 출력하는 프로그램을 작성하라.
다른 경우에서는 맛의 차이가 2보다 작을 수 없다.
*/