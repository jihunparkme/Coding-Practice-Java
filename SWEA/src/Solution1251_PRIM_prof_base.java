import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1251_PRIM_prof_base {

	private static int N;
	private static long[][] adjMatrix;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// 섬의 갯수
			N = Integer.parseInt(br.readLine());

			// X좌표 입력
			int[] x = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				x[i] = Integer.parseInt(st.nextToken());
			// Y좌표 입력
			int[] y = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				y[i] = Integer.parseInt(st.nextToken());
			// 인접행렬 만들기
			adjMatrix = new long[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					adjMatrix[i][j] = adjMatrix[j][i] = getDistance(x[i], x[j], y[i], y[j]);
				}
			}
			double E = Double.parseDouble(br.readLine());

			// 총 환경 부담금을 최소로 지불하며, N개의 모든 섬을 연결할 수 있는 교통 시스템을 설계
			System.out.println("#" + tc + " " + Math.round(E * makeMST()));
		}
	}

	private static long makeMST() {
		
		long[] minEdge = new long[N];
		boolean[] visited = new boolean[N];
		
		long result = 0;
		int cnt = 0;
		
		Arrays.fill(minEdge, Long.MAX_VALUE);
		minEdge[0] = 0;	// 0섬을 시작점으로
		
		while(true) {
			
			// 1단계 : 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 선택
			long min = Long.MAX_VALUE;
			int minNo = 0; // 최소간선비용의 정점
			for (int i = 0; i < N; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minNo = i;
				}
			}
			
			visited[minNo] = true;	// 정점 방문 처리(신장트리에 포함)
			result += min;
			if(++cnt == N) break;
			
			// 2단계 : 선택된 정점에서 신장트리에 포함되지 않은 다른 정점들로의 간선 비용을 고려하여 minEdge 업데이트
			for (int i = 0; i < N; i++) {
				if(!visited[i] && adjMatrix[minNo][i] > 0 && minEdge[i] > adjMatrix[minNo][i]) {
					minEdge[i] = adjMatrix[minNo][i];
				}
			}
			
		}
		
		return result;
	}

	private static long getDistance(int x1, int x2, int y1, int y2) {
		return (long)(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
}
