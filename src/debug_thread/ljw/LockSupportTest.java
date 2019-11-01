package debug_thread.ljw;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
	public static void main(String[] args) {
		final Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				LockSupport.unpark(Thread.currentThread());
				LockSupport.park();
				System.out.println("thread " + Thread.currentThread().getId() + " awake!");
			}
		});
		t.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.interrupt();
	}

}
