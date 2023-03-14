package myspringboot;

import java.util.concurrent.LinkedBlockingQueue;

public class Test {
	public static void main(String[] args) {
		LinkedBlockingQueue a=new LinkedBlockingQueue();
		System.out.println(a.remainingCapacity());
	}
}
