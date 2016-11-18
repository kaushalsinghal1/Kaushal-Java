package com.ace.training.usecase;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierImpl {
	private AtomicInteger count;
	private int parties = 0;
	private Runnable barrierCommad;
	private boolean broken = false;
	private int reset = 0;

	public CyclicBarrierImpl(int parties, Runnable barrierCommad) {
		this.parties = parties;
		this.count = new AtomicInteger(parties);
		this.barrierCommad = barrierCommad;
	}

	private synchronized int doBarrier() throws BrokenBarrierException,
			InterruptedException {

		int index = count.decrementAndGet();
		if (broken) {
			throw new BrokenBarrierException();
		} else if (Thread.interrupted()) {
			broken = true;
			throw new InterruptedException();
		} else if (index == 0) {
			reset();
			notifyAll();
			if (barrierCommad != null) {
				barrierCommad.run();
			}
		} else {
			int r = reset;
			while (true) {
				try {
					wait();
				} catch (InterruptedException e) {
					if (r == reset) {
						broken = true;
						notifyAll();
						throw e;
					} else {
						Thread.currentThread().interrupt();
					}
				}
				if (broken) {
					throw new BrokenBarrierException(index + "");
				} else if (r != reset) {
					return index;
				}
			}
		}
		return 0;

	}

	private void reset() {
		reset++;
		broken = false;
		count = new AtomicInteger(parties);

	}

	public void await() throws InterruptedException, BrokenBarrierException {
		doBarrier();
	}
	// public void await() throws InterruptedException {
	// lock.lock();
	// try {
	// count--;
	// if (count == 0) {
	// reset();
	// condition.signalAll();
	// barrierCommad.run();
	// } else {
	// condition.await();
	// }
	// } finally {
	// lock.unlock();
	// }
	// }
}
