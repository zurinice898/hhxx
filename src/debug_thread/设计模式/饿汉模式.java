package debug_thread.设计模式;

public class 饿汉模式 {
	private static final 饿汉模式 singleton = new 饿汉模式();

	private 饿汉模式() {

	}

	public static 饿汉模式 getSingleton() {
		return singleton;
	}

}
