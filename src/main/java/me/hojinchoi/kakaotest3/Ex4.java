package me.hojinchoi.kakaotest3;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Ex4 {


    static class MusicInfo {
        private Date startDateTime;
        private Date endDateTime;
        private String title;
        private List<String> melodyList = new ArrayList<>();
        private boolean isMatch = false;
        private int index;
    }
    
    static String solution(String m, String[] rawMusicInfos) {
        
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        List<MusicInfo> musicInfoList = new ArrayList<>();
        try {
            for(int inx = 0; inx < rawMusicInfos.length; inx++) {
                
                String[] rawMusicInfo = rawMusicInfos[inx].split(",");
                MusicInfo musicInfo = new MusicInfo();
                musicInfo.index = inx;
                musicInfo.startDateTime = df.parse(rawMusicInfo[0]);
                musicInfo.endDateTime = df.parse(rawMusicInfo[1]);
                
                musicInfo.title = rawMusicInfo[2];
                
                int melodyInx = 0;
                String[] melodyArr = rawMusicInfo[3].split("");
                for(long jnx = musicInfo.startDateTime.getTime(); jnx < musicInfo.endDateTime.getTime(); jnx += (1000 * 60)) {
                    if(melodyInx + 1 < melodyArr.length && "#".equals(melodyArr[melodyInx + 1])) {
                        musicInfo.melodyList.add(melodyArr[melodyInx] + "#");
                        melodyInx += 2;
                    } else {
                        musicInfo.melodyList.add(melodyArr[melodyInx]);
                        melodyInx++;
                    }
                    
                    if( melodyInx >= melodyArr.length) {
                        melodyInx = 0;
                    }
                }
                musicInfoList.add(musicInfo);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        List<String> listenedMelodyArr = new ArrayList<>();
        for(int inx = 0; inx < m.length(); inx++) {
            if(inx + 1 < m.length() && '#' == m.charAt(inx + 1)) {
                listenedMelodyArr.add(m.charAt(inx) + "" + m.charAt(inx + 1));
                inx++;
            } else {
                listenedMelodyArr.add(m.charAt(inx) + "");
            }
        }
        for (MusicInfo musicInfo : musicInfoList) {
            int mInx = 0;
            for (String eachMelody : musicInfo.melodyList) {
                if (eachMelody.equals(listenedMelodyArr.get(mInx))) {
                    mInx++;
                } else {
                    mInx = 0;
                }
                
                if (mInx >= listenedMelodyArr.size()) {
                    musicInfo.isMatch = true;
                    break;
                }
            }
            
        }
        
        List<MusicInfo> resultList = musicInfoList.stream().filter((musicInfo) -> {
            return musicInfo.isMatch;
        }).sorted((music1, music2) -> {
            int result = 0;
            long music1Period = music1.endDateTime.getTime() - music1.startDateTime.getTime();
            long music2Period = music2.endDateTime.getTime() - music2.startDateTime.getTime();
            if(music1Period > music2Period) {
                result = -1;
            }else if(music1Period < music2Period) {
                result = 1;
            }
            
            if(result != 0) {
                return result;
            }
            
            if(music1.index > music2.index) {
                return 1;
            } else if(music1.index < music2.index) {
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());
        
        if(resultList.size() == 0) {
            return "(None)";
        } else {
            return resultList.get(0).title;
        }
        
    }

    // private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


//        String answer = solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"});
//        String answer = solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"});
        String answer = solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"});
        
        System.out.println(answer);
        

    }

}
