package debug_thread.设计模式;

public class 双重检查懒汉模式 {
	private static volatile 双重检查懒汉模式 instance = null;

	private 双重检查懒汉模式() {

	}

	public static 双重检查懒汉模式 getInstance() {
		if (instance == null) {
			synchronized (双重检查懒汉模式.class) {
				if (instance == null)
					instance = new 双重检查懒汉模式();
			}
		}
		return instance;
	}

}
