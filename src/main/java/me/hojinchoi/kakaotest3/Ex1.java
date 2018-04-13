package me.hojinchoi.kakaotest3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * http://tech.kakao.com/2017/11/14/kakao-blind-recruitment-round-3/
 * 
 * @author hojinchoi
 *
 */

public class Ex1 {

    static class Player {
        private List<String> resultList = new ArrayList<>();
    }

    static String solution(int n, int t, int m, int p) {
        
        List<Player> players = new ArrayList<>();
        for(int inx = 0; inx < m; inx++) {
            players.add(new Player());
        }
        
        int currentNumber = 0;
        int currentPlayerInx = 0;
        while(players.get(p - 1).resultList.size() < t) {
            
            String currentNumberRadix = Integer.toString(currentNumber, n).toUpperCase();
            String[] currentSplitedNumberRadix = currentNumberRadix.split("");
            for(int inx = 0; inx < currentSplitedNumberRadix.length; inx++) {
                players.get(currentPlayerInx).resultList.add(currentSplitedNumberRadix[inx]);
                
                currentPlayerInx++;
                if(currentPlayerInx >= players.size()) {
                    currentPlayerInx = 0;
                }
            }
            
            currentNumber++;
        }
        
        return players.get(p - 1).resultList.subList(0, t).stream().collect(Collectors.joining());
    }

    // private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


//        System.out.println(solution(2, 4, 2, 1));
//        System.out.println(solution(16, 16, 2, 1));
        System.out.println(solution(16, 16, 2, 2));
        

    }

}
