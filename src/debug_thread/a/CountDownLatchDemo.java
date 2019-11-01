package debug_thread.a;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws Throwable {
		int num = 2;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(num);
		for (int i = 1; i <= num; i++) {
			new Worker("t" + i, startSignal, doneSignal).start();
		}
		System.out.println(Thread.currentThread().getName() + " doing before start");
		startSignal.countDown();
		System.out.println(Thread.currentThread().getName() + " doing after start");

		System.out.println(Thread.currentThread().getName() + " before await");
		doneSignal.await();
		System.out.println(Thread.currentThread().getName() + " after await");

	}

}

class Worker extends Thread {

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	public Worker(String name, CountDownLatch start, CountDownLatch end) {
		super(name);
		this.startSignal = start;
		this.doneSignal = end;
	}

	@Override
	public void run() {
		try {
			startSignal.await();
			System.out.println(Thread.currentThread().getName() + " Worker: working");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " Worker: done");
			doneSignal.countDown();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
