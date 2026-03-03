package bj;

import java.io.*;
import java.util.*;

public class Main_1504 {

    // 특정 노드에서 다른 노드로 가는 최대한의 거리는 (800-1)*1000 = 대략 800,000
    // 총 3번의 경로의 합이 전체 답 경로, 따라서 대략 2,400,000
    // 이것보다 훨씬 크면서 더해도 오버플로우가 나지 않는 1억으로 INF값 설정
    static final int INF = 100_000_000;

    static int N, E;
    static List<int[]>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N =  Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        //O(E)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[from].add(new int[]{to, cost});
            edges[to].add(new int[]{from, cost});
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        // 1->A,B | A->B,N | B -> A,N 으로 가는 거리
        Map<Integer,Integer> toAB = dijkstra(1, new HashSet<>(Arrays.asList(a, b)));
        Map<Integer,Integer> toBN = dijkstra(a, new HashSet<>(Arrays.asList(b, N)));
        Map<Integer,Integer> toAN = dijkstra(b, new HashSet<>(Arrays.asList(a, N)));

        // 각 구간의 거리 추출 (Map에 없는 경우 연결되지 않은 것이므로 INF 처리)
        int d1a = toAB.getOrDefault(a, INF);
        int d1b = toAB.getOrDefault(b, INF);
        int dab = toBN.getOrDefault(b, INF); // a -> b
        int dan = toBN.getOrDefault(N, INF); // a -> N
        int dbn = toAN.getOrDefault(N, INF); // b -> N

        // 경로 1: 1 -> a -> b -> N
        int path1 = d1a + dab + dbn;

        // 경로 2: 1 -> b -> a -> N
        int path2 = d1b + dab + dan;

        // 두 경로 중 최솟값 선택
        int result = Math.min(path1, path2);

        // 만약 결과가 INF보다 크거나 같으면 도달 불가능한 경로
        if (result >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
        br.close();
    }

    //O(E log V) : E = 2*10^5, V = 8*10^2
    static Map<Integer,Integer> dijkstra(int start, Set<Integer> ends) {
    	Map<Integer,Integer> ret = new HashMap<>();

        boolean[] visited = new boolean[N+1];
        Queue<int[]> q = new PriorityQueue<>((a, b)->Integer.compare(a[1], b[1]));
        q.offer(new int[]{start, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (ends.contains(cur[0])) {
            	ends.remove(cur[0]);
                ret.put(cur[0], cur[1]);
            }
            
            if(ends.isEmpty()) break; //모든 도착지에 도착함.

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;
            
            for (int[] next : edges[cur[0]]) {
                if(!visited[next[0]]) {
                    q.offer(new int[]{next[0], cur[1] + next[1]});
                }
            }
        }
        return ret;
    }
}
