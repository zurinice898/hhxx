package debug_thread.ch5;

import java.util.Random;

public class TimeoutWaitExample {

	private static final Object lock = new Object();
	private static boolean ready = false;
	private static final Random random = new Random();

	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					synchronized (lock) {
						int r = random.nextInt(100);
						ready = false;
						if (ready == true) {
							lock.notify();
						}
					}
				}
			}
		});
		t.setDaemon(true);
		t.start();
		timeWait(1000);
	}

	private static void timeWait(int i) {
		if (i <= 0) {
			throw new IllegalArgumentException();
		}

		long start = System.currentTimeMillis();
		synchronized (lock) {
			while (!ready) {
				long timeToWait = i - (System.currentTimeMillis() - start);
				if (timeToWait <= 0) {
					break;
				} else {
					try {
						lock.wait(timeToWait);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (ready) {
				System.out.println("条件满足结束");
			} else {
				System.out.println("超时结束");
			}

		}

	}

}
