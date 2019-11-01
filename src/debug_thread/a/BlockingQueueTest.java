package debug_thread.a;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class BlockingQueueTest {
	{
		ArrayBlockingQueue ArrayBlockingQueue;
		LinkedBlockingQueue LinkedBlockingQueue;
		CopyOnWriteArrayList CopyOnWriteArrayLis;
		ConcurrentLinkedQueue ConcurrentLinkedQueue;
		CopyOnWriteArraySet CopyOnWriteArraySet;
		SynchronousQueue SynchronousQueue;
	}

	public static void main(String[] args) {

		ArrayBlockingQueuetest();

	}

	public static void ArrayBlockingQueuetest() {
//		ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
		LinkedBlockingQueue queue = new LinkedBlockingQueue(3);
		new Consumer(queue, "消费者#1").start();
		new Consumer(queue, "消费者#2").start();
		new Provider(queue, "生产者#1").start();
		new Provider(queue, "生产者#2").start();

	}

	static class Consumer extends Thread {
		private BlockingQueue<Object> queue;
		private String name;

		public Consumer(BlockingQueue<Object> queue, String name) {
			super(name);
			this.queue = queue;
		}

		public void run() {
			while (true) {
				consumer();
			}
		}

		private void consumer() {
			Object o = null;
			try {
				o = queue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ", consumer: " + o);
		}
	}

	static class Provider extends Thread {
		private BlockingQueue<Object> queue;
		private String name;

		public Provider(BlockingQueue<Object> queue, String name) {
			super(name);
			this.queue = queue;
		}

		public void run() {
			while (true) {
				provider();
			}
		}

		private void provider() {
			Object o = new Object();
			try {
				queue.put(o);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ", provider: " + o);
		}
	}

}
