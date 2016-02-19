import java.util.LinkedList;
import java.util.Random;

public class WaitNotifyExample2 {
	
	private LinkedList<Integer> l1 = new LinkedList<Integer>();
	private final int MAX = 10;
	
	private Object lock = new Object();
	
	private void producer() throws InterruptedException {
		Random random = new Random();
		while(true) {
			synchronized (lock) {
				if(l1.size() == MAX) lock.wait();
				l1.add(random.nextInt(100));
				lock.notify();
			}	
		}
	}
	
	private void consumer() throws InterruptedException {
		while(true) {
			synchronized (lock) {
				if(l1.size() == 0) lock.wait();
				int val = l1.removeFirst();
				System.out.println("List size is: " + l1.size());
				System.out.println("Value is: " + val);
				lock.notify();
			}
			
			Thread.sleep(2000);
		}
	}
	
	public static void main(String[] args) {
		WaitNotifyExample2 example = new WaitNotifyExample2();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					example.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					example.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
