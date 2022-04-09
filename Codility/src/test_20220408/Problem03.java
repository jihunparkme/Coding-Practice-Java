package test_20220408;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Problem03 {

    public static void main(String[] args) {
        Map<String, UserStats>[] mapArr = new HashMap[10];

        Map<String, UserStats> map = new HashMap<>();
        map.put("123", new UserStats(Optional.of(3L)));
        map.put("1", new UserStats(Optional.of(25L)));

        Map<String, UserStats> map2 = new HashMap<>();
        map.put("121233", new UserStats(Optional.of(142L)));
        map.put("11231", new UserStats(Optional.of(1243L)));

        mapArr[0] = map;
        mapArr[1] = map2;

        Map<Long, Long> count = count(map);
        for (Long key : count.keySet()) {
            System.out.println(key + " " + count.get(key));
        }
    }

    /**
     * 사용자 방문 수를 나타내는 집계
     * Map<Long, Long> id, 방문 횟수
     */
    static Map<Long, Long> count(Map<String, UserStats>... visits) {

        Map<Long, Long> result = new LinkedHashMap<>();

        if (visits == null) return result;

        for (Map<String, UserStats> visit : visits) {

            if (visit == null) continue;

            for (String key : visit.keySet()) {

                if (checkValid(visit, key)) {
                    Long existCnt = result.getOrDefault(Long.parseLong(key), 0L);
                    result.put(Long.parseLong(key), existCnt + visit.get(key).getVisitCount().get());
                }
            }
        }

        return result;
    }

    private static boolean checkValid(Map<String, UserStats> visit, String key) {

        try {
            long id = Long.parseLong(key);
        } catch (NumberFormatException e) {
            return false;
        }

        UserStats userStats = visit.get(key);
        if (userStats == null) return false;

        Optional<Long> visitCount = userStats.getVisitCount();
        if (!visitCount.isPresent()) return false;

        return true;
    }
}
