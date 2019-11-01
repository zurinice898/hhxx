package debug_thread.a;

public class DepotTest {
	public static void main(String[] args) {
		Depot depot = new Depot(500);
		new Producer(depot).produce(500);
		new Producer(depot).produce(200);
		new Producer(depot).produce(100);
		new Consumer(depot).consume(500);
		new Consumer(depot).consume(200);
		new Consumer(depot).consume(100);
	}

}

class Consumer {
	private Depot depot;

	public Consumer(Depot depot) {
		this.depot = depot;
	}

	public void consume(int no) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				depot.consume(no);
			}
		}, no + " comsume thread ").start();
	}
}

class Producer {
	private Depot deport;

	public Producer(Depot depot) {
		this.deport = depot;
	}

	public void produce(int no) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				deport.produce(no);
			}
		}, no + " produce thread ").start();
	}
}
