package debug_thread.ch5;

import java.util.concurrent.CountDownLatch;

public abstract class AbstractService implements Service {
	protected final CountDownLatch latch;
	protected boolean started = false;

	public AbstractService(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void start() {

		new ServiceStarter().start();

	}

	class ServiceStarter extends Thread {

		public void run() {
			try {
				doStart();
				started = true;

			} catch (Exception e) {

			} finally {
				latch.countDown();
			}
		}
	}

	abstract protected void doStart();

	@Override
	public void stop() {

	}

	@Override
	public boolean isStarted() {
		return started;
	}

}
