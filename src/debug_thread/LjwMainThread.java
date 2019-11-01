package debug_thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LjwMainThread {

	public static void main(String[] args) {
		Object object;
		Thread thread;
		LockSupport locksupport;
		AbstractQueuedSynchronizer AbstractQueuedSynchronizer;
		ReentrantReadWriteLock ReentrantReadWriteLock;
		CyclicBarrier CyclicBarrier;
		CountDownLatch CountDownLatch;
		Semaphore Semaphore;
		Future f;
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
		ThreadPoolExecutor ThreadPoolExecuter;
		ScheduledThreadPoolExecutor ScheduledThreadPoolExecutor;
	}

}
