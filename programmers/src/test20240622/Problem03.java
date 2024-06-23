package test20240622;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem03 {

    static int N, M;
    static boolean isFinished = false;
    static boolean[][] isEdge;
    static boolean[][] isVisited;

    public static void solution(final int x, final int y, final int count) {
        if (count > M) {
            return;
        }

        isVisited[x][y] = true;
        for (int k = 0; k < N; k++) {
            if (isVisited[y][k]) continue;
            if (count == M && !isEdge[y][k]) {
                isFinished = true;
                return;
            }
            solution(y, k, count + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        isEdge = new boolean[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            final String[] split = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                isEdge[i][j] = split[j].equals("1");
            }
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isEdge[i][j]) continue;

                solution(i, j, 1);
                if (isFinished) {
                    System.out.print("1");
                    return;
                }
            }
        }

        System.out.print("0");
    }
}
/**
 * 4
 * 0111
 * 1000
 * 1000
 * 0010
 * 3
 */