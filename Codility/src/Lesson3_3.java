class Lesson3_3 {
    public static void main(String[] args) {
        System.out.println("Answer : " + solution(new int[]{3,1,2,4,3}));
    }

    /**
     * P로 나눌 수 있는 부분합의 차이 최소
     * N : [ 2 .. 100,000 ]
     * 배열 A의 각 요소 [ −1,000 .. 1,000 ]
     * |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
     * 0 < P < N
     *
     * 시간복잡도 생각하자 친구야!! N * N 이면 10,000,000,000 이다 !!!
     * 
     * https://app.codility.com/demo/results/trainingNGPEUJ-TSJ/
     */
    public static int solution(int[] A) {

        int N = A.length;
        int total = 0;
        total = getTotal(A);

        int tape1 = A[0];
        int tape2 = total - tape1;
        int min = Math.abs(tape1 - tape2);

        for (int p = 2; p < N; p++) {
            tape1 = tape1 + A[p-1];
            tape2 = tape2 - A[p-1];

            int diff = Math.abs(tape1 - tape2);
            min = min < diff ? min : diff;
        }

        return min;
    }

    private static int getTotal(int[] A) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result += A[i];
        }
        return result;
    }
}



