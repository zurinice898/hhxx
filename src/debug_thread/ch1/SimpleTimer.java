package debug_thread.ch1;

public class SimpleTimer {

	private static int count = 60;

	public static void main(String[] args) {
		while (true) {
			countDown();
			if (count == 0) {
				break;
			} else {
				System.out.println("Remain " + count + "second(s)");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
		System.out.println("Done.");
	}

	public static void countDown() {
		count--;
	}

}
