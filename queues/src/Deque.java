import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author vitalii.stukalov
 * 
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
	private int F; // size of the stack
	private int L; // size of the stack
	private Node first; // top of stack
	private Node last; // top of stack

	// helper linked list class
	private class Node {
		private Item item;
		private Node next;
	}

	/**
	 * construct an empty deque
	 */
	public Deque() {
		first = null;
		last = null;
		F = 0;
		L = 0;
		assert check();
	}

	/**
	 * s the deque empty?
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return first == null && last == null;
	}

	/**
	 * return the number of items on the deque
	 * 
	 * @return
	 */
	public int size() {
		return F + L;
	}

	/**
	 * insert the item at the front
	 * 
	 * @param item
	 */
	public void addFirst(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		if (F == 0)
			first.next = last;
		else
			first.next = oldfirst;
		F++;
		assert check();
	}

	/**
	 * insert the item at the end
	 * 
	 * @param item
	 */
	public void addLast(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = oldlast;
		L++;
		assert check();
	}

	/**
	 * delete and return the item at the front
	 * 
	 * @return
	 */
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		if (F == 0)
			return removeLast();
		Item item = first.item; // save item to return
		first = first.next; // delete first node
		F--;
		assert check();
		return item; // return the saved item
	}

	/**
	 * delete and return the item at the end
	 * 
	 * @return
	 */
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		if (L == 0)
			return removeLast();
		Item item = last.item; // save item to return
		last = last.next; // delete first node
		L--;
		assert check();
		return item; // return the saved item
	}

	/**
	 * Returns an iterator to this stack that iterates through the items in LIFO
	 * order.
	 * 
	 * @return an iterator to this stack that iterates through the items in LIFO
	 *         order.
	 */
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}


	// check internal invariants
	private boolean check() {
		if (F == 0) {
			if (first != null)
				return false;
		} else if (F == 1) {
			if (first == null)
				return false;
			if (first.next != last)
				return false;
		} else {
			if (first.next == null)
				return false;
		}

		// check internal consistency of instance variable N
		int numberOfNodes = 0;
		for (Node x = first; x != null; x = x.next) {
			numberOfNodes++;
		}
		if (numberOfNodes != F)
			return false;

		return true;
	}

	/**
	 * unit testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Deque<String> s = new Deque<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				s.addLast(item);
			else if (!s.isEmpty())
				StdOut.print(s.removeLast() + " ");
		}
		StdOut.println("(" + s.size() + " left on stack)");
		if (s.size() > 0)
			StdOut.println(s);
	}
}
