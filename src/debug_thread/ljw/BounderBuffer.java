package debug_thread.ljw;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class BounderBuffer {
	private int contents;
	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public synchronized void put(Object N) {
		while (items.length == count) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		items[putptr++] = N;
		if (putptr == items.length) {
			putptr = 0;
		}
		count++;
		notifyAll();

	}

	public synchronized Object take() {
		while (count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Object result = items[takeptr++];
		if (takeptr == items.length) {
			takeptr = 0;
		}
		count--;
		notifyAll();
		return result;
	}

	public static class Producer implements Runnable {
		private BounderBuffer q;

		public Producer(BounderBuffer p) {
			this.q = p;
			new Thread(this, "Producer").start();
		}

		int i = 0;

		public void run() {
			int i = 0;
			while (true) {
				q.put(i++);
			}
		}
	}

	public static class Consumer implements Runnable {
		private BounderBuffer q;

		public Consumer(BounderBuffer p) {
			this.q = p;
			new Thread(this, "Consumer").start();
		}

		public void run() {
			while (true) {
				System.out.println(q.take());
			}
		}
	}

	public static void main(String[] args) {
		AbstractQueuedSynchronizer AbstractQueuedSynchronizer;
		final BounderBuffer buffer = new BounderBuffer();
		new Thread(new Producer(buffer)).start();
		new Thread(new Consumer(buffer)).start();
	}

}
