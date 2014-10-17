/*
@author: Paul Salmon
@title: CountDivisors

Supply an integer runtime parameter and we will find the number up to that, which has the most divisors.
Uses a multithreaded approach, threadsafe through the volatile keyword
Spawns x threads as detected by the runtime method availableProcessors() which returns the available logical cores.
*/

public class DivisorThread extends Thread{
	
	public void run(){
	
		int myNum = CountDivisors.curr++;
	
		while(myNum <= CountDivisors.countTo){
			int divisors = 2;//assume divisible by 1 and itself.
		
			for(int i = 2; i <= myNum/2; i++){//only need to count up to halfway point
				if(myNum%i==0){divisors++;}		
			}
		
			//mark new highest number of divisors. **takes larger number if tied.
			if (divisors >= CountDivisors.maxDivisors){CountDivisors.maxNum = myNum; CountDivisors.maxDivisors = divisors;}

			myNum = CountDivisors.curr++;
		}//end while
		
		CountDivisors.finished++;//report back that we are finished.
		
	}

}