package com.ace.training.usecase;

import java.util.concurrent.TimeUnit;

public class CountDownLatchImpl {
	private int counter = 0;

	public CountDownLatchImpl(int counter) {
		this.counter = counter;
	}

	public synchronized void countDown() {
		if (counter > 0)
			counter--;

		if (counter == 0) {
			notifyAll();
		}
	}

	public int getCounter() {
		return counter;
	}

	public boolean doWait(long time, TimeUnit unit) throws InterruptedException {
		long milis = TimeUnit.MILLISECONDS.convert(time, unit);
		return doCounyterWait(true, milis);
	}

	public void doWait() throws InterruptedException {
		doCounyterWait(false, 0);
	}

	private synchronized boolean doCounyterWait(boolean timed, long timeMilis) throws InterruptedException {
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
		if (timed && timeMilis <= 0) {
			notifyAll();
			return false;
		}
		if (counter == 0) {
			notifyAll();
			return true;
		} else {
			long startTime = System.currentTimeMillis();
			try {
				if (timed) {
					wait(timeMilis);
				} else {
					wait();
				}
			} catch (InterruptedException e) {
				notifyAll();
				throw e;
			}
			if (timed) {
				if (timeMilis - (System.currentTimeMillis() - startTime) <= 0) {
					notifyAll();
					return false;
				}
			}
		}
		return true;

	}

}
