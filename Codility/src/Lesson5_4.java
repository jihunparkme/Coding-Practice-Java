public class Lesson5_4 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-4, -2, 2, 5, -1, -5, 8}));
    }

    /**
     * 최소 평균으로 슬라이스의 시작 위치를 반환
     * 평균을 가진 슬라이스가 두 개 이상 있는 경우 해당 슬라이스의 가장 작은 시작 위치를 반환
     *
     * https://app.codility.com/demo/results/training5SMPYY-8XK/
     * 참고: https://nukeguys.tistory.com/175
     */
    public static int solution(int[] A) {

        double minAvg = (A[0] + A[1]) / 2;
        int startIdx = 0;

        for (int i = 2; i < A.length; i++) {
            double g2 = (A[i-1] + A[i]) / 2.0;
            if (minAvg > g2) {
                minAvg = g2;
                startIdx = i - 1;
            }

            double g3 = (A[i - 2] + A[i - 1] + A[i]) / 3.0;
            if (minAvg > g3) {
                minAvg = g3;
                startIdx = i - 2;
            }
        }

        return startIdx;
    }
}
