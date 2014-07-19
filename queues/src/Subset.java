/**
 * @author vitalii.stukalov
 * 
 */
public class Subset {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int cnt = Integer.valueOf(args[0]);
		RandomizedQueue<String> s = new RandomizedQueue<String>();
		
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (("-").equals(item))
				break;
			s.enqueue(item);
		}
		for (int i = 0; i < cnt; i++) {
			StdOut.println(s.dequeue());

		}

		// for(String item: s)
		// StdOut.println(s.dequeue());

		StdOut.println("(" + s.size() + " left on stack)");
	}
}