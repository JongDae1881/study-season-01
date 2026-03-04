package programmers;

import java.util.Arrays;

public class prog_72413_합승택시요금 {
	
	static final int INF = 20000002;
	
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        int[][] dist = new int[n + 1][n + 1];
        
        // O(N * N) -> fill() 을 들어가보면 O(N)임
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        // O(E)
        for (int i = 0; i < fares.length; i++) {
        	dist[fares[i][0]][fares[i][1]] = fares[i][2];
        	dist[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        // O(N ^ 3)
        for (int k = 1; k < n + 1; k++) {
        	for (int i = 1; i < n + 1; i++) {
        		for (int j = 1; j < n +1; j++) {
        			if (dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j]; 
        		}
        	}
        }
        
        // O(N)
        for (int i = 1; i < n + 1; i++) {
        	answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        
        
        return answer;
    }
}