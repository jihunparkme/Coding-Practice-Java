import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 완탐 : 조합후 각 조합된 선택마다 부분집합의 합 계산 
public class Solution2115_teach2  {

	static int N, M, C; // N: 벌통크기, M: 연속된벌통수, C:채취량
	static int[][] map; // map : 입력된  벌통정보

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int testN = Integer.parseInt(in.readLine().trim());
		StringTokenizer st = null;
		for (int t = 1; t <= testN; ++t) {
			st = new StringTokenizer(in.readLine().trim());
			N = Integer.parseInt(st.nextToken()); // 벌통 크기
			M = Integer.parseInt(st.nextToken()); // 선택할 벌통개수
			C = Integer.parseInt(st.nextToken()); // 벌꿀 채취량
			map = new int[N][N];

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(in.readLine().trim());
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("#" + t + " " + getMaxBenefit());
		}
	}

	private static int getMaxBenefit() {
		return processCombination();
	}
	private static int processCombination() {
		int max = 0, aBenefit = 0,bBenefit;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j <= N - M; ++j) { // a 일꾼
				maxSum = 0;
				makeMaxSubset(i, j, 0, 0, 0); //현재 A일꾼이 선택한 M개의 벌통 중 이익을 최대로 하는 부분집합의 합 구하기 
				aBenefit = maxSum;
				
				maxSum = 0;
				bBenefit = 0;
				// b 일꾼 조합
				for (int j2 = j + M; j2 <= N - M; j2++) { // 같은 행 뒷쪽 열
					makeMaxSubset(i, j2, 0, 0, 0); //현재 B일꾼이 선택한 M개의 벌통 중 이익을 최대로 하는 부분집합의 합 구하기 
					if (bBenefit < maxSum) bBenefit = maxSum;
				}

				for (int i2 = i + 1; i2 < N; i2++) { // 다음 행 첫열부터
					for (int j2 = 0; j2 <= N - M; j2++) {
						makeMaxSubset(i2, j2, 0, 0, 0);
						if (bBenefit < maxSum) bBenefit = maxSum;
					}
				}
				if(max<aBenefit+bBenefit) max = aBenefit+bBenefit;
			}
		}
		return max;
	}
	static int maxSum;
	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {

		if (sum > C) return;
		if (cnt == M) {
			if (maxSum < powSum) maxSum = powSum;
			return;
		}
		makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powSum + map[i][j] * map[i][j]); // 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powSum); // 비선택
	}

}