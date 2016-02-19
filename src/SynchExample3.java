import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author abalak5
 * Use of synchronized block
 * synchronized method locks the entire object so only one synchronized method is active in a given time if there are two threads like the example below
 * so use synchronized block instead
 * 
 * check this doc for synchronization in static methods
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
 */
public class SynchExample3 {

	private List<Integer> l1 = new ArrayList<Integer>();
	private List<Integer> l2 = new ArrayList<Integer>();
	private Random random = new Random();
	
	public void addToListOne() {
		synchronized(l1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			l1.add(random.nextInt(100));
		}
	}
	
	public void addToListTwo() {
		synchronized(l2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			l2.add(random.nextInt(100));
		}
	}
	
	public void process() {
		for(int i=0; i<1000; i++) {
			addToListOne();
			addToListTwo();
		}
	}
	
	public static void main(String[] args) {
		SynchExample3 example = new SynchExample3();	
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				example.process();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				example.process();
			}
		});
		
		long start = System.currentTimeMillis();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken: " + (end - start));
		System.out.println("List1: " + example.l1.size());
		System.out.println("List2: " + example.l2.size());
	}

}
