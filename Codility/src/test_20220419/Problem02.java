package test_20220419;

public class Problem02 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,5,3,4,3,4,1,2,3,4,6,2}));
    }

    /**
     * 각 테스트 그룹을 요약
     *
     * 그룹이름, 그룹 테스트 수, 상 OK 테스트 수, 통과된 테스트 총
     * total value 기준 정렬, 동정일 겸우 이름순
     *
     *
     -- all_test_case
     -- SELECT group_name, count(*) as all_test_case
     -- from test_cases
     -- group by group_name

     -- passed_test_cases
     -- SELECT group_name, count(*) as passed_test_cases
     -- from test_cases
     -- where status = 'OK'
     -- group by group_name

     -- total_value
     -- select t1.name, t1.test_value * coalesce(t2.cnt, 0) as total_value
     -- from test_groups t1
     -- left join (
     --     SELECT group_name, count(*) as cnt
     --     from test_cases
     --     where status = 'OK'
     --     group by group_name
     -- ) t2 on t1.name = t2.group_name


     -- select *
     -- from test_groups t1
     -- left join (
     --     SELECT group_name, count(*) as all_test_case
     --     from test_cases
     --     group by group_name
     -- ) t2 on t1.name = t2.group_name
     -- left join











     select t1.name as name,
     coalesce(t3.all_test_case, 0) as all_test_case,
     coalesce(t2.passed_test_cases, 0) as passed_test_cases,
     t1.test_value * coalesce(t2.passed_test_cases, 0) as total_value
     from test_groups t1
     left join (
     SELECT group_name, count(*) as passed_test_cases
     from test_cases
     where status = 'OK'
     group by group_name
     ) t2 on t1.name = t2.group_name
     left join (
     SELECT group_name, count(*) as all_test_case
     from test_cases
     group by group_name
     ) t3 on t1.name = t3.group_name
     order by total_value desc, name
     */
    public static int solution(int[] A) {
        return 0;
    }
}
