package day9;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficSignalUsingLock {
	private Lock firstLock = new ReentrantLock(true);
	private Lock secondLock = new ReentrantLock(true);
	private Lock thirdLock = new ReentrantLock(true);
	private Lock fourthLock = new ReentrantLock(true);

	private Condition firstOn = firstLock.newCondition();
	private Condition secondOn = secondLock.newCondition();
	private Condition thirdOn = thirdLock.newCondition();
	private Condition fourthOn = fourthLock.newCondition();

	private void signalFirst() {
		firstLock.lock();
		try {
			firstOn.signal();
		} finally {
			firstLock.unlock();
		}
	}

	private void signalSecond() {
		secondLock.lock();
		try {
			secondOn.signal();
		} finally {
			secondLock.unlock();
		}
	}

	private void signalThird() {
		thirdLock.lock();
		try {
			thirdOn.signal();
		} finally {
			thirdLock.unlock();
		}
	}

	private void signalFourth() {
		fourthLock.lock();
		try {
			fourthOn.signal();
		} finally {
			fourthLock.unlock();
		}
	}

	private AtomicInteger counter = new AtomicInteger(1);

	public void firstSignal() throws InterruptedException {
		firstLock.lockInterruptibly();
		try {
			while (counter.get() != 1) {
				System.out.println("First Signal Red....");
				firstOn.await();
			}
			System.out.println("First Signal Green for 1 second.");
			Thread.sleep(1000);
			counter.incrementAndGet();
		} finally {
			firstLock.unlock();
		}
		System.out.println("Signal pass to second");
		signalSecond();
	}

	public void secondSignal() throws InterruptedException {
		secondLock.lockInterruptibly();
		try {
			while (counter.get() != 2) {
				System.out.println("Second Signal Red....");
				secondOn.await();
			}
			System.out.println("Second Signal Green for 1 second.");
			Thread.sleep(1000);
			counter.incrementAndGet();
		} finally {
			secondLock.unlock();
		}
		System.out.println("Signal pass to third");
		signalThird();

	}

	public void thirdSignal() throws InterruptedException {
		thirdLock.lockInterruptibly();
		try {
			while (counter.get() != 3) {
				System.out.println("Third Signal Red....");
				thirdOn.await();
			}
			System.out.println("Third Signal Green for 1 second.");
			Thread.sleep(1000);
			counter.incrementAndGet();
		} finally {
			thirdLock.unlock();
		}
		System.out.println("Signal pass to fourth");
		signalFourth();

	}

	public void fourthSignal() throws InterruptedException {
		fourthLock.lockInterruptibly();
		try {
			while (counter.get() != 4) {
				System.out.println("Fourth Signal Red....");
				fourthOn.await();
			}
			System.out.println("Fourth Signal Green for 1 second.");
			Thread.sleep(1000);
			counter.set(1);
		} finally {
			fourthLock.unlock();
		}
		System.out.println("Signal passes back to first");
		signalFirst();

	}

	public static void main(String[] args) {
		final TrafficSignalUsingLock trafficSignalUsingLock = new TrafficSignalUsingLock();
		final int count = 3;
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < count; i++) {
					try {
						trafficSignalUsingLock.firstSignal();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < count; i++) {
					try {
						trafficSignalUsingLock.secondSignal();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < count; i++) {
					try {
						trafficSignalUsingLock.thirdSignal();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t4 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < count; i++) {
					try {
						trafficSignalUsingLock.fourthSignal();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
