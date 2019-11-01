package debug_thread.a;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureDemo {
	static class Chuju {

	}

	static class ShiCai {

	}

	static void cook(Chuju c, ShiCai s) {

	}

	public static void main(String[] args) throws Throwable {
		long startTime = System.currentTimeMillis();
		// 1.准备厨具
		Callable<Chuju> onlineShopping = new Callable<FutureDemo.Chuju>() {
			public Chuju call() throws Exception {
				System.out.println("第一步：下单");
				System.out.println("第一步：等待送货");
				Thread.sleep(4000);
//				throw new Exception();
				System.out.println("第一步：快递送达");
				return new Chuju();
			}
		};

		FutureTask<Chuju> task = new FutureTask<Chuju>(onlineShopping);

		new Thread(task).start();
		// 2准备食材
		Thread.sleep(2000);
		ShiCai shicai = new ShiCai();
		System.out.println("第二步：食材到位");
		if (!task.isDone()) {
			System.out.println("第三步：厨具还没到，心情好就等着（心情不好就调用cancel方法取消订单）");
//			task.cancel(false);
//			task.cancel(true);
		}
		Chuju chuju = task.get();
		System.out.println("万事俱备，开始showtime");
		cook(chuju, shicai);
		System.out.println("总共用时：" + (System.currentTimeMillis() - startTime) + "ms");

	}

}
