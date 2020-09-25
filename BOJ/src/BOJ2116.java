import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2116 {

	static int N, dice[][], res;
	static int[] opp = { 0, 6, 4, 5, 2, 3, 1 };
	static boolean[][] isAdhere;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dice = new int[N][7];
		
		// Make dice
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= 6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		pileDice();
		
		System.out.println(res);
	}

	private static void pileDice() {

		int up = 0;
		// 1번 주사위의 윗 면이 1 ~ 6일 경우
		for (int num = 1; num <= 6; num++) {

			isAdhere = new boolean[N][7];
			for (int f = 1; f <= 6; f++) {
				if(dice[0][f] == num) {
					up = dice[0][f];
					
					isAdhere[0][f] = isAdhere[0][opp[f]] = true;
					break;
				}
			}
		
			// 1번 주사위의 윗 면이 정해지면 그에 맞게 주사위 쌓기
			for (int i = 1; i < N; i++) {
				
				for (int f = 1; f <= 6; f++) {
					if(dice[i][f] == up) {
						up = dice[i][opp[f]];
						
						isAdhere[i][f] = isAdhere[i][opp[f]] = true;
						break;
					}
				}
			}
			
			// 주사위가 다 쌓아지면
			res = Math.max(res, sumSide());
		}		
	}

	// 옆 면의 최대 값들을 합하기
	private static int sumSide() {
		
		int sum = 0;
		// 1번 주사위부터
		for (int i = 0; i < N; i++) {
			
			int max = 0;
			// 가장 큰 옆면 숫자를 찾고
			for (int j = 1; j <= 6; j++) {
				if(!isAdhere[i][j])
					max = Math.max(max, dice[i][j]);
			}
			// 누적
			sum += max;			
		}
		
		return sum;
	}

}
