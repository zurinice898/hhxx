package debug_thread.ljw.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ArrayListTest {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(1);
		Hashtable table = new Hashtable();
		table.put(1, "2");
		table.put(1, "3");
		table.remove(1);
		ArrayList<Integer> list = new ArrayList<>();
		List lll = Collections.synchronizedList(list);
		lll.add(1);
		LinkedList<Integer> link = new LinkedList<Integer>();
		link.offer(1);
		link.offer(2);
		link.offer(3);
		int length = link.size();
		for (int i = 0; i < length; i++) {
			System.out.println(link.poll());
		}
	}

}
