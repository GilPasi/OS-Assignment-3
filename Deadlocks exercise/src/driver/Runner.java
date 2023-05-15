/*Authors:
 * Gil Pasi 	- 206500936
 * Heba Abu-Kaf - 323980441
 * */
package driver;
import implementation.*;
public class Runner {
	static int pCount,rCount;
	public static void main(String [] args) {	
		
		
		//=====Task 1 check safety====
		initialize();
		if(Banker.isSafe())
			System.out.println("This condition is safe!");
		else
			System.out.println("Notice! deadlock is possible");
		
		
		
		
		//=====Task 2 find minimal number of additional resources in order to avoid deadlock.====
		int resourceNum = Validator.getValidInt("Please enter the resource's serial number "
				+ "in the range 1 - " + (rCount + 1),rCount + 1) ;
		System.out.println("The additional needed resources are " + Banker.minDeadlockAvoidance(resourceNum));
		
		
		
		
		//=====Task 3 check safety====
		int processNum = Validator.getValidInt("Please enter a process number in range " 
		+ 1 +"-" + pCount,pCount) ;
		
		Integer[] pClaims = new Integer [rCount];
		System.out.println("For the process claim:");
		Banker.fillTable(pClaims);
		
		//Refill according to the user's new demands.
		if(Banker.approveAllocation(pClaims , Banker.allocation[processNum - 1]))
			System.out.println("Request accepted");
		else 
			System.out.println("Request rejected");
	}
	
	
	public static void initialize() {		
		Banker.getData();
		//Uncomment for pre-made values
		//			||
		//			||
		//			\/
				
				
				
//		Banker.available = new Integer[]{3,2,1,5};
//		
//		
//										   //A  B  D  D
//		Banker.allocation = new Integer[][]{{1, 0, 0, 3}, //P1
//											{2, 2, 3, 2}, //P2
//											{1, 0, 0, 2}};//P3
//
//									  //A  B  C  D
//		Banker.claim = new Integer[][]{{2, 0, 0, 3}, //P1
//									   {4, 4, 4, 4}, //P2
//									   {3, 0, 1, 3}};//P3
		
//  needed
//        |  A B C D
//   _____|__________
//   P1   | 1 0 0 0
//	 P2   | 2 2 1 2 
//	 P3   | 2 0 1 1
									   
				
				
									   
	   //Reboot counts
		pCount = Banker.claim.length ; 
		rCount = Banker.claim[0].length;
		Banker.printTables();
									   
	}
}
