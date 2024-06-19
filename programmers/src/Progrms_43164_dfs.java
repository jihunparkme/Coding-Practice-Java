import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Progrms_43164_dfs {

    private static List<String> result = new ArrayList<>();
    private static boolean[] used;

    public static String[] solution(String[][] tickets) {
        used = new boolean[tickets.length];

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(result);
        return result.get(0).split(" ");
    }

    private static void dfs(final int dept, final String now, final String path, final String[][] tickets) {
        if (dept == tickets.length) {
            result.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (used[i]) continue; // 이미 사용한 티켓
            if (!now.equals(tickets[i][0])) continue; // 출발지가 다른 경우
            used[i] = true;
            dfs(dept + 1, tickets[i][1], path + " " + tickets[i][1], tickets);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        String[] result = solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}});
        Arrays.stream(result).forEach(System.out::println);

        System.out.printf("===\n");

        result = solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}});
        Arrays.stream(result).forEach(System.out::println);
    }
}

// 구현 / 문자열 조작(문자열 나누기, 대소문자 등) / 완전탐색 / 스택, 큐, 집합 자료구조 / BFS, DFS