import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author vitalii.stukalov
 * 
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a; // array of items
	private int N; // number of elements on stack
	private int[] order;

	/**
	 * construct an empty randomized queue
	 */
	public RandomizedQueue() {
		a = (Item[]) new Object[2];
	}

	/**
	 * @return
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * return the number of items on the queue
	 * 
	 * @return
	 */
	public int size() {
		return N;
	}

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		assert capacity >= N;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}

	/**
	 * add the item
	 * 
	 * @param item
	 */
	public void enqueue(Item item) {
		if (N == a.length)
			resize(2 * a.length); // double size of array if necessary
		a[N++] = item; // add item
	}

	private void exch(int from, int to) {
		Item swap = a[to];
		a[to] = a[from];
		a[from] = swap;
	}

	/**
	 * delete and return a random item
	 * 
	 * @return
	 */
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		exch(StdRandom.uniform(N), N - 1);
		Item item = a[N - 1];
		a[N - 1] = null; // to avoid loitering
		N--;
		// shrink size of array if necessary
		if (N > 0 && N == a.length / 4)
			resize(a.length / 2);
		return item;
	}

	/**
	 * return (but do not delete) a random item
	 * 
	 * @return
	 */
	public Item sample() {
		return a[StdRandom.uniform(N)];
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomOrderArrayIterator();
	}

	private int[] createOrderArray() {
		int[] order = new int[N];
		for (int i = 0; i < N; i++)
			order[i] = i;
		StdRandom.shuffle(order);
		return order;
	}

	// an iterator, doesn't implement remove() since it's optional
	private class RandomOrderArrayIterator implements Iterator<Item> {
		private int i;
		private int[] order;

		public RandomOrderArrayIterator() {
			i = N;
			order = createOrderArray();
		}

		public boolean hasNext() {
			return i > 0;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return a[order[--i]];
		}
	}

	/**
	 * unit testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RandomizedQueue<String> s = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				s.enqueue(item);
			else if (!s.isEmpty())
				StdOut.print(s.dequeue() + " ");
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}
}