import java.util.Random;
import java.util.Scanner;

public class WaitNotifyExample {
	private void producer() {
		synchronized (this) {
			System.out.println("Producer running...");
			try {
				wait();
				System.out.println("Resumed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void consumer() {
		try {
			Scanner scanner = new Scanner(System.in);
			Thread.sleep(2000);
			
			synchronized (this) {
				System.out.println("Waiting for enter key...");
				scanner.nextLine();
				notify();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		WaitNotifyExample example = new WaitNotifyExample();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				example.producer();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				example.consumer();
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
