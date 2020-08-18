import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1167 {

	static int V, res, leaf;
	static ArrayList<info> relation[];

	static class info {
		int to;
		int wgt;

		public info(int to, int wgt) {
			this.to = to;
			this.wgt = wgt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		V = Integer.parseInt(br.readLine()); // ���� ����
		relation = new ArrayList[V+1];
		for (int i = 1; i <= V; i++)
			relation[i] = new ArrayList<>();
		// ����� ������ ���� ����
		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			while (true) {
				int to = Integer.parseInt(st.nextToken());
				if (to == -1)
					break;
				int weight = Integer.parseInt(st.nextToken());
				relation[from].add(new info(to, weight));
			}
		}

		// Ư�� �������� ���� �ָ� �ִ� ����� ������ ���� ���
		dfs(1, 0, 0);
		// ���� ��忡�� �ö���鼭 ���� �� ���� ã��
		dfs(leaf, 0, 0);
		System.out.println(res);
	}

	public static void dfs(int node, int prnt, int wgt) {
		if (wgt > res) {
			res = wgt;
			leaf = node;
		}
		for (int i = 0; i < relation[node].size(); i++) {
			info next = relation[node].get(i);
			// �θ� ���� pass
			if (next.to == prnt) continue;
			dfs(next.to, node, next.wgt + wgt);
		}
	}
}
