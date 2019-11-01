package debug_thread.a;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

	public static void main(String[] args) {
		ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();
		ReadThread rt1 = new ReadThread("rt1", rrwLock);
		ReadThread rt2 = new ReadThread("rt2", rrwLock);
//		ReadThread rt3 = new ReadThread("rt3", rrwLock);
//		ReadThread rt4 = new ReadThread("rt4", rrwLock);
		WriteThread wt1 = new WriteThread("wt1", rrwLock);
		rt1.start();
		rt2.start();
//		rt3.start();
//		rt4.start();
		wt1.start();
	}

}

class ReadThread extends Thread {
	private ReentrantReadWriteLock rrwLock;

	public ReadThread(String name, ReentrantReadWriteLock rrwLock) {
		super(name);
		this.rrwLock = rrwLock;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " trying to lock");
		try {
			rrwLock.readLock().lock();
			rrwLock.readLock().lock();
			System.out.println(Thread.currentThread().getName() + " lock successfully");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rrwLock.readLock().unlock();
			rrwLock.readLock().unlock();
			System.out.println(Thread.currentThread().getName() + " unlock successfully");
		}
	}
}

class WriteThread extends Thread {
	private ReentrantReadWriteLock rrwLock;

	public WriteThread(String name, ReentrantReadWriteLock rrwLock) {
		super(name);
		this.rrwLock = rrwLock;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " trying to lock");
		try {
			rrwLock.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + " lock successfully");
		} finally {
			rrwLock.writeLock().unlock();
			System.out.println(Thread.currentThread().getName() + " unlock successfully");
		}
	}
}
