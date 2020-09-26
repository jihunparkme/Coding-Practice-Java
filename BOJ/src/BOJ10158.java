import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 시간초과
 * 시뮬레이션으로 풀려고 했는데,
 * w, h의 최대는 40,000 으로 최대 w x h 는 1,600,000,000 이 되어버린다..
 * 심지어 최대 t도 200,000,000 이므로 절대 컴퓨터가 1초만에 해결할 수 없다.
 */
public class BOJ10158 {

	static int R, C, sR, sC, T;
	// 우하 다이아몬드
	static int[] dr = {1, 1, -1, -1}, dc = {1, -1, -1, 1}; 
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		sC = Integer.parseInt(st.nextToken());
		sR = Integer.parseInt(st.nextToken());
		
		T = Integer.parseInt(br.readLine()); 
		
		int d = 0;
		while(--T >= 0) {
			
			// 다음 칸으로
			int nr = sR + dr[d]; 
			int nc = sC + dc[d]; 
			
			// d가 0이고 
			if(d == 0) {
				// c가 C를 초과할 경우 d는 1
				if(nc > C) d = 1;
				// r이 R을 초과할 경우 d는 3
				else if(nr > R) d = 3;
				// 초과 안하면 이동하고 continue
				else {
					sR = nr;
					sC = nc;
					continue;
				}
			}
			// d가 1이고 
			else if(d == 1) {
				// c가 0보다 작을 경우 d는 0
				if(nc < 0) d = 0;
				// r이 R을 초과할 경우 d는 2
				else if(nr > R) d = 2;
				// 초과 안하면 이동하고 continue
				else {
					sR = nr;
					sC = nc;
					continue;
				}
			}
			// d가 2이고
			else if(d == 2) {
				// c가 0보다 작을 경우 d는 3
				if(nc < 0) d = 3;
				// r이 0보다 작을 경우 d는 1
				else if(nr < 0) d = 1;
				// 초과 안하면 이동하고 continue
				else {
					sR = nr;
					sC = nc;
					continue;
				}
			}
			// d가 3이고
			else {
				// c가 C를 초과할 경우 d는 2
				if(nc > C) d = 2;
				// r이 0보다 작을 경우 d는 0
				else if(nr < 0) d = 0;
				// 초과 안하면 이동하고 continue
				else {
					sR = nr;
					sC = nc;
					continue;
				}
			}

			// 초과한 경우는 T++;
			T++;
		}
		
		System.out.println(sC + " " + sR);
	}

}
