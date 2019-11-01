package debug_thread.ch3;

public class SingletonLazy {
	private static volatile SingletonLazy instance;

	private SingletonLazy() {
	};

	public SingletonLazy getInstance() {
		if (instance == null) {
			synchronized (SingletonLazy.class) {
				if (instance == null)
					instance = new SingletonLazy();
			}
		}
		return instance;
	}

}
