import java.util.LinkedList;
import java.util.Queue;

public class Progrms_1844_bfs {

    private static int N, M;
    private static boolean visited[][];
    private static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
    private static int result = -1;

    public static void main(String[] args) {
        int solution = solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}});
        System.out.println(solution);

        solution = solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}});
        System.out.println(solution);
    }

    /**
     * - maps는 n x m 크기의 게임 맵의 상태가 들어있는 2차원 배열로, n과 m은 각각 1 이상 100 이하의 자연수입니다.
     * - n과 m은 서로 같을 수도, 다를 수도 있지만, n과 m이 모두 1인 경우는 입력으로 주어지지 않습니다.
     * - maps는 0과 1로만 이루어져 있으며, 0은 벽이 있는 자리, 1은 벽이 없는 자리를 나타냅니다.
     * - 처음에 캐릭터는 게임 맵의 좌측 상단인 (1, 1) 위치에 있으며, 상대방 진영은 게임 맵의 우측 하단인 (n, m) 위치에 있습니다.
     */
    public static int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];

        return bfs(0, 0, maps);
    }

    private static int bfs(int r, int c, int[][] maps) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{r, c, 1});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            r = curr[0];
            c = curr[1];
            int distance = curr[2];

            // 목적지에 도달
            if (r == N-1 && c == M-1) {
                return distance;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                if (maps[nr][nc] == 0) {
                    continue;
                }
                if (!visited[nr][nc] && maps[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, distance + 1});
                }
            }
        }

        return result;
    }
}
