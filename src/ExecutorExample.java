import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		for(int i=0; i<5; i++) {
			service.submit(new Processor1(i));
		}
		
		service.shutdown();
		System.out.println("All tasks submitted");
		
		try {
			service.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Processor1 implements Runnable {
	private int id;
	
	public Processor1(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("Starting task: " + id);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finishing task: " + id);
	}
}