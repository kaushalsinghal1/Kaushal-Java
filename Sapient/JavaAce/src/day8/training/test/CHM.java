package day8.training.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CHM {
	static final int LIMIT = 1000000;

	public static void main(String[] args) throws InterruptedException {
		final ExecutorService service = Executors.newCachedThreadPool();
		int tcount = 1000;
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch end = new CountDownLatch(tcount);
		AtomicInteger integer = new AtomicInteger(0);
		Map<Integer, Object> map = new ConcurrentHashMap<>(LIMIT, 0.75f, 64);
	//	Collections.synchronizedMap(map);

		for (int i = 0; i < tcount; i++) {
			service.submit(new Write(integer, map, start, end));
			service.submit(new Read(map, start));
		}
		long startTime = System.currentTimeMillis();
		start.countDown();
		end.await();
		service.shutdownNow();
		System.out.println("Size" + map.size());
		long endTime = System.currentTimeMillis();
		System.out.println("------Statistics--------");
		System.out.println("Insert Element: " + LIMIT);
		System.out.println("Thread Count: " + tcount);
		System.out.println("Timetaken in Millis: " + (endTime - startTime));

	}

	static class Read implements Runnable {
		private Map<Integer, Object> map;
		private CountDownLatch start;

		public Read(Map<Integer, Object> map, CountDownLatch start) {
			this.map = map;
			this.start = start;
		}

		@Override
		public void run() {
			//while (true) {
				try {
					start.await();
					Set<Integer> keyset = map.keySet();
					for (Iterator<Integer> iterator = keyset.iterator(); iterator
							.hasNext();) {
						Integer key = iterator.next();

						map.get(key);
					//	Thread.sleep(2);
						//System.out.println(Thread.currentThread() + " size"+ map.size());
					}
				} catch (InterruptedException e) {

				}
			//}
		}
	}

	static class Write implements Runnable {
		private Map<Integer, Object> map;
		private CountDownLatch latch;
		private AtomicInteger integer;
		private CountDownLatch end;

		public Write(AtomicInteger integer, Map<Integer, Object> map,
				CountDownLatch latch, CountDownLatch end) {
			this.map = map;
			this.latch = latch;
			this.integer = integer;
			this.end = end;
		}

		@Override
		public void run() {
			//while (true) {
				try {
					latch.await();
					int key = integer.incrementAndGet();
					while (key <= LIMIT) {
						map.put(key, new Object());
						key = integer.incrementAndGet();
					}
					end.countDown();
				} catch (InterruptedException e) {

				}

			//}
		}
	}
}
