package me.hojinchoi.kakaotest1;


import java.io.*;
import java.util.*;

public class Ex5 {
    
    static class JakadData {
        private int intersectionCount;
        private int unionCount;
    }
    
    static Map<String, Integer> generateAggregation(String source) {
        
        Map<String, Integer> aggregation = new HashMap<>();
        
        for(int inx = 0; inx < source.length() - 1; inx++) {
            String element = source.substring(inx, inx + 2);
            if(element.matches("[A-Za-z]*$")) {
                if(aggregation.containsKey(element.toLowerCase())) {
                    aggregation.put(element.toLowerCase(), aggregation.get(element.toLowerCase()) + 1);
                } else {
                    aggregation.put(element.toLowerCase(), 1);
                }
            }
        }
        
        return aggregation;
    }

    static int getJakad(String str1, String str2) {
        Map<String, Integer> str1Aggregation = generateAggregation(str1);
        Map<String, Integer> str2Aggregation = generateAggregation(str2);
        
        JakadData jakadData = new JakadData();
        
        str1Aggregation.forEach((element, count) -> {
            if(str2Aggregation.containsKey(element)) {
                jakadData.intersectionCount += Math.min(count, str2Aggregation.get(element));
            }
        });
        
        
        Map<String, Integer> mergedAggregation = new HashMap<>();
        str1Aggregation.forEach((element, count) -> {
            mergedAggregation.put(element, count);
        });
        
        str2Aggregation.forEach((element, count) -> {
            if(mergedAggregation.containsKey(element)) {
                mergedAggregation.put(element, Math.max(mergedAggregation.get(element), count));
            } else {
                mergedAggregation.put(element, count);
            }
        });
        
        mergedAggregation.forEach((element, count) -> {
            jakadData.unionCount += count;
        });
        
        double jakadRate = 1.0;
        if(jakadData.intersectionCount != 0 && jakadData.unionCount != 0) {
            jakadRate = ((double)jakadData.intersectionCount / (double)jakadData.unionCount);
        }
        
        
        return (int) Math.floor(jakadRate * 65536);
    }
    
    

//    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println(getJakad("handshake", "shake hands"));
    }

}

