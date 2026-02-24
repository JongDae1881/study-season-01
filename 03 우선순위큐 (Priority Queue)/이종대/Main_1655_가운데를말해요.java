package PriorityQueue;

import java.io.*;
import java.util.*;

public class Main_1655_가운데를말해요 {
	
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder out = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine().trim());
		PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> upper = new PriorityQueue<>();
		// \sum_{n=1}^{N} (5 log_2 \frac{n}{2})  (if N = 100,000, then ~= 7,083,520 using Log-gamma function)
		// = 5 \sum(log n - 1) = 5(log(N!) - N) <= 5NlogN - 5N
		// If N = 100,000, then ~= 7,804,820.
		// O(N log N) (N log N ~= 100,000 * 16.6 = 1,660,000 )
		for (int i = 1 ; i  <= N ; i++) {
			// 홀수 번째 입력
			if (i % 2 == 1) {
				// log_2 \frac{n}{2}
				// O(log N)
				lower.offer(Integer.parseInt(br.readLine().trim()));
			// 짝수 번째 입력
			} else {
				// log_2 \frac{n}{2}
				// O(log N)
				upper.offer(Integer.parseInt(br.readLine().trim()));
			}
			if (!lower.isEmpty() && !upper.isEmpty()) {
				if (lower.peek() > upper.peek()) {
					// log_2 \frac{n}{2}
					// O(log N)
					int l_temp = lower.poll();
					// log_2 \frac{n}{2}
					// O(log N)
					int u_temp = upper.poll();
					int tmp = l_temp;
					l_temp = u_temp;
					u_temp = tmp;
					// log_2 \frac{n}{2}
					// O(log N)
					lower.offer(l_temp);
					// log_2 \frac{n}{2}
					// O(log N)
					upper.offer(u_temp);
				}
			}
			out.append(lower.peek()).append('\n');
		}
		System.out.print(out);
		br.close();
	}

}
