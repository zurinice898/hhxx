package debug_thread.a;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinTest {

	public static void main(String[] args) throws Throwable {
//		ActionTest();
		TaskTest();
	}

	public static class CalTask extends RecursiveTask<Integer> {
		private static final int threshold = 20;
		private int arr[];
		private int start;
		private int end;

		public CalTask(int[] arr, int start, int end) {
			this.arr = arr;
			this.start = start;
			this.end = end;
		}

		@Override
		protected Integer compute() {
			int sum = 0;
			if (end - start < threshold) {
				for (int i = start; i < end; i++) {
					sum += arr[i];
				}
				return sum;
			} else {
				int middle = (start + end) / 2;
				CalTask left = new CalTask(arr, start, middle);
				CalTask right = new CalTask(arr, middle, end);
				left.fork();
				right.fork();
				return left.join() + right.join();
			}
		}

	}

	public static void TaskTest() throws Throwable, ExecutionException {
		int arr[] = new int[100];
		Random random = new Random();
		int total = 0;
		for (int i = 0, len = arr.length; i < len; i++) {
			int temp = random.nextInt(20);
			total += (arr[i] = temp);
		}
		System.out.println("total: " + total);
		ForkJoinPool pool = new ForkJoinPool();
		Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
		System.out.println(future.get());
		pool.shutdown();
	}

	/**
	 * 定义一个可分解的的任务类，继承了RecursiveAction抽象类 必须实现它的compute方法
	 */
	public static class myTask extends RecursiveAction {

		private static final long serialVersionUID = 1L;
		// 定义一个分解任务的阈值——50,即一个任务最多承担50个工作量
		int THRESHOLD = 50;
		// 任务量
		int task_Num = 0;

		myTask(int Num) {
			this.task_Num = Num;
		}

		@Override
		protected void compute() {
			if (task_Num <= THRESHOLD) {
				System.out.println(Thread.currentThread().getName() + "承担了" + task_Num + "份工作");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				// 随机解成两个任务
				Random m = new Random();
				int x = m.nextInt(50);

				myTask left = new myTask(x);
				myTask right = new myTask(task_Num - x);

				left.fork();
				right.fork();
			}
		}
	}

	public static void ActionTest() throws Throwable {
		// 创建一个支持分解任务的线程池ForkJoinPool
		ForkJoinPool pool = new ForkJoinPool();
		myTask task = new myTask(178);

		pool.submit(task);
		pool.awaitTermination(20, TimeUnit.SECONDS);// 等待20s，观察结果
		pool.shutdown();
	}

}
