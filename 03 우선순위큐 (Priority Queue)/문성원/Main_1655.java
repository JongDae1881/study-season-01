
import java.io.*;
import java.util.*;

/*
 * maxHeap에 먼저 넣고, minHeap에 넣기. (항상 maxHeap의 사이즈가 더 크거나 같기)
 * maxHeap의 top이 minHeap의 탑보다 크면  swap해주기 
 */
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb =new StringBuilder();
    	int N = Integer.parseInt(br.readLine());
    	//내림차순 Heap 선언(maxHeap)
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1,o2)->-Integer.compare(o1,o2));
    	//오름차순 Heap 선언(minHeap)
    	PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1,o2)->Integer.compare(o1,o2));
    	while(N-->0) {
    		int input = Integer.parseInt(br.readLine());
    		
    		//삽입 O(1) + 정렬 O(logN) <- 삽입과 동시에 정렬에 대한 연산이 발생 
    		if(maxHeap.size()==minHeap.size()) maxHeap.offer(input); 
    		else minHeap.offer(input);
    			
			if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
				//삭제 O(1)
				int maxHeapTemp = maxHeap.poll();
    			int minHeapTemp = minHeap.poll();
    			
    			//삽입 O(1) + 정렬 O(logN) <- 삽입과 동시에 정렬에 대한 연산이 발생
    			maxHeap.offer(minHeapTemp);
    			minHeap.offer(maxHeapTemp); 			
    		}
			//Heap의 top 확인 O(1) 
			if(minHeap.isEmpty())
				sb.append(maxHeap.peek()).append("\n");
			else sb.append(Math.min(maxHeap.peek(),minHeap.peek())).append("\n");
    	}
    	System.out.println(sb);
    	
    	//total O(NlogN) -> O(logN)을 N번 수행함
    }
}
