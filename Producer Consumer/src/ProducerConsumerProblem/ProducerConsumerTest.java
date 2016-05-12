package ProducerConsumerProblem;

public class ProducerConsumerTest {

	public static void main(String[] args) {
		PCBuffer pcb = new PCBuffer();
		Producer p1 = new Producer(pcb);
	    Consumer c1 = new Consumer(pcb);
	    p1.start();
	    c1.start();
	}

}
