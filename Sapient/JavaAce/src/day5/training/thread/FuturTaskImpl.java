package day5.training.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FuturTaskImpl<V> implements Future<V> {

//	public FuturTaskImpl(Callable<V> task) {
//		super(task);
//	}
//
//	public FuturTaskImpl(Runnable task, V val) {
//		super(task, val);
//	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		return null;
	}

	@Override
	public boolean cancel(boolean arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(long arg0, TimeUnit arg2) throws InterruptedException, ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

}
