package me.hojinchoi.kakaotest3;

import java.io.*;
import java.util.*;

public class Ex2 {


    static int findIndex(List<String> langDict, String key) {
        
        int result = -1;
        for(int inx = 0; inx < langDict.size(); inx++) {
            if(langDict.get(inx).equals(key)) {
                result = inx;
                break;
            }
        }
        
        if (result == -1) {
            langDict.add(key);
        }
        return result;
    }
    
    static int[] solution(String msg) {
        List<String> langDict = new ArrayList<>();
        for(char character = 'A'; character <= 'Z'; character++) {
            langDict.add(Character.toString(character));
        }
        
        List<Integer> indexList = new ArrayList<>();
        
        while(0 < msg.length()) {
            
            int currentStrSize = 1;
            String currentStr = msg.substring(0, currentStrSize);
            int currentInx = findIndex(langDict, currentStr);
            while(currentInx != -1) {
                int lastInx = currentInx;
                
                currentInx = -1;
                currentStrSize++;
                
                if (msg.length() >= currentStrSize) {
                    currentStr = msg.substring(0, currentStrSize);
                    currentInx = findIndex(langDict, currentStr);
                }
                
                if(currentInx == -1) {
                    indexList.add(lastInx);
                    msg = msg.substring(currentStrSize - 1);
                }
            }
            
        }
        return indexList.stream().mapToInt(i -> i + 1).toArray();
    }

    // private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        int[] indexArr = solution("KAKAO");
//        int[] indexArr = solution("TOBEORNOTTOBEORTOBEORNOT");
//        int[] indexArr = solution("ABABABABABABABAB");

        Arrays.stream(indexArr).forEach(System.out::println);
        

    }

}
