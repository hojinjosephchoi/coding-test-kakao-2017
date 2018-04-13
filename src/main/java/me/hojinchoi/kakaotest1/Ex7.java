package me.hojinchoi.kakaotest1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Ex7 {

    static class LogData {
        private Date startDateTime;
        private Date endDateTime;
    }

    static int solution(String[] logs) {
        
        

        SimpleDateFormat formatter = new SimpleDateFormat(("yyyy-MM-dd HH:mm:ss.SSS"));

        List<LogData> logList = new ArrayList<>();
        Arrays.stream(logs).forEach((log) -> {
            String[] separatedLog = log.split(" ");

            try {
                Date endDateTime = formatter.parse(separatedLog[0] + " " + separatedLog[1]);

                float operatedSecTime = Float.parseFloat(separatedLog[2].replaceAll("s", ""));
                operatedSecTime -= 0.001;
                long opeartedMilliTime = (long) (operatedSecTime * 1000L);
                Date startDateTime = new Date(endDateTime.getTime() - opeartedMilliTime);

                LogData logData = new LogData();
                logData.startDateTime = startDateTime;
                logData.endDateTime = endDateTime;
                logList.add(logData);

                // System.out.println(log);
                // System.out.println(formatter.format(startDateTime));
                // System.out.println(formatter.format(endDateTime));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        int maxOperationCnt = 0;
        long logsStartMilliTime = logList.get(0).startDateTime.getTime();
        long logsEndMilliTime = logList.get(logList.size() - 1).endDateTime.getTime();

        for (long inx = logsStartMilliTime; inx < logsEndMilliTime; inx++) {
            Date periodStartDateTime = new Date(inx);
            Date periodEndDateTime = new Date(inx + 999);
            int tmpOperationCnt = 0;
            for (int jnx = 0; jnx < logList.size(); jnx++) {
                if (logList.get(jnx).startDateTime.getTime() <= periodEndDateTime.getTime()
                        && logList.get(jnx).endDateTime.getTime() >= periodStartDateTime.getTime()) {
                    tmpOperationCnt++;
                }
            }

            if (tmpOperationCnt > maxOperationCnt) {
                maxOperationCnt = tmpOperationCnt;
            }
        }
        
        

        return maxOperationCnt;
    }

    // private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        // System.out.println(solution(new String[]{"2016-09-15 01:00:04.001 2.0s",
        // "2016-09-15 01:00:07.000 2s"}));
        // System.out.println(solution(new String[]{"2016-09-15 01:00:04.002 2.0s",
        // "2016-09-15 01:00:07.000 2s"}));
        System.out.println(solution(new String[] { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" }));
        

    }

}
