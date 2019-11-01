package debug_thread.ljw.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {
	public static void main(String[] args) {
//		Map<Integer, String> map = new LinkedHashMap<Integer, String>(16, (float) 0.75, true);
//		Map<Integer, String> map = new ConcurrentHashMap(4, (float) 0.75);
		TreeMap<Integer, String> map = new TreeMap();
		Hashtable d;
		map.put(16, "luhaha");
		map.put(32, "ludada");
		map.put(48, "lushang");
		map.put(64, "lushang");
		map.put(80, "lushang");
		map.get(16);
		map.put(96, "lushang");
		map.put(112, "lushang");
		map.put(128, "lushang");
		map.put(144, "luhaha");
		map.put(160, "luhaha");
		map.remove(32);
		map.get(48);

		int t1 = map.ceilingKey(64);
		int t2 = map.floorKey(64);
		int t3 = map.lowerKey(64);
		int t4 = map.higherKey(64);
		for (Integer i : map.keySet()) {
			System.out.print(i + " ");
		}

	}

	public static void HashMapTest() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(16, "luhaha");
		map.put(32, "ludada");
		map.put(48, "lushang");
		map.put(64, "lushang");
		map.put(80, "lushang");
		map.put(96, "lushang");
		map.put(112, "lushang");
		map.put(128, "lushang");
		map.put(144, "luhaha");
		map.put(160, "ludada");
		map.put(176, "lushang");
		map.put(192, "lushang");
		map.put(208, "lushang");
		map.put(224, "lushang");
		map.put(240, "lushang");
		map.put(256, "lushang");
		map.remove(16);

	}

}
