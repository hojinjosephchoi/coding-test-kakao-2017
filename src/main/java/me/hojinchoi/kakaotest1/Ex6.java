package me.hojinchoi.kakaotest1;


import java.io.*;

public class Ex6 {
    
    static class Block {
        private char type = ' ';
        private boolean isDeleteTarget = false;
    }
    
    static Block[][] generateBlockMap(int m, int n, String[] board) {
        
        Block[][] blockMap = new Block[m][n];
        for(int inx = 0; inx < m; inx++) {
            for(int jnx = 0; jnx < n; jnx++) {
                blockMap[inx][jnx] = new Block();
                blockMap[inx][jnx].type = board[inx].charAt(jnx);
            }
        }
        
        return blockMap;
    }
    
    static void printBlock(Block[][] blockMap) {
        
        System.out.println("------");
        for(int inx = 0; inx < blockMap.length; inx++) {
            for(int jnx = 0; jnx < blockMap[inx].length; jnx++) {
                                
                System.out.print(blockMap[inx][jnx].type + " ");
            }
            System.out.println();
        }
    }
    
    static boolean hasDeleteTargetBlock(Block[][] blockMap) {
        boolean result = false;
        
        for(int inx = 0; inx < blockMap.length - 1; inx++) {
            for(int jnx = 0; jnx < blockMap[inx].length - 1; jnx++) {
                char baseBlockType = blockMap[inx][jnx].type;
                if (baseBlockType != ' ' && baseBlockType == blockMap[inx + 1][jnx].type
                        && baseBlockType == blockMap[inx][jnx + 1].type
                        && baseBlockType == blockMap[inx + 1][jnx + 1].type) {

                    blockMap[inx][jnx].isDeleteTarget = true;
                    blockMap[inx + 1][jnx].isDeleteTarget = true;
                    blockMap[inx][jnx + 1].isDeleteTarget = true;
                    blockMap[inx + 1][jnx + 1].isDeleteTarget = true;

                    result = true;
                    
                    
                }
            }
        }
        
        return result;
    }
    
    static void deleteTargetBlock(Block[][] blockMap) {

        for (int inx = 0; inx < blockMap.length; inx++) {
            for (int jnx = 0; jnx < blockMap[inx].length; jnx++) {
                if (blockMap[inx][jnx].isDeleteTarget) {
                    blockMap[inx][jnx].type = ' ';
                    blockMap[inx][jnx].isDeleteTarget = false;
                }
            }
        }

    }
    
    static void fillDeletedBlock(int m, int n, Block[][] blockMap) {

        for (int jnx = 0; jnx < n; jnx++) {
            int srcInx = m - 1;
            int destInx = m - 1;
            for (; srcInx >= 0; srcInx--) {
                if(blockMap[srcInx][jnx].type == ' ') {
                    if(blockMap[destInx][jnx].type != ' ') {
                        //check....
                        destInx--;
                    }
                } else {
                    if(blockMap[destInx][jnx].type == ' ') {
                        blockMap[destInx][jnx].type = blockMap[srcInx][jnx].type;
                        blockMap[srcInx][jnx].type = ' ';
                    }
                    destInx--;
                }
            }
        }
        
    }
    
    static int blockBreaker(int m, int n, String[] board) {
        
        Block[][] blockMap = generateBlockMap(m, n, board);
        
        
        while(hasDeleteTargetBlock(blockMap)) {
            printBlock(blockMap);
            deleteTargetBlock(blockMap);
            fillDeletedBlock(m, n, blockMap);
            
            printBlock(blockMap);
        }
        
        int emptyBlockCount = 0;
        for(int inx = 0; inx < blockMap.length; inx++) {
            for(int jnx = 0; jnx < blockMap[inx].length; jnx++) {
                if(blockMap[inx][jnx].type == ' ') {
                    emptyBlockCount++;
                }
            }
        }
        return emptyBlockCount;
    }
    
    

//    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        
        
        System.out.println(blockBreaker(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
//        System.out.println(blockBreaker(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
    }

}

