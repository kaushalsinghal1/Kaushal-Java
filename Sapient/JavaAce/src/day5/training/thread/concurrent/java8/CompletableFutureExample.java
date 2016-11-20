package day5.training.thread.concurrent.java8;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
	public static void main(String[] args) throws InterruptedException {
		// Callable<Double> task = () -> {
		// Thread.sleep(2000);
		// return 2000D;
		// };
		//
		// Supplier<Double> supplier = new Supplier<Double>() {
		//
		// @Override
		// public Double get() {
		// return null;
		// }
		// };
		CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Future running..");
			// try {
			//
			// //Thread.sleep(1000);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = 2;
			// if (i == 2) {
			// throw new Exception();
			// }
			System.out.println("Sleep complete");
			return 100D;
		});// .exceptionally(e-> new Object());
		Thread.currentThread().setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread arg0, Throwable arg1) {
				System.out.println("Uncaught Exception Handler");

			}
		});
		future.completeExceptionally(new Exception());
		future.thenAccept(d -> System.out.println("Completed" + d)).exceptionally(e -> {
			System.out.println("Exceptoom" + e);
			return null;
		});
		// future.exceptionally(e-> System.out.println("Exceptionalll"+e));
		// .completeExceptionally(e -> {
		// System.out.println("Exception" + e);
		// });
		future.join();
		Thread.sleep(2000);
		System.out.println("Main Thread Completed");

	}

}
