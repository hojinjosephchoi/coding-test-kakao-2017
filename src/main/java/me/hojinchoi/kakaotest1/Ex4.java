package me.hojinchoi.kakaotest1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Crew {
    private LocalTime time;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
}

public class Ex4 {

    public static void main(String[] args) {
        
//        int n = 1;
//        int t = 1;
//        int m = 5;
//        
//        String[] timetables = {"08:00", "08:01", "08:02", "08:03"};
        
//        int n = 2;
//        int t = 10;
//        int m = 2;
//        
//        String[] timetables = {"09:10", "09:09", "08:00"};
        
        
//        int n = 2;
//        int t = 1;
//        int m = 2;
//        
//        String[] timetables = {"09:00", "09:00", "09:00", "09:00"};
        
//        int n = 1;
//        int t = 1;
//        int m = 5;
//        
//        String[] timetables = {"00:01", "00:01", "00:01", "00:01", "00:01"};
        
//        int n = 1;
//        int t = 1;
//        int m = 1;
//        
//        String[] timetables = {"23:59"};
        
        int n = 10;
        int t = 60;
        int m = 45;
        
        String[] timetables = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
        
        
        List<Crew> timetableList = new ArrayList<>();
        for(int inx = 0; inx < timetables.length; inx++) {
            Crew crew = new Crew();
            crew.setTime(LocalTime.of(Integer.parseInt(timetables[inx].split(":")[0]),
                    Integer.parseInt(timetables[inx].split(":")[1])));
            timetableList.add(crew);
        }
        timetableList.sort(Comparator.comparing(Crew::getTime));
        
        
        String answer = "";
        LocalTime currentBusTime = LocalTime.of(9, 0);
        for(int inx = 0; inx < n; inx++) {
            if (inx == (n - 1)) {
                //last bus
                
                if (m > timetableList.size()) {
                    answer = currentBusTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                } else if (m <= timetableList.size()) {
                    
                    int currentBusCrew = 0;
                    for(int jnx = 0; jnx < timetableList.size(); jnx++) {
                        if (currentBusCrew < m && (timetableList.get(jnx).getTime().isBefore(currentBusTime)
                                || timetableList.get(jnx).getTime().equals(currentBusTime))) {
                            currentBusCrew++;
                        }
                    }
                    
                    if(currentBusCrew < m) {
                        answer = currentBusTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                    } else {
                        answer = timetableList.get(currentBusCrew - 1).getTime().minusMinutes(1).format(DateTimeFormatter.ofPattern("HH:mm"));
                    }
                    
                    
                }
                
                
            } else {
                
                int currentBusCrew = 0;
                for(int jnx = 0; jnx < timetableList.size(); jnx++) {
                    if (currentBusCrew < m && (timetableList.get(jnx).getTime().isBefore(currentBusTime)
                            || timetableList.get(jnx).getTime().equals(currentBusTime))) {
                        currentBusCrew++;
                        timetableList.remove(jnx);
                        jnx--;
                    }
                }
                
                currentBusTime = currentBusTime.plusMinutes(t);
            }
        }

        
        System.out.println(answer);
    }

}
