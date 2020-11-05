import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 완탐 : 벌통M개 채취할수 있는 각 위치마다 가능한 부분집합의 최대합을 먼저 계산후 조합처리
// 조합과정에서 선택되는 동일한 위치의 M개 벌통 채취시의 부분집합의 최대합 계산의 반복계산을 배제하는 방법 
public class Solution2115_teach  {

	static int N, M, C;
	static int[][] map, maxMap;

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
			maxMap = new int[N][N];

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
		makeMaxMap();
		return processCombination();
	}
	private static void makeMaxMap() {
		for (int i = 0; i < N; ++i) { // 연속된 M개를 만들수 있는 위치마다 연속 M개로 만들수 있는 부분집합의 최대이익 계산
			for (int j = 0; j <= N - M; ++j) {
				makeMaxSubset(i, j, 0, 0, 0);
			}
		}
	}
	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {

		if (sum > C) return;
		if (cnt == M) {
			if (maxMap[i][j - M] < powSum) maxMap[i][j - M] = powSum;
			return;
		}
		makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powSum + map[i][j] * map[i][j]); // 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powSum); // 비선택
	}

	private static int processCombination() {
		int max = 0, aBenefit = 0,bBenefit;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j <= N - M; ++j) { // a 일꾼
				aBenefit = maxMap[i][j];
				
				bBenefit = 0;
				// b 일꾼 조합
				for (int j2 = j + M; j2 <= N - M; j2++) { // 같은 행 뒷쪽 열
					if (bBenefit < maxMap[i][j2]) bBenefit = maxMap[i][j2];
				}
				
				for (int i2 = i + 1; i2 < N; i2++) { // 다음 행 첫열부터
					for (int j2 = 0; j2 <= N - M; j2++) {
						if (bBenefit < maxMap[i2][j2]) bBenefit = maxMap[i2][j2];
					}
				}
				if(max<aBenefit+bBenefit) max = aBenefit+bBenefit;
			}
		}
		return max;
	}
	private static int processCombination2() {
		int max = 0, temp = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j <= N - M; ++j) { // a 일꾼
				
				// b 일꾼 조합
				int i2 = i, j2 = j+M;
				while(true) {
					if(j2>N-M) { // 해당 열 위치에서 연속 M개 확보가 안되면
						i2++; // 다음행
						j2 = 0; // 첫열로.
					}
					if(i2>=N) break;
					temp = maxMap[i][j] + maxMap[i2][j2];
					if (max < temp) max = temp;
					j2++;
				}
			}
		}
		return max;
	}
}