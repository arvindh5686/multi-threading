import java.util.Scanner;

/**
 * 
 * @author abalak5
 * Use of volatile to avoid caching
 */
public class SynchExample1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Processor processor = new Processor();
		processor.start();
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		processor.shutdown(); 
	}

}

class Processor extends Thread {
	
	private volatile boolean isRunning = true;
	
	public void run() {
		while (isRunning) {
			System.out.println("hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		isRunning = false;
	}
}