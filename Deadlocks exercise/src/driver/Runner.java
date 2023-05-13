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
		initialize();
		int resourceNum = Validator.getValidInt("Please enter the resource's serial number "
				+ "in the range 1 - " + (rCount + 1),rCount + 1) ;
		System.out.println("The additional needed resources are " + Banker.minDeadlockAvoidance(resourceNum));
		
		//=====Task 3 check safety====
		initialize();
		int processNum = Validator.getValidInt("Please enter a process number in range " 
		+ 1 +"-" + pCount,pCount) ;
		
		Integer[] pClaims = new Integer [rCount];//Vertical array
		Banker.fillTable(pClaims);
		
		//Assert changes
		for(int i = 0 ; i < rCount ; i ++ )
			Banker.claim[i][processNum - 1] = pClaims[i];
		
		//Refill according to the user's new demands.
		if(Banker.examineResourcesRequest(processNum, pClaims))
			System.out.println("The request accepted");
		else 
			System.out.println("This request rejected");
		
	}
	
	
	public static void initialize() {		
				Banker.getData();
		
		//Reboot counts

		
		
		//Uncomment for pre-made values
		//			||
		//			||
		//			\/
				
				
				
//		Banker.available = new Integer[]{3, //A
//										 2, //B
//										 1};//C
//		
//		
//										   //P1 P2 P3 P4
//		Banker.allocation = new Integer[][]{{1, 0, 0, 3}, //A
//											{2, 0, 0, 2}, //B
//											{1, 0, 0, 2}};//C
//
//									  //P1 P2 P3 P4
//		Banker.claim = new Integer[][]{{2, 0, 0, 3}, //A
//									   {4, 4, 4, 4}, //B
//									   {3, 0, 1, 3}};//C
				
				
									   
		pCount = Banker.claim[0].length ; 
		rCount = Banker.claim.length;
									   
		System.out.println("Allocation table: ");
		Banker.printTable("allocation");	
		
		System.out.println("Available array: ");
		Banker.printTable("available");	
		
		System.out.println("Claim table: ");
		Banker.printTable("claim");	

		
	}
	

}
