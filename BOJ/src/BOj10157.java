import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOj10157 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		boolean[][] check = new boolean[R][C];	
		
		// 시작점
		// dir(방향) 하:0, 우:1, 상:2, 좌:3
		int r = 0, c = 0, cnt = 1, dir = 0;
		boolean isFind = false;
		check[0][0] = true;
		if(K == 1) isFind = true;
		while(!isFind && r < R && c < C) {
			// 위로 이동
			if(dir == 2) {
				// 위로 이동 가능할 때까지 이동
				while( r - 1 >= 0 && !check[r - 1][c]) {
					r--;
					check[r][c] = true;
					if(++cnt == K) {
						isFind = true;
						break;
					}
				}
			}
			// 오른쪽으로 이동
			else if(dir == 1) {
				// 오른쪽으로 이동 가능할 때까지 이동
				while( c + 1 < C && !check[r][c + 1]) {
					c++;
					check[r][c] = true;
					if(++cnt == K) {
						isFind = true;
						break;
					}
				}
			}
			// 아래로 이동
			else if(dir == 0) {
				// 아래로 이동 가능할 때까지 이동
				while( r + 1 < R && !check[r + 1][c]) {
					r++;
					check[r][c] = true;
					if(++cnt == K) {
						isFind = true;
						break;
					}
				}
			} // 왼쪽으로 이동
			else if(dir == 3) {
				// 왼쪽으로 이동 가능할 때까지 이동
				while( c - 1 >= 0 && !check[r][c - 1]) {
					c--;
					check[r][c] = true;
					if(++cnt == K) {
						isFind = true;
						break;
					}
				}
			}
			
			dir = dir + 1 < 4 ? dir + 1 : 0; 
			if(cnt == R * C) break;
		}
		
		// 모든 좌석이 배정되어 해당 대기번호의 관객에게 좌석을 배정할 수 없는 경우에는 0(숫자 영)을 출력
		if(isFind) System.out.println((c + 1) + " " + (r + 1));
		else System.out.println("0");
	}

}
