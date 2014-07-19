import static org.junit.Assert.*;

import org.junit.Test;


public class TestDeque {
	@Test
	public void sequentialAddRemoveTest() {
	   Deque<String> deque = new Deque<String>();
	   
	   deque.addFirst("first");
	   deque.addLast("last");
	   deque.addFirst("before first");
	   deque.addLast("after last");
	   
	   assertEquals(4, deque.size());
	   assertEquals("expected: 'before first'", "before first", deque.removeFirst());
	   assertEquals("expected: 'after last'", "after last", deque.removeLast());
	   assertEquals("expected: 'last'", "last", deque.removeLast());
	   assertEquals("expected: 'first'", "first", deque.removeFirst());
	   assertEquals(true, deque.isEmpty());

	   deque.addLast("one more last");	   
	   deque.addFirst("one more first");
	   
	   assertEquals(2, deque.size());
	   assertEquals("expected: 'one more first'", "one more first", deque.removeFirst());
	   assertEquals("expected: 'one more last'", "one more last", deque.removeLast());
	 } 
}
