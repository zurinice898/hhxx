package debug_thread.a;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractQueueSynchronizerDemo {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		MyThread t1 = new MyThread("t1", lock);
		MyThread t2 = new MyThread("t2", lock);
		MyThread t3 = new MyThread("t3", lock);
		MyThread t4 = new MyThread("t4", lock);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}

class MyThread extends Thread {
	private Lock lock;

	public MyThread(String name, Lock lock) {
		super(name);
		this.lock = lock;
	}

	public void run() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread() + "running");
		} finally {
			lock.unlock();
		}
	}

}
