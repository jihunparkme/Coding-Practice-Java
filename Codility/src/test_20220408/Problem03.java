package test_20220408;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Problem03 {

    public static void main(String[] args) {
        Map<String, UserStats> map = new HashMap<>();
        map.put("123", new UserStats(Optional.of(3L)));
        Map<Long, Long> count = count(map);
        for (Long key : count.keySet()) {
            System.out.println(key + " " + count.get(key));
        }
    }

    /**
     * 사용자 방문 수를 나타내는 집계
     * count 메스드가 이미 있음
     * 반환 Map<Long, Long> id, 방문 횟
     *
     *
     * String key 를 Long 으로 파싱. 불가능할 경우 패스
     * 몇몇 key 의 UserState 가 null 일 경우 패스
     * UserState는 visitCount 필드를 갖는데 Optional<Long> 타입. getter 있음. optional empty 면 패스
     *
     */
    static Map<Long, Long> count(Map<String, UserStats>... visits) {

        Map<Long, Long> result = new HashMap<>();

        if (visits == null) return result;

        for (Map<String, UserStats> visit : visits) {

            if (visit == null) return result;

            for (String key : visit.keySet()) {

                long id;
                try {
                    id = Long.parseLong(key);
                } catch (Exception e) {
                    continue;
                }

                UserStats userStats = visit.get(key);
                if (userStats == null) continue;

                Optional<Long> visitCount = userStats.getVisitCount();
                if (!visitCount.isPresent()) continue;

                result.put(id, visitCount.get());
            }
        }

        return result;
    }
}
