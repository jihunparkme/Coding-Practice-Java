package test20220319;

import java.util.*;
import java.util.stream.Collectors;

public class Problem04 {
    public static void main(String[] args) {
        String[] solution = solution(new String[]{
                        "/",
                        "/hello",
                        "/hello/tmp",
                        "/root",
                        "/root/abcd",
                        "/root/abcd/etc",
                        "/root/abcd/hello"},
                new String[]{
                        "mkdir /root/tmp",
                        "cp /hello /root/tmp",
                        "rm /hello"
                });
//        String[] solution = solution(new String[]{
//                        "/",
//                        },
//                new String[]{
//                        "mkdir /a",
//                        "mkdir /a/b",
//                        "mkdir /a/b/c",
//                        "cp /a/b /",
//                        "rm /a/b/c"
//                });
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }

    public static String[] solution(String[] directory, String[] command) {
        
        List<String> directoryList = Arrays.stream(directory).collect(Collectors.toList());

        for (int i = 0; i < command.length; i++) {
            String[] comm = command[i].split(" ");
            if ("mkdir".equals(comm[0])) {
                mkdir(directoryList, comm[1]);
            } else if ("cp".equals(comm[0])) {
                cp(directoryList, comm);
            } else {
                rm(directoryList, comm);
            }
        }

        Collections.sort(directoryList);

        return directoryList.toArray(new String[directoryList.size()]);
    }

    private static void rm(List<String> directoryList, String[] comm) {
        List<String> targetList = getDirectoryList(directoryList, comm[1]);
        for (String tg : targetList) {
            directoryList.remove(tg);
        }
    }

    private static void cp(List<String> directoryList, String[] comm) {
        String[] targetFullPath = comm[1].split("/");
        List<String> targetList;
        if (targetFullPath.length > 2) {
            comm[1] = targetFullPath[targetFullPath.length - 1];
            targetList = getSubDirectoryList(directoryList, comm);
        } else {
            targetList = getDirectoryList(directoryList, comm[1]);
        }

        for (String tg : targetList) {
            int index = tg.indexOf(comm[1]);
            String substring = tg.substring(index);
            mkdir(directoryList, comm[2] + substring);
        }
    }

    private static void mkdir(List<String> directoryList, String comm) {
        directoryList.add(comm);
    }

    private static List<String> getDirectoryList(List<String> directoryList, String comm) {
        return directoryList.stream()
                .filter(s -> s.startsWith(comm)).collect(Collectors.toList());
    }

    private static List<String> getSubDirectoryList(List<String> directoryList, String[] comm) {
        return directoryList.stream()
                .filter(s -> s.contains(comm[1])).collect(Collectors.toList());
    }
}
