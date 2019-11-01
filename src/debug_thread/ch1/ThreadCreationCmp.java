package debug_thread.ch1;

import debug_thread.tool.Tools;

public class ThreadCreationCmp {

	public static void main(String[] args) {
		Thread t;
		CountingTask ct = new CountingTask();

		final int processNums = Runtime.getRuntime().availableProcessors();
		for (int i = 0; i < 2 * processNums; i++) {
			t = new Thread(ct);
			t.start();
		}
		for (int i = 0; i < 2 * processNums; i++) {
			CountingThread thread = new CountingThread();
			thread.run();
		}

	}

	static class Counter {
		private int count = 0;

		public void increment() {
			count++;
		}

		public int value() {
			return count;
		}
	}

	static class CountingTask implements Runnable {
		private Counter counter = new Counter();

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				doSomething();
				counter.increment();
			}
			System.out.println("CountingTask:" + counter.value());
		}

		private void doSomething() {
			Tools.randomPause(80);
		}

	}

	static class CountingThread extends Thread {
		private Counter counter = new Counter();

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				doSomthing();
				counter.increment();
			}
			System.err.println("CounterThread:" + counter.value());
		}

		private void doSomthing() {
			Tools.randomPause(80);
		}
	}

}
