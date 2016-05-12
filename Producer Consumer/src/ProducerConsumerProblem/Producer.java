package ProducerConsumerProblem;

public class Producer extends Thread {
	 PCBuffer pcb;
	  public Producer(PCBuffer pcb) {
	    this.pcb=pcb; 
	  }
	  public void run() {
	    for(int i=0;i<10;i++) {
	      pcb.put(i);
	      System.out.println("P1 put : " + i);
	    } 
	  }
}
