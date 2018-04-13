package me.hojinchoi.kakaotest3;

import java.io.*;
import java.util.*;

public class Ex3 {


    static class FileTitle {
        private String originFileName = "";
        private int originalInx = -1;
        private String head = "";
        private String numberStr = "";
        private int number = -1;
        public int getOriginalInx() {
            return originalInx;
        }
        public void setOriginalInx(int originalInx) {
            this.originalInx = originalInx;
        }
        public String getHead() {
            return head;
        }
        public void setHead(String head) {
            this.head = head;
        }
        public int getNumber() {
            return number;
        }
        public void setNumber(int number) {
            this.number = number;
        }
        
        
    }
    
    static String[] solution(String[] unsortedFiles) {
        List<FileTitle> fileNameList = new ArrayList<>();
        
        for(int inx = 0; inx < unsortedFiles.length; inx++) {
            FileTitle fileTitle = new FileTitle();
            fileTitle.originFileName = unsortedFiles[inx];
            fileTitle.originalInx = inx;
            
            
            String tmpFileName = unsortedFiles[inx];
            boolean isPassNumber = false;
            while(tmpFileName.length() > 0) {
                String character = tmpFileName.substring(0, 1);
//                if(character.matches("[A-z.\\t]") || character.matches("[-]") || character.matches("[\\s]")) {
                if(character.matches("[A-z.\\s[-]]")) {
                    if(isPassNumber) {
                        break;
                    }
                    fileTitle.head += character.toLowerCase();
                }else if(character.matches("[0-9]")) {
                    fileTitle.numberStr += character;
                    isPassNumber = true;
                }
                
                tmpFileName = tmpFileName.substring(1);
            }
            
            try {
                fileTitle.number = Integer.parseInt(fileTitle.numberStr);
            }catch(Exception e) {
                System.out.println();
            }
            
            
            fileNameList.add(fileTitle);
        }
        
        
//        String[] results = fileNameList.stream().sorted(Comparator.comparing(FileTitle::getHead))
//                .sorted((fileTitle1, fileTitle2) -> {
//                    
//                    int result = fileTitle1.head.compareTo(fileTitle2.head);
//                    
//                    if (result != 0) {
//                        return result;
//                    }
//                    
//                    result = Integer.compare(fileTitle1.number, fileTitle2.number);
//                    
//                    if (result != 0) {
//                        return result;
//                    }
//                    
//                    if (fileTitle1.originalInx > fileTitle2.originalInx) {
//                        return 1;
//                    }else if(fileTitle1.originalInx < fileTitle2.originalInx) {
//                        return -1;
//                    } else {
//                        return 0;
//                    }
//                    
//                }).map((fileTitle) -> new String(fileTitle.originFileName))
//                .toArray(String[]::new);
        
        String[] results = fileNameList.stream()
                .sorted(Comparator.comparing(FileTitle::getHead).thenComparing(FileTitle::getNumber)
                        .thenComparing(FileTitle::getOriginalInx))
                .map((fileTitle) -> new String(fileTitle.originFileName)).toArray(String[]::new);
        
        return results;
    }

    // private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        String[] sortedFiles = solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
//        String[] sortedFiles = solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});

        
        Arrays.stream(sortedFiles).forEach(System.out::println);
        

    }

}
