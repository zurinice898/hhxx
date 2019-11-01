package debug_thread.ch3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import debug_thread.ch2.CircularSeqGenerator;

public class LockbasedCircularSeqGenerator implements CircularSeqGenerator {
	public final Lock lock = new ReentrantLock();
	private short sequence = -1;

	@Override
	public short nextSequence() {
		lock.lock();
		try {
			if (sequence >= 999) {
				sequence = 0;
			} else {
				sequence++;
			}
			return sequence;

		} finally {
			lock.unlock();
		}
	}

}
