package ProducerConsumerProblem;

public class Consumer extends Thread {
	 PCBuffer pcb;	
	  int result;
	  public Consumer(PCBuffer pcb) {
	    this.pcb=pcb;
	  }
	  public void run() {
	    for(int i=0;i<10;i++) {
	      result = pcb.get();
	      System.out.println("C1 got : " + result);
	      try {
	        sleep((int)(Math.random() * 100));
	      } 
	      catch (InterruptedException e) { 
	      }
	    }
	  }
}
