public class Lessaion1_1 {
    public static void main(String[] args) {
        System.out.println("Answer : " + solution(529));
    }

    /**
     * 가장 긴 이진 간견의 길이
     * 이진 간격에 포함되지 않을 경우 0 반환
     *
     * 1 : 2
     * 000001 : 32
     * 0001 : 16
     * 1000010001 : 529
     */
    private static int solution(int N) {

        int num = N;
        int result = 0;
        int cnt = -1;

        while(num > 0) {
            int mod = num % 2;
            if (start(cnt, mod)) {
                cnt = 0;
            } else if (end(cnt, mod)) {
                result = result < cnt ? cnt : result;
                cnt = 0;
            } else if (count(cnt, mod)){
                cnt++;
            }
            num /= 2;
        }

        return result;
    }

    private static boolean end(int cnt, int mod) {
        return mod == 1 && cnt != -1;
    }

    private static boolean start(int cnt, int mod) {
        return mod == 1 && cnt == -1;
    }

    private static boolean count(int cnt, int mod) {
        return mod == 0 && cnt != -1;
    }
}
