public class Progrms_77484 {
    public static void main(String[] args) {
//        int[] solution = solution(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19});
        int[] solution = solution(new int[]{0, 0, 0, 0, 0, 0}, new int[]{38, 19, 20, 40, 15, 25});
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
    private static final boolean[] winner = new boolean[46];
    private static boolean[] selected = new boolean[46];
    private static int cnt = 0;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        //1. 당첨 번호 세팅
        for (int i = 0; i < win_nums.length; i++) {
            winner[win_nums[i]] = true;
        }

        //2. 민우가 현재까지 맞춘 개수
        int zero = 0;
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) zero++;
            if (winner[lottos[i]]) cnt++;
            selected[lottos[i]] = true;
        }

        //3. 알아볼 수 없는 숫자 채워보기
        if (zero == 6) {
            max = 6;
            min = 0;
        } else {
            find(1, zero, cnt);
        }

        //4.가장 많이 맞은 개수랑 적은 개수 등수 환산
        answer[0] = getRank(max);
        answer[1] = getRank(min);

        return answer;
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

    private static void find(int idx, int zero, int check) {

        if (zero == 0) {
            max = max < check ? check : max;
            min = min > check ? check : min;
            return;
        }

        for (int i = idx; i < 45; i++) {
            //이미 선택된 숫자
            if (selected[i]) continue;

            selected[i] = true;
            if (winner[i]) check++;
            find(idx + 1, zero - 1, check);

            selected[i] = false;
            if (winner[i]) check--;
        }

    }
}
