import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ5567 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<Integer> lst[] = new ArrayList[M+1];
		Set<Integer> res = new HashSet<Integer>();
		for (int i = 1; i <= M; i++) 
			lst[i] = new ArrayList<Integer>();
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			lst[a].add(b);
			lst[b].add(a);
		}
		// 郴 模备
		for (int i = 0; i < lst[1].size(); i++) {
			int tmp = lst[1].get(i); 
			res.add(tmp);
			// 郴 模备狼 模备
			for (int j = 0; j < lst[tmp].size(); j++) {
				res.add(lst[tmp].get(j));
			}
		}
		
		System.out.println(res.size() - 1);
	}

}
