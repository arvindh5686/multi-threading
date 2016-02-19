/**
 * 
 * @author abalak5
 * Use of synchronized method
 * every object has an intrinsic lock or the mutex which only one thread can acquire
 * 
 * check this doc for synchronization in static methods
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
 */
public class SynchExample2 {

	private int count = 0;
	
	public synchronized void increment() {
		count++;
	}
	
	public void run() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					increment();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(count);
	}
	
	public static void main(String[] args) {
		SynchExample2 example = new SynchExample2();
		example.run();	
	}

}
