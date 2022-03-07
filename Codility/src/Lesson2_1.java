import java.util.Arrays;

class Lesson2_1 {
    public static void main(String[] args) {
        int[] solution = solution(new int[]{3, 8, 9, 7, 6}, 3);
        for (int i : solution) {
            System.out.println(i);
        }
    }

    /**
     * 배열의 회전은 각 요소가 한 인덱스만큼 오른쪽으로 이동하고 배열의 마지막 요소가 첫 번째 위치로 이동하는 것을 의미
     *
     * N, K 의 범위를 잘 확인해서 리스트 크기가 1일 경우 예외 처리 필요
     */
    public static int[] solution(int[] A, int K) {

        int size = A.length;
        if (size <= 1) return A;

        for (int i = 0; i < K; i++) {
            A = rotation(A, size);
        }

        return A;
    }

    private static int[] rotation(int[] a, int size) {
        int last = a[size - 1];
        for (int i = size - 1; i > 0; i--) {
            a[i] = a[i-1];
        }
        a[0] = last;

        return a;
    }
}



