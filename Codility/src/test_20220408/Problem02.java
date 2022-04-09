package test_20220408;

import java.util.stream.Stream;

public class Problem02 {

    public static void main(String[] args) {
//        System.out.println(solution("We test coders. Give us a try?"));
//        System.out.println(solution("Forget  CVs..Save time . x x"));
//        System.out.println(solution("We test coders. Give us a try? Give us a try test!"));
//        System.out.println(solution("Forget           CVs.. ..   .. Save time . x x"));
        System.out.println(solution("?and or!?!...and?!d ?!?"));
    }

    /**
     * 문장의 최대 단어 수
     * . ? !
     */
    public static int solution(String S) {

        int result = 0;
        String[] split = S.split("\\.|\\?|\\!");
        for (int i = 0; i < split.length; i++) {
            int cnt = (int) Stream.of(split[i].trim().split(" ")).filter(s -> !s.equals("")).count();
            result = result < cnt ? cnt : result;
        }

        return result;
    }
}
