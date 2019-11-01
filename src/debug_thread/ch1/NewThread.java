package debug_thread.ch1;

public class NewThread {
	public static void main(String[] args) {
		Thread thread = new WelcomThreadl();
		thread.start();
		System.out.printf("1.Welcom! I'm %s.%n", Thread.currentThread().getName());

	}

}

class WelcomThreadl extends Thread {
	@Override
	public void run() {
		System.out.printf("2.Welcom! I'm %s.%n", Thread.currentThread().getName());
	}
}