import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution3282 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 물건 개수
			int K = Integer.parseInt(st.nextToken()); // 가방 부피
			int[][] dp = new int[N + 1][K + 1];
			ArrayList<Item> bag = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				bag.add(new Item(a, b));
			}
			
			for (int i = 0; i < dp.length; i++) {
				
			}

			System.out.println("#" + tc + " " + "dd");
		}
	}

}

class Item {
	int weight;
	int value;

	public Item(int weight, int value) {
		super();
		this.weight = weight;
		this.value = value;
	}

}