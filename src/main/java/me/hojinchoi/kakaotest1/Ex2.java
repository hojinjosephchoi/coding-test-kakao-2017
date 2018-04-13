package me.hojinchoi.kakaotest1;

import java.util.ArrayList;
import java.util.List;

class Score {
    String point = "";
    int calculatedPoint = 0;
}

public class Ex2 {
    public static void main(String[] args) {

        String input = "1T2D3D#";

        List<Score> totalScores = new ArrayList<>();
        
        int currentScoreCnt = -1;
        boolean isLastStrNumber = false;

        for (int inx = 0; inx < input.length(); inx++) {
            String str = input.charAt(inx) + "";

            if (str.matches("[0-9]")) {
                
                if(!isLastStrNumber) {
                    currentScoreCnt++;
                    totalScores.add(new Score());
                }
                totalScores.get(currentScoreCnt).point += str;
                
                isLastStrNumber = true;

            } else if (str.matches("[SDT]")) {

                if ("S".equals(str)) {
                    totalScores.get(currentScoreCnt).calculatedPoint = (int) Math
                            .pow(Integer.parseInt(totalScores.get(currentScoreCnt).point), 1);
                } else if ("D".equals(str)) {
                    totalScores.get(currentScoreCnt).calculatedPoint = (int) Math
                            .pow(Integer.parseInt(totalScores.get(currentScoreCnt).point), 2);
                } else if ("T".equals(str)) {
                    totalScores.get(currentScoreCnt).calculatedPoint = (int) Math
                            .pow(Integer.parseInt(totalScores.get(currentScoreCnt).point), 3);
                }

                isLastStrNumber = false;

            } else if (str.matches("[#*]")) {
                
                if ("#".equals(str)) {
                    totalScores.get(currentScoreCnt).calculatedPoint *= -1;
                    
                } else if ("*".equals(str)) {
                    totalScores.get(currentScoreCnt).calculatedPoint *= 2;
                    if(currentScoreCnt > 0) {
                        totalScores.get(currentScoreCnt - 1).calculatedPoint *= 2;
                    }
                }
                
                isLastStrNumber = false;
            }
            
        }
        
        int answer = 0;
        for(Score score : totalScores) {
            answer += score.calculatedPoint;
        }
        
        System.out.println(answer);
    }
}
