package debug_thread.ljw;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class SimpleLock extends AbstractQueuedSynchronizer {
	@Override
	protected boolean tryAcquire(int arg) {
		if (compareAndSetState(0, 1)) {
			setExclusiveOwnerThread(Thread.currentThread());
			return true;
		}
		return false;
	}

	@Override
	protected boolean tryRelease(int arg) {
		setExclusiveOwnerThread(null);
		setState(0);
		return true;
	}

	public void lock() {
		acquire(1);
	}

	public boolean tryLock() {
		return tryAcquire(1);
	}

	public void unlock() {
		release(1);
	}

	public boolean isLocked() {
		return isHeldExclusively();
	}

	public static void main(String[] args) throws Throwable {
		final SimpleLock lock = new SimpleLock();
		lock.lock();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					lock.lock();
					System.out.println(Thread.currentThread().getId() + " acquired the lock!");
					lock.unlock();
				}
			}).start();
			Thread.sleep(100);
		}
		System.out.println("main Thread Unlock!");
		lock.unlock();
	}

}
