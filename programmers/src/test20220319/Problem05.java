package test20220319;

public class Problem05 {
    public static void main(String[] args) {
        int solution = solution("[]([[]){}");
        System.out.println(solution);
    }

    public static int solution(String s) {
        int answer = 0;

        String remain = s;
        int before = 0;
        while(before != s.length()) {
            before = remain.length();
            remain = remain.replace("()", "").replace("[]", "").replace("{}", "");
        }

        int idx = remain.length() % 2;
        char wrong = remain.charAt(idx);
        if (wrong == '(' || wrong == '[' || wrong == '{' ) {

        } else {

        }

        return answer;
    }
}
