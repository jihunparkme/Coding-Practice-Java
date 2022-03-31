public class Progrms_77484_2 {
    public static void main(String[] args) {
        int[] solution = solution(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19});
//        int[] solution = solution(new int[]{0, 0, 0, 0, 0, 0}, new int[]{38, 19, 20, 40, 15, 25});
//        int[] solution = solution(new int[]{45, 4, 35, 20, 3, 9}, new int[]{20, 9, 3, 45, 4, 35});
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }

    /**
     * 당첨이 가능했던 최고 순위와 최저 순위를 알아보고 싶어 졌습니다.
     * 당첨 가능한 최고 순위와 최저 순위를 차례대로 배열에 담아서 return
     *
     *
     * lottos	win_nums	result
     * [44, 1, 0, 0, 31, 25]	[31, 10, 45, 1, 6, 19]	[3, 5]
     * [0, 0, 0, 0, 0, 0]	[38, 19, 20, 40, 15, 25]	[1, 6]
     * [45, 4, 35, 20, 3, 9]	[20, 9, 3, 45, 4, 35]	[1, 1]
     */
    public static int[] solution(int[] lottos, int[] win_nums) {
        boolean[] winner = new boolean[46];
        for (int i = 0; i < win_nums.length; i++) {
            winner[win_nums[i]] = true;
        }

        int zero = 0;
        int cnt = 0;
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) zero++;
            if (winner[lottos[i]]) cnt++;
        }

        int max = cnt + zero;
        int min = cnt;

        return new int[]{getRank(max), getRank(min)};
    }

    private static int getRank(int max) {
        switch (max) {
            case 6: return 1;
            case 5: return 2;
            case 4: return 3;
            case 3: return 4;
            case 2: return 5;
            default:return 6;
        }
    }
}
