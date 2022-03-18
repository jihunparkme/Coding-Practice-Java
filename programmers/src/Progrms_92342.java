public class Progrms_92342 {
    public static void main(String[] args) {
        int[] solution = solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3});
        for (int i = 0; i < solution.length; i++) {
            System.out.printf(String.valueOf(solution[i]));
        }
    }

    /**
     * 라이언이 n발의 화살을 어떤 과녁 점수에 맞혀야 가장 큰 점수 차이로 승리할 수 있을지.
     *
     * 어피치가 화살 n발을 다 쏜 후에 라이언이 화살 n발을 쏩니다.
     *      만약, k(k는 1~10사이의 자연수)점을 어피치가 a발을 맞혔고 라이언이 b발을 맞혔을 경우 더 많은 화살을 k점에 맞힌 선수가 k 점을 가져갑니다.
     *      단, a = b일 경우는 어피치가 k점을 가져갑니다. k점을 여러 발 맞혀도 k점 보다 많은 점수를 가져가는 게 아니고 k점만 가져가는 것을 유의하세요. 또한 a = b = 0 인 경우,
     *      즉, 라이언과 어피치 모두 k점에 단 하나의 화살도 맞히지 못한 경우는 어느 누구도 k점을 가져가지 않습니다.
     *              예를 들어, 어피치가 10점을 2발 맞혔고 라이언도 10점을 2발 맞혔을 경우 어피치가 10점을 가져갑니다.
     *              다른 예로, 어피치가 10점을 0발 맞혔고 라이언이 10점을 2발 맞혔을 경우 라이언이 10점을 가져갑니다.
     *
     *      모든 과녁 점수에 대하여 각 선수의 최종 점수를 계산합니다.
     *
     *  최종 점수가 더 높은 선수를 우승자로 결정합니다. 단, 최종 점수가 같을 경우 어피치를 우승자로 결정합니다.
     *
     *  10점부터 0점까지 순서대로 정수 배열에 담아 return
     *  라이언이 우승할 수 없는 경우(무조건 지거나 비기는 경우)는 [-1]
     *
     *  라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return 해주세요.
     * 가장 낮은 점수를 맞힌 개수가 같을 경우 계속해서 그다음으로 낮은 점수를 더 많이 맞힌 경우를 return 해주세요.
     */
    public static int[] answer = {-1};
    public static int[] ryan;
    public static int max = Integer.MIN_VALUE;
    public static int[] solution(int n, int[] info) {
        ryan = new int[11];

        dfs(0, 0, n, info);

        return answer;
    }

    private static void dfs(int cnt, int shot, int n, int[] info) {
        if (shot > n) return;
        // 점수 계산
        if(shot == n || cnt == 11) {
            ryan[cnt - 1] += n - shot;
            setResult(info);
            ryan[cnt - 1] -= n - shot;
            return;
        }

        // 점수 얻기 (라이언이 더 많은 화살)
        ryan[cnt] += info[cnt] + 1;
        dfs(cnt + 1, shot + info[cnt] + 1, n, info);
        ryan[cnt] -= info[cnt] + 1;

        // 점수 안 얻기 (어피치가 더 많은 화살)
        dfs(cnt + 1, shot, n, info);
    }

    private static void setResult(int[] info) {
        int s1 = getRyanScore();
        int s2 = getPeachScore(info);
        if (s2 >= s1) return;
        int result = s1 - s2;

        if (max == result) {
            if(isNewCaseMoreLowScore()) {
                setNewResult();
            }
        }

        if (max < result) {
            max = result;
            setNewResult();
        }

    }

    private static void setNewResult() {
        answer = new int[11];
        for (int i = 0; i <= 10; i++) {
            answer[i] = ryan[i];
        }
    }

    private static int getRyanScore() {
        int score = 0;
        for (int i = 0; i <= 10; i++) {
            if (ryan[i] != 0) score += 10 - i;
        }
        return score;
    }

    private static int getPeachScore(int[] info) {
        int score = 0;
        for (int i = 0; i <= 10; i++) {
            if (ryan[i] == 0 && info[i] != 0) score += 10 - i;
        }
        return score;
    }

    private static boolean isNewCaseMoreLowScore() {
        for (int i = 10; i >= 0; i--) {
            if (ryan[i] > answer[i]) return true;
            else if(ryan[i] < answer[i]) return false;
        }

        return false;
    }
}
