import java.util.HashMap;
import java.util.Map;

class Lesson2_2 {
    public static void main(String[] args) {
        int solution = solution(new int[]{9, 3, 9, 3, 9, 7, 9});
        System.out.println(solution);
    }

    /**
     * N개의 정수로 구성된 비어 있지 않은 배열 A
     * 짝을 이루지 않은 요소의 값을 반환
     *
     * 반례
     * [1, 2, 3, 4, 5, 4, 3, 2, 1, 5, 5]
     *
     * https://app.codility.com/demo/results/trainingQTDTMZ-CU3/
     */
    public static int solution(int[] A) {
        int size = A.length;
        Map<Integer, Integer> couple = new HashMap<>();
        for (int i = 0; i < size; i++) {
            if (couple.containsKey(A[i])) couple.put(A[i], couple.get(A[i]) + 1);
            else couple.put(A[i], 1);
        }

        for (Integer key : couple.keySet()) {
            if (hasNotCouple(couple, key)) return key;
        }

        return 0;
    }

    private static boolean hasNotCouple(Map<Integer, Integer> couple, Integer key) {
        return couple.get(key) % 2 == 1;
    }
}



