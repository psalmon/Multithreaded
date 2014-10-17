/*
@author: Paul Salmon
@title: Count Divisors

Supply an integer runtime parameter and we will find the number up to that, which has the most divisors.
Uses a multithreaded approach, threadsafe through the volatile keyword
Spawns x threads as detected by the runtime method availableProcessors() which returns the available logical cores.
*/


public class CountDivisors{
	//The number of "logical" cores available.
    //Returns 8 on my hyperthreaded (virtual cores, doubles) quad core.
	final static int cores = Runtime.getRuntime().availableProcessors();
	static volatile int curr = 1;
	static volatile int maxNum = 1;//number with the max divisors
	static volatile int maxDivisors = 1;
	static int countTo;
	static int finished = 0;
	
	public static void main(String[] args){
	
		final long startTime = System.currentTimeMillis();
		countTo = Integer.parseInt(args[0]);
	
		for(int i=0; i < cores; i++){
			Thread thread = new DivisorThread();
			thread.start();
		}
		
		//wait for each thread to finish
		while(finished < cores){
			try{Thread.sleep(500);}//pause for half a second
			catch(InterruptedException E){System.out.println("Unhandled interrupted Exception...");}
			System.out.println(finished + " threads are completed ");
		}
		
		final long endTime = System.currentTimeMillis();
		
		System.out.println("The number with the largest divisors (up to " + countTo + ") is " + maxNum + " with " + maxDivisors + " divisors");
		System.out.println("Completed in " + (endTime-startTime) + " milliseconds");
	}

}