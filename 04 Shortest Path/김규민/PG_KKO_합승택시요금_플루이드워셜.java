import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {

        int[][] mat = new int[n+1][n+1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(mat[i], 1_000_000);
            mat[i][i] = 0;
        }
        for(int i = 0; i< fares.length; i++){
            int[] k = fares[i];
            mat[k[0]][k[1]] = k[2];
            mat[k[1]][k[0]] = k[2];
        }
        for(int k =1; k<=n;k++){
            for(int i = 1; i<=n;i++){
                for(int j = 1 ; j <= n ; j++){
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    mat[j][i] = mat[i][j];
                }
            }
        }
//         for(int i = 0; i<=n;i++){
//         System.out.println(Arrays.toString(mat[i]));

//         }
        int result = Integer.MAX_VALUE;
        for(int i = 1; i<=n;i++){
            result = Math.min(result,mat[s][i] + mat[i][a]+ mat[i][b]);
        }
        int answer = result;
        return answer;
    }
}