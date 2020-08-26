import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14442_v2 {
	 
    static int N, M, K, res = -1, map[][], visited[][];
    static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
 
    static class pos {
        // x좌표, y좌표, 지금까지 거리, 벽을 부순 횟수
        int x, y, dist, crush;
 
        public pos(int x, int y, int dist, int crush) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.crush = crush;
        }
    }
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        K = Integer.parseInt(st.nextToken()); // 부술 수 있는 횟수
        map = new int[N][M];
        visited = new int[N][M];
 
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
                // 벽을 부순 횟수를 저장하는 visited 배열을 큰 값으로 초기화
				visited[i][j] = Integer.MAX_VALUE;
            }
        }
 
        Queue<pos> q = new LinkedList<>();
        visited[0][0] = 0;
        // 시작 칸과 끝나는 칸도 거리에 포함
        q.add(new pos(0, 0, 1, 0));
 
        while (!q.isEmpty()) {
            pos now = q.poll();
            // 목적지에 도착
            if(now.x == N-1 && now.y == M-1) {
                res = now.dist;
                break;
            }
            // 상하좌우로 탐색
            for (int d = 0; d < 4; d++) {
                int xx = now.x + dx[d];
                int yy = now.y + dy[d];
                // 범위를 벗어날 경우 pass
                if(xx < 0 || yy < 0 || xx >= N || yy >= M) continue;
                // 다음 칸으로 이동했을 때, 벽을 부순 횟수를 저장.
                // 다음 칸이 벽이면 벽을 부순 횟수가 1 증가할 것이고,
                // 벽이 아니라면 증가하지 않을 것임
                int nextK = now.crush + map[xx][yy];
                // K번을 초과하여 벽을 부순 상태이거나,
                // 더 적은 횟수로 벽을 부순 적이 있다면 pass
                if(nextK > K || visited[xx][yy] <= nextK) continue;
                
                visited[xx][yy] = nextK;
                q.add(new pos(xx, yy, now.dist + 1, nextK));
            }
        }
        
        System.out.println(res);
    }
}