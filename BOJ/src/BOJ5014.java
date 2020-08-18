import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5014 {

	static int F, S, G, U, D, cnt = 0;
	static boolean isArrive;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		F = Integer.parseInt(st.nextToken()); // 최고 층
		S = Integer.parseInt(st.nextToken()); // 강호 위치
		G = Integer.parseInt(st.nextToken()); // 회사 위치
		U = Integer.parseInt(st.nextToken()); // up
		D = Integer.parseInt(st.nextToken()); // down

		isArrive = true;

		if (S > G) {
			// 회사가 나보다 낮을 층에 있을 때, 계속 내려가보자.
			while (S > G) {
				Down();
				if(!isArrive) break;
			}
			// 더 내려왔다면 다시 올라가자.
			while (S < G) {
				Up();
				if(!isArrive) break;
			}
			// 같은 층에 도착하지 못했다면 
			if (S != G) isArrive = false;
		} 
		else if (S < G) {
			// 회사가 나보다 높은 층에 있을 때, 계속 올라가보자.
			while(S < G) {
				Up();
				if(!isArrive) break;
			}
			// 더 올라갔다면 다시 내려가자.
			while(S > G) {
				Down();
				if(!isArrive) break;
			}
			// 같은 층에 도착하지 못했다면 
			if (S != G) isArrive = false;
		}
		
		// 처음부터 같은 층일 경우, 바로 여기로 넘어오겠지
		if(isArrive) System.out.println(cnt);
		else System.out.println("use the stairs");
	}

	public static void Up() {
		if(U == 0) {
			isArrive = false;
			return;
		}
		S += U;
		cnt++;
	}

	public static void Down() {
		if(D== 0) {
			isArrive = false;
			return;
		}
		S -= D;
		cnt++;
	}

}
