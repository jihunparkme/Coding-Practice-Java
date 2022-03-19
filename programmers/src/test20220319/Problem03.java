package test20220319;

import java.util.*;

public class Problem03 {
    public static void main(String[] args) {
        boolean[] solution = solution(new String[][]{{".....", ".XXX.", ".X.X.", ".XXX.", "....."}, {"XXXXX", "XXXXX", "XXX.X", "XXX.X", "XXXXX"}, {"XXXXX", "X...X", "X.X.X", "X...X", "XXXXX"}, {"....X", ".....", "XXX..", "X.X..", "XXX.."}, {".......", "XXX.XXX", "X.X.X.X", "XXX.XXX", "......."}, {"XXXXX", "XX.XX", "X...X", "XX.XX", "XXXXX"}});
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }

    private static int R;
    private static int C;
    private static int remain;
    private static int[] dr= {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    public static boolean[] solution(String[][] grids) {
        boolean[] answer = new boolean[grids.length];

        for (int c = 0; c < grids.length; c++) {
            answer[c] = isSquare(grids[c]);
        }

        return answer;
    }

    private static boolean isSquare(String[] grids) {
        R = grids.length;
        C = grids[0].length();
        boolean[][] blackBoard = new boolean[R][C];
        boolean[][] visited = new boolean[R][C];

        int cntBlack = 0;
        Point startBlack = null;
        Point startWhite = null;
        for (int i = 0; i < grids.length; i++) {

            char[] row = grids[i].toCharArray();
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 'X') {
                    blackBoard[i][j] = true;
                    cntBlack++;
                    if (startBlack == null) startBlack = new Point(i, j);
                } else {
                    if (startWhite == null) startWhite = new Point(i, j);
                }
            }
        }

        int cnt = countSquareBlock(blackBoard, visited, startBlack);

        if (cntBlack == cnt) {
            return isCenterSquare(blackBoard, startWhite, cnt);
        }

        return false;
    }

    private static boolean isCenterSquare(boolean[][] blackBoard, Point start, int cnt) {

        if (cnt < 3) return true;

        remain = R * C - cnt;
        while (remain != 0) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (!blackBoard[i][j]) {
                        if (checkWhite(blackBoard, start)) return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkWhite(boolean[][] blackBoard, Point start) {

        int cnt = 0;
        int line1 = 0;
        int line2 = 0;
        for (int d = 0; d < 4; d++) {
            int rr = start.r + dr[d];
            int cc = start.c + dc[d];
            if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
            if (blackBoard[rr][cc] == false) {
                line1 = d;
                line2 = (d + 2) % 4;
            }
        }

        int side = -1;
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int rr = now.r + dr[d];
                int cc = now.c + dc[d];
                // 범위를 벗어나면 pass
                if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
                // 검정색이거나 이미 방문한 곳이면 pass
                if(blackBoard[rr][cc]) continue;
                // 흰색
                if (d == line1 || d == line2) {
                    blackBoard[rr][cc] = true;
                    remain--;
                    q.add(new Point(rr, cc));
                } else {
                    blackBoard[rr][cc] = true;
                    remain--;
                    if (side == -1) side = d;
                    else {
                        if (side != d) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private static int countSquareBlock(boolean[][] blackBoard, boolean[][] visited, Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);

        int cnt = 0;
        while(!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int rr = now.r + dr[d];
                int cc = now.c + dc[d];
                // 범위를 벗어나면 pass
                if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
                // 흰색이거나 이미 방문한 곳이면 pass
                if(!blackBoard[rr][cc] || visited[rr][cc]) continue;
                // 빈칸
                visited[rr][cc] = true;
                cnt++;
                q.add(new Point(rr,cc));
            }
        }

        return cnt;
    }

    private static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
