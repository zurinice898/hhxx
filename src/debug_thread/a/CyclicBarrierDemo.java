package debug_thread.a;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(3, new Thread("barrierAction") {
			public void run() {
				System.out.println(Thread.currentThread().getName() + " barrier action ");
			}
		});
		MyThreadCyc t1 = new MyThreadCyc("t1", cb);
		MyThreadCyc t2 = new MyThreadCyc("t2", cb);
		t1.start();
		t2.start();
		System.out.println(Thread.currentThread().getName() + " going to await ");
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " continue ");

	}
}

class MyThreadCyc extends Thread {
	private CyclicBarrier cb;

	public MyThreadCyc(String name, CyclicBarrier cb) {
		super(name);
		this.cb = cb;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " going to await ");
		try {
			cb.await();
			System.out.println(Thread.currentThread().getName() + " continue ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
