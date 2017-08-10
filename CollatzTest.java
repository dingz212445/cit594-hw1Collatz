package collatz;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CollatzTest {

	@Test
	public void testCollatz_1() {
		assertEquals(1, Collatz.collatz_1(1));
		assertEquals(2, Collatz.collatz_1(4));
		assertEquals(22, Collatz.collatz_1(7));
	}
	
	@Test
	public void testSequenceOf() {
		List<Long> sequence = new ArrayList<Long>();
		sequence.add((long)2);
		sequence.add((long)1);
		assertEquals(sequence, Collatz.sequencyOf(2));
	}
	
	@Test
	public void testLengthOfSequence() {
		assertEquals(2, Collatz.lengthOfSequence(2));
		assertEquals(8, Collatz.lengthOfSequence(3));
	}
	
	@Test
	public void testLargestValueInSequency() {
		assertEquals(16, Collatz.largestValueInSequence(3));
	}
	
	@Test
	public void testSimpleComputeSequenceLengths() {
		int[] sequence = {0, 1, 2, 8};
		for (int i = 0; i < 4; i++) {
			assertEquals(sequence[i], Collatz.simpleComputeSequenceLengths(3)[i]);
		}
	}
	
	@Test
	public void testMemorizedComputeSequenceLengths() {
		int[] sequence = {0, 1, 2, 8};
		for (int i = 0; i < 4; i++) {
			assertEquals(sequence[i], Collatz.memorizedComputeSequenceLengths(3)[i]);
		}
	}

}
