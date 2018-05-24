package net.sycu.meade.business;

public class HelloWorld {
	
	public static void main(String[] args) {
		
	}
	private int i= 0;
	public void run(){
		while (i<100) {
			i++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println();
		}
	}
}