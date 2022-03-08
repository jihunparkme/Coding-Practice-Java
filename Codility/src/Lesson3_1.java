class Lesson3_1 {
    public static void main(String[] args) {
        System.out.println(solution(10, 85, 30));
    }

    /**
     * 개구리는 현재 위치 X에 있고,
     * Y보다 크거나 같은 위치에 도달하려고 한다. (항상 고정된 거리 D만큼 점프)
     * 목표물에 도달하기 위해 수행해야 하는 최소 점프 횟수
     *
     * 무조건 결과에 +1 을 해주면 안 되고, 정확하게 나누어 떨어지는 경우를 생각해야 한다.
     * -> 그냥 올림으로 해결할 수 있다.
     *
     * 1차 : https://app.codility.com/demo/results/trainingRGR68Q-33B/
     * 2차 : https://app.codility.com/demo/results/training76RKN7-9PJ/
     */
    public static int solution(int X, int Y, int D) {
        double cntJump = (double) (Y - X) / D;
        return (int) Math.ceil(cntJump);
    }
}
