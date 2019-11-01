package debug_thread.a;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	public final static int SEM_SIZE = 10;

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(SEM_SIZE);
		MyThreadSem t1 = new MyThreadSem("t1", semaphore);
		MyThreadSem t2 = new MyThreadSem("t2", semaphore);
		t1.start();
		t2.start();
		int permits = 5;
		System.out.println(Thread.currentThread().getName() + " trying to acquire");
		try {
			semaphore.acquire(permits);
			System.out.println(Thread.currentThread().getName() + " acquire successfully");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
			System.out.println(Thread.currentThread().getName() + " release successfully");
		}
	}

}

class MyThreadSem extends Thread {

	private Semaphore semaphore;

	public MyThreadSem(String name, Semaphore semaphore) {
		super(name);
		this.semaphore = semaphore;
	}

	public void run() {
		int count = 3;
		System.out.println(Thread.currentThread().getName() + " trying to acquire ");
		try {
			semaphore.acquire(count);
			System.out.println(Thread.currentThread().getName() + " acquire successfully");
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			semaphore.release(count);
			System.out.println(Thread.currentThread().getName() + " release successfully");
		}
	}
}
