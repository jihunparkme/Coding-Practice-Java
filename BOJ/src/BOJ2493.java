import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {

	static int N, res[];
	static class Tower {
		int num, height;

		public Tower(int num, int height) {
			this.num = num;
			this.height = height;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		res = new int[N + 1];
		Stack<Tower> towers = new Stack<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			
			int h = Integer.parseInt(st.nextToken());
			
			while(!towers.isEmpty()) {
				// 지금 타워가 더 높다면 pop
				if(towers.peek().height < h) {
					towers.pop();
				} 
				// 지금 타워가 더 낮다면 앞 타워 번호를 저장
				else {
					res[i] = towers.peek().num;
					break;
				}
			}
			
			// 수신을 받을 타워가 없다면
			if(towers.isEmpty()) {
				res[i] = 0;
			}
			
			towers.add(new Tower(i, h));
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.print(res[i] + " ");
		}
	}

}
