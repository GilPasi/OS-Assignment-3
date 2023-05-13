/*Authors:
 * Gil Pasi 	- 206500936
 * Heba Abu-Kaf - 323980441
 * */
package driver;
import java.util.Scanner;

import implementation.*;
public class Runner {
	public static void main(String [] args) {
		final int NO_LIMIT = Integer.MAX_VALUE;
		Scanner s = new Scanner(System.in);
		//=====Initialization====
		
		
//		Banker.getData();
		//__Pre-made mock values__
//		Banker.allocation = new int[][]{{4,1,1},{0,1,0},{1,1,2}};
//		Banker.available = new int[][]{{3,2,1}};
//		Banker.claim = new int[][]{{5,6,4},{4,4,4},{3,0,1}};
		
		Banker.allocation = new int[][]{{1,0,0,3},{2,0,0,2},{1,0,0,2}};
		Banker.available = new int[]{3,2,0};
		Banker.claim = new int[][]{{2,0,0,3},{4,4,4,4},{3,0,1,3}};

		

		System.out.println("Allocation table: ");
		Banker.printTable("allocation");	
		
		System.out.println("Available array: ");
		Banker.printTable("available");	
		
		System.out.println("Claim table: ");
		Banker.printTable("claim");	
		
		//=====Task 1 check safety====
		if(Banker.isSafe())
			System.out.println("This condition is safe!");
		else
			System.out.println("Notice! deadlock is possible");
		
		//=====Task 2 find minimal number of additional resources in order to avoid deadlock.====
		int resourceNum = Validator.getValidInt("Please enter the resource's serial number "
				+ "in the range 1 - " + (Banker.available.length + 1), Banker.available.length+ 1) ;
		System.out.println("The additional needed resources are " + Banker.minDeadlockAvoidance(resourceNum));
		
		//=====Task 3 check safety====
		
		

		
	}

}
