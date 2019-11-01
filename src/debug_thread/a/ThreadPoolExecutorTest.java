package debug_thread.a;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	private static ExecutorService executor = Executors.newFixedThreadPool(3);
	private static ThreadPoolExecutor executor_ = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<>());

	private static ExecutorService executor2 = Executors.newCachedThreadPool();
	private static ExecutorService executor2_ = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
			new SynchronousQueue<>());

	private static ExecutorService executor3 = Executors.newSingleThreadExecutor();

	private static ScheduledExecutorService executor4 = Executors.newScheduledThreadPool(5);
	ScheduledThreadPoolExecutor ScheduledThreadPoolExecutor;
	Timer Timer;
	FutureTask FutureTask;

	public static void main(String[] args) throws Throwable, ExecutionException {
		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
		for (int i = 1; i < 5; i++) {
			list.add(new Task("任务 " + i));
		}
		List<Future<Integer>> result = executor.invokeAll(list);
		for (Future f : result) {
			System.out.println(f.get());
		}
		executor.shutdownNow();

	}

	static class Task implements Callable<Integer> {
		private String name;

		public Task(String name) {
			this.name = name;
		}

		@Override
		public Integer call() throws Exception {
			System.out.println(Thread.currentThread().getName() + ", 任务名：" + name);
			return 1;
		}
	}

}
