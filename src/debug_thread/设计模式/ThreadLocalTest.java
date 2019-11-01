package debug_thread.设计模式;

public class ThreadLocalTest {
//	public T get() { }  
//	public void set(T value) { }  
//	public void remove() { }  
//	protected T initialValue() { }  

	ThreadLocal<Long> longlocal = new ThreadLocal<Long>();
	ThreadLocal<String> StringLocal = new ThreadLocal<String>();

	public void set() {
		longlocal.set(Thread.currentThread().getId());
		StringLocal.set(Thread.currentThread().getName());
	}

	public long getLong() {
		return longlocal.get();
	}

	public String getString() {
		return StringLocal.get();
	}

	public static void main(String[] args) throws Throwable {
		ThreadLocalTest test = new ThreadLocalTest();

//		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				test.set();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			}

		});
		thread1.start();
		thread1.join();

		System.out.println(test.getLong());
		System.out.println(test.getString());
	}

}
