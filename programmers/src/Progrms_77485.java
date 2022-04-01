public class Progrms_77485 {
    public static void main(String[] args) {
        int[] solution = solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}});
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }

    private static int[][] board;
    public static int[] solution(int rows, int columns, int[][] queries) {

        int[] answer = new int[queries.length];
        board = new int[rows + 1][columns + 1];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                board[i][j] = (i-1) * columns + j;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotation(queries[i]);
        }

        return answer;
    }

    private static int rotation(int[] query) {
        int r1 = query[0];
        int c1 = query[1];
        int r2 = query[2];
        int c2 = query[3];

        int min = Integer.MAX_VALUE;
        int tmp = board[r1][c1];
        min = Math.min(min, tmp);
        for (int i = r1; i < r2; i++) {
            min = Math.min(min, board[i + 1][c1]);
            board[i][c1] = board[i + 1][c1];
        }
        for (int i = c1; i < c2; i++) {
            min = Math.min(min, board[r2][i + 1]);
            board[r2][i] = board[r2][i + 1];
        }
        for (int i = r2; i > r1 ; i--) {
            min = Math.min(min, board[i -1][c2]);
            board[i][c2] = board[i -1][c2];
        }
        for (int i = c2; i > c1 + 1; i--) {
            min = Math.min(min, board[r1][i - 1]);
            board[r1][i] = board[r1][i - 1];
        }
        board[r1][c1 + 1] = tmp;

        return min;
    }

    private static void print() {
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
