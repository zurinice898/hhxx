package debug_thread.设计模式;

public class 静态内部类懒汉模式 {
	private static class InstanceHolder {
		public static 静态内部类懒汉模式 instance = new 静态内部类懒汉模式();
	}

	private 静态内部类懒汉模式() {

	}

	public static 静态内部类懒汉模式 getInstance() {
		return InstanceHolder.instance;
	}

}
