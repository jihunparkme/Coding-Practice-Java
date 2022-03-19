package test20220319;

public class Problem02 {
    public static void main(String[] args) {
//        int[] solution = solution(new int[]{1, 1, 2, 0});
        int[] solution = solution(new int[]{1, 1, 1});
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }

    private static int[] check;
    private static int[] answer;
    private static int N;
    public static int[] solution(int[] black_caps) {
        N = black_caps.length;
        check = new int[N];
        answer = new int[N];

        checkFirstLast(black_caps);
        find(black_caps, N - 2, true);

        return answer;
    }

    private static void find(int[] black_caps, int idx, boolean certain) {

        if (idx == 0 && certain) {
            for (int i = 0; i < N; i++) {
                answer[i] = check[i];
            }
            return;
        }

        if (idx == 0) {
            for (int i = 1; i < N - 1; i++) {
                answer[i] = check[i];
            }
            return;
        }

        int cntBlack = black_caps[idx];
        if (cntBlack == 2) {
            check[idx - 1] = 1;
            check[idx + 1] = 1;
            find(black_caps, idx - 1, true);
        } else if (cntBlack == 0) {
            check[idx - 1] = 2;
            check[idx + 1] = 2;
            find(black_caps, idx - 1, true);
        } else {
            check[idx - 1] = 1;
            find(black_caps, idx - 1, false);
            check[idx - 1] = 0;

            if (check[idx + 1] != 0) {
                check[idx - 1] = 1;
                find(black_caps, idx - 1, true);
                check[idx - 1] = 0;
            } else {
                check[idx + 1] = 1;
                find(black_caps, idx - 1, false);
                check[idx + 1] = 0;
            }
        }
    }

    private static void checkFirstLast(int[] black_caps) {
        if (black_caps[0] == 1) check[1] = 1;
        else check[1] = 2;

        if (black_caps[N - 1] == 1) check[N - 2] = 1;
        else check[N - 2] = 2;
    }
}
