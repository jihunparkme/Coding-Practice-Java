import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2164 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Queue<Integer> q = new LinkedList<>();
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++)
			q.add(i);

		while (q.size() != 1) {
			// 제일 위에 있는 카드를 바닥에 버린다
			q.poll();
			// 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로
			q.add(q.poll());
		}

		System.out.println(q.poll());
	}

}
