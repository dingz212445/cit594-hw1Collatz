package collatz;

import java.util.List;
import java.util.ArrayList;

public class Collatz {
	
	class Pair<A, B> {
		private A first;
		private B second;
		
		public Pair(A first, B second) {
			this.first = first;
			this.second = second;
		}
		
		public A _1() {
			return this.first;
		}
		
		public B _2() {
			return this.second;
		}
		
		@Override
		public String toString() {
			return "(" + first + ", " + second + ")";
		}
	}

	public static void main(String[] args) {
		for(int i = 2; i < 10000; i = i + 100) {
			doTimings(i);
		}
		
		for (int i = 1; i < 100; i++) {
			System.out.print(i + " : length  " + lengthOfSequence(i) + ", max  " +
					largestValueInSequence(i) + ": ");
			List<Long> sequency = sequencyOf(i);
			for (int j = 0; j < sequency.size(); j++) {
				System.out.print(sequency.get(j) + "  ");
			}
			System.out.println();
		}

	}
	
	static int[] simpleComputeSequenceLengths(long n) {
		int[] sequenceLengths = new int[(int)n + 1];
		sequenceLengths[0] = 0;
		for (int i = 1; i < n + 1; i++) {
			sequenceLengths[i] = lengthOfSequence(i);
		}
		return sequenceLengths;
	}
	
	static int[] memorizedComputeSequenceLengths(long n) {
		int[] sequenceLengths = new int[(int)n + 1];
		sequenceLengths[0] = 0;
		sequenceLengths[1] = 1;
		for (int i = 2; i < n+1; i++) {
			int consequenceLength = 1;
			long collatz_once = collatz_1((long)i);
			while (collatz_once > i) {
				collatz_once = collatz_1(collatz_once);
				consequenceLength++;
			}
			sequenceLengths[i] = sequenceLengths[(int)collatz_once] + consequenceLength;
		}
		return sequenceLengths;
	}
	
	static void doTimings(long n) {
		System.gc();
		long startTime, totalElapsedTime = 0;
		for (int i = 0; i < 10; i++) {
			startTime = System.nanoTime();
			simpleComputeSequenceLengths(n);
			totalElapsedTime = totalElapsedTime + (System.nanoTime() - startTime);
		}
		System.out.println("SimpleComputeSequenceLength calculation last: " + totalElapsedTime/10);
		
		totalElapsedTime = 0;
		for (int i = 0; i < 10; i++) {
			startTime = System.nanoTime();
			memorizedComputeSequenceLengths(n);
			totalElapsedTime = totalElapsedTime + (System.nanoTime() - startTime);
		}
		System.out.println("MemorizedComputeSequenceLength calculation last: " + totalElapsedTime);
	}
	
	static long collatz_1(long n) {
		if (n == 1) {
			return 1;
		}
		
		if (n % 2 == 0) {
			return n/2;
		} else {
			return 3 * n + 1;
		}
	}
	
	static List<Long> sequencyOf(long n){
		List<Long> sequence = new ArrayList<Long>();
		assert n >= 1;
		sequence.add(n);
		while (n != 1) {
			long next_n = collatz_1(n);
			sequence.add(next_n);
			n = next_n;
		}
		
		return sequence;
	}
	
	static int lengthOfSequence(long n) {
		return sequencyOf(n).size();
	}
	
	static long largestValueInSequence(long n) {
		List<Long> sequence = Collatz.sequencyOf(n);
		long largestValue = sequence.get(0);
		for (long i : sequence) {
			if (i > largestValue) {
				largestValue = i;
			}
		}
		return largestValue;
	}

}
