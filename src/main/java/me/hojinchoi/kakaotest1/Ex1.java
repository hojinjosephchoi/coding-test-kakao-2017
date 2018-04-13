package me.hojinchoi.kakaotest1;

/**
 * http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
 * 
 * @author hojinchoi
 *
 */

public class Ex1 {

    private static String getBinaryResult(int source) {
        if (source <= 1) {
            return Integer.toString(source);
        } else {
            return getBinaryResult(source / 2) + source % 2;
        }
    }

    public static int[] getBinaryNumberArr(int source, int arraySize) {
        int[] results = new int[arraySize];

        String strBinary = getBinaryResult(source);

        int inx = 0;
        for (inx = 0; inx < arraySize - strBinary.length(); inx++) {
            results[inx] = 0;
        }

        for (int jnx = 0; jnx < strBinary.length(); jnx++, inx++) {
            results[inx] = Integer.parseInt(strBinary.charAt(jnx) + "");
        }

        return results;
    }

    public static void main(String[] args) {

        // int n = 5;
        // int[] arr1 = {9, 20, 28, 18, 11};
        // int[] arr2 = {30, 1, 21, 17, 28};

        int n = 6;
        int[] arr1 = { 46, 33, 33, 22, 31, 50 };
        int[] arr2 = { 27, 56, 19, 14, 14, 10 };

        int[][] decryptArr1 = new int[n][n];
        int[][] decryptArr2 = new int[n][n];

        String[][] resultMaps = new String[n][n];

        for (int inx = 0; inx < n; inx++) {
            decryptArr1[inx] = getBinaryNumberArr(arr1[inx], n);
            decryptArr2[inx] = getBinaryNumberArr(arr2[inx], n);
        }

        for (int inx = 0; inx < n; inx++) {
            for (int jnx = 0; jnx < n; jnx++) {
                if (decryptArr1[inx][jnx] == 1 || decryptArr2[inx][jnx] == 1) {
                    resultMaps[inx][jnx] = "#";
                } else {
                    resultMaps[inx][jnx] = " ";
                }
            }
        }

        for (int inx = 0; inx < n; inx++) {
            for (int jnx = 0; jnx < n; jnx++) {
                System.out.print(resultMaps[inx][jnx]);
            }
            System.out.println();
        }

    }

}
