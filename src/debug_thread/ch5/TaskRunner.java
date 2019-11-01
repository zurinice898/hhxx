package debug_thread.ch5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner {
	protected final BlockingQueue<Runnable> channel;
	protected volatile Thread workerThread;
	protected volatile boolean inUse = true;
	public final AtomicInteger reservations = new AtomicInteger(0);

	public TaskRunner(BlockingQueue<Runnable> channel) {
		this.channel = new LinkedBlockingQueue<Runnable>();
		workerThread = new WorkerThread();
	}

	public void init() {
		if (workerThread != null) {
			workerThread.start();
		}
	}

	public void submit(Runnable task) {
		try {
			if (inUse) {
				channel.put(task);
				reservations.incrementAndGet();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void shutDown() {
		inUse = false;
		final Thread t = workerThread;
		if (null != t) {
			t.interrupt();
		}
	}

	public void cancelTask() {
		workerThread.interrupt();
	}

	class WorkerThread extends Thread {

		public void run() {

			Runnable task = null;
			try {
				for (;;) {
					if (!inUse && reservations.get() <= 0) {
						break;
					}
					task = channel.take();
					try {
						task.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
					reservations.decrementAndGet();

				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				workerThread = null;
			}

		}
	}

}
