package ProducerConsumerProblem;

public class PCBuffer {
	int content;
	  boolean available = false;
	  public synchronized int get() {
	    while(!available) {
	      try {
	        wait(); 
	      } 
	      catch (InterruptedException e) {
	      }
	    }
	    available=false;
	    notifyAll();
	    return content;
	  }
	  
	  public synchronized void put(int i) {
	    while(available) {
	      try {
	       	wait(); 
	      } 
	      catch(InterruptedException e) {
	      }
	    }
	    available=true;
	    content=i;
	    notifyAll();
	  }
}
