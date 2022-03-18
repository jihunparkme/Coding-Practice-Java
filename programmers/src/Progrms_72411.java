    import java.util.*;

public class Progrms_72411 {
    public static void main(String[] args) {
        String[] solution = solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }

    }

    /**
     * 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성
     * 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성
     * 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함
     */
    private static Map<String, Integer> menuMap;
    private static String[] menu;
    private static int max[];
    public static String[] solution(String[] orders, int[] course) {
        menuMap = new HashMap<>();
        menu = new String[11];
        max = new int[11];
        for (int i = 0; i < course.length; i++) {
            for (int j = 0; j < orders.length; j++) {
                char[] chars = orders[j].toCharArray();
                Arrays.sort(chars);
                find(0, 0, chars, course[i]);
            }
        }

        List<String> answer = new ArrayList<>();
        for (String mn : menuMap.keySet()) {
            if (menuMap.get(mn) >= 2 && menuMap.get(mn) == max[mn.length()]) answer.add(mn);
        }

        Collections.sort(answer);

        return answer.toArray(new String[answer.size()]);
    }

    private static void find(int cnt, int idx, char[] orders, int n) {

        if (cnt == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(menu[i]);
            }
            String newMenu = sb.toString();
            menuMap.put(newMenu.toString(), menuMap.getOrDefault(newMenu.toString(), 0) + 1);
            max[n] = max[n] < menuMap.get(newMenu) ? menuMap.get(newMenu) : max[n];
            return;
        }

        for (int i = idx; i < orders.length; i++) {
            menu[cnt] = String.valueOf(orders[i]);
            find(cnt + 1, i + 1, orders, n);
        }
    }
}
