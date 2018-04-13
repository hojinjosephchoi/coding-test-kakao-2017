package me.hojinchoi.kakaotest3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Ex5 {

    static class Word {
        private char[] splitedData;
    }

    static int solution(String[] words) {
        List<Word> wordList = new ArrayList<>();
        for (int inx = 0; inx < words.length; inx++) {
            Word word = new Word();
            word.splitedData = words[inx].toCharArray();
            wordList.add(word);
        }

        int result = 0;

//        for (int inx = 0; inx < words.length; inx++) {
//
//            List<Word> searchedWordList = wordList;
//            Map<String, Integer> indexMap = new HashMap<>();
//            for (indexMap.put("index", 0); indexMap.get("index") < words[inx].length(); indexMap.put("index",
//                    indexMap.get("index") + 1)) {
//                int jnx = indexMap.get("index");
//                char text = words[inx].charAt(jnx);
//
//                searchedWordList = searchedWordList.stream().filter((word) -> {
//                    if (jnx < word.splitedData.length && text == word.splitedData[jnx]) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }).collect(Collectors.toList());
//
//                if (searchedWordList.size() <= 1) {
//                    result += jnx + 1;
//                    break;
//                } else if (jnx == words[inx].length() - 1) {
//                    result += jnx + 1;
//                }
//            }
//
//        }
        
        
        for (int inx = 0; inx < words.length; inx++) {

            List<Word> searchedWordList = wordList;
            for (int jnx = 0; jnx < words[inx].length(); jnx++) {
                char text = words[inx].charAt(jnx);
                final Integer verticalInx = jnx;
                searchedWordList = searchedWordList.stream().filter((word) -> {
                    if (verticalInx < word.splitedData.length && text == word.splitedData[verticalInx]) {
                        return true;
                    } else {
                        return false;
                    }
                }).collect(Collectors.toList());

                if (searchedWordList.size() <= 1) {
                    result += jnx + 1;
                    break;
                } else if (jnx == words[inx].length() - 1) {
                    result += jnx + 1;
                }
            }

        }
        
        
        return result;
    }

    // private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println(solution(new String[] { "go", "gone", "guild" }));
        System.out.println(solution(new String[] { "abc", "def", "ghi", "jklm" }));
        System.out.println(solution(new String[] { "word", "war", "warrior", "world" }));

    }

}
