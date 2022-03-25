import java.util.HashSet;
import java.util.Set;

public class Lesson4_1 {

    public static void main(String[] args) {
        System.out.println("Answer : " + solution(5, new int[]{1,3,1,4,2,3,5,4}));
    }

    /**
     * 개구리는 처음에 강의 한쪽 강둑(위치 0)에 있으며 반대쪽 강둑(위치 X+1)으로 이동하려고 한다.
     * 떨어지는 잎을 나타내는 N개의 정수로 구성된 배열 A
     * A[K]는 초 단위로 측정된 시간 K에서 한 잎이 떨어지는 위치
     * 개구리가 강의 반대편으로 점프할 수 있는 가장 빠른 시간 찾기
     * 개구리가 강의 반대편으로 점프할 수 없는 경우 함수는 -1을 반환
     *
     * N and X are integers within the range [1..100,000];
     * each element of array A is an integer within the range [1..X].
     *
     * https://app.codility.com/demo/results/trainingV3B9HW-ZM4/
     */
    public static int solution(int X, int[] A) {
        int result = -1;
        Set<Integer> leaves = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            leaves.add(A[i]);
            if (leaves.size() == X) {
                result = i;
                break;
            }
        }

        return result;
    }
}
