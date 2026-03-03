import java.util.*;

// 이 문제를 푸는데, 어디까지 함께 가고, 어디서 부터 따로 갈지를 보고 최소 거리를 봐야한다고 생각함.
// 그럼 모든 노드 사이의 최단거리를 구해야한다고 생각해서 N=200 이라서 플로이드 워셜로 풀음
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 노드가 최대 200개, 요금이 최대 10만. 
        // 200 * 10만 = 2000만이라서 2000만 + 1로 연결되지 않음 초기화
        int INF = 20000001; 
        int[][] dist = new int[n + 1][n + 1];
        
        // 1. 최단 거리 배열 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // 자기 자신으로 가는 비용은 0
        }
        
        // 2. 양방향 연결 간선 입력 받기
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];
            dist[u][v] = cost;
            dist[v][u] = cost;
        }
        
        // 3. 플로이드-워셜로 모든 노드 간의 최단 경로 구함.
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // 4. i 까지 같이 가고 나머지는 혼자 가는 것의 최소 거리를 확인하기
        int answer = INF;
        for (int i = 1; i <= n; i++) {
            // S->i (합승) + i->A (A혼자) + i->B (B혼자)
            int cost = dist[s][i] + dist[i][a] + dist[i][b];
            answer = Math.min(answer, cost);
        }
        
        return answer;
    }
}