/*Authors:
 * Gil Pasi 	- 206500936
 * Heba Abu-Kaf - 323980441
 * */
package implementation;
import java.util.LinkedList;
import java.util.Queue;

public class Banker {
	final static int  NULL = -1 ;
	final static int NO_LIMIT = Integer.MAX_VALUE;

	public static Integer [][] claim;
	public static Integer [][] allocation;
	public static Integer [] available;
	
	
	public static boolean examineResourcesRequest(int pn,Integer [] processClaims) {
		//pn = Process number , rq = Array of requests
		/**This method find if a given request is going to be approved in any time
		 * on the run.It is very similar to the isSafe() method.
		 * the only difference is the focus on a specific process.
		 * This will be achieved by comparing the result to the given process.
		 *
		 * Logic: if we remove the request claim that means that it didn't cause
		 * a deadlock. Therefore there is no need to check the rest of the claims
		 * for the current resource - break the loop.
		 *  If a deadlock is reached and the loop has not break already,
		 *  that means that the process request has something to do with 
		 *  a potential deadlock.Therefore the resources will not be allocated.
		 *  
		 * */
		//________ADDDED_________
		pn = pn - 1 ;//Adjust to indexes
		//_______________________

		//Banker algorithm
		final int RES_AMOUNT = allocation.length ;
		
		//Step 1: take check a resource 
		for(int i = 0 ; i < RES_AMOUNT; i++) {
			Queue<Integer> leftClaims = intArrayToQueue(claim[i]);//Currently all processes are not treated
			Queue<Integer> leftAllocations = intArrayToQueue(allocation[i]);

			//Sign the start of a circular queue
			leftClaims.add(NULL);
			int prevLeftClaims = leftClaims.size();
			
			while(leftClaims.size() > 1) {
				Integer currentClaim = leftClaims.remove();
				
				

				//Step 2 : check if no change was made == deadlock
				if(currentClaim == NULL) {
					leftClaims.add(currentClaim);
					if(prevLeftClaims == leftClaims.size())//Sign for no change 
						return false;//Deadlock reached
					prevLeftClaims = leftClaims.size();//Update before the next cycle
					continue;					
				}
				
				//Step 3: try to allocate resources to the current process
				Integer currentAllocation = leftAllocations.remove();
				
				//Failure, return the process to queue
				if(currentClaim.intValue() > available[i] + currentAllocation) {
					leftClaims.add(currentClaim);
					leftAllocations.add(currentAllocation);
				}
				//Success, redeem the process'es previous allocation
				else {
					//________ADDDED_________
					if(currentClaim == processClaims[i])//Compare addresses
						break;
					//_______________________

					
					available[i] += currentAllocation.intValue();
				}
			}
		}

		return true;
	}
	
	
	
	public static int minDeadlockAvoidance (int rn) {//rn = Resource number
		/**This method works very similarly to the isSafe method.
		 * It activates the banker's algorithm however there is a small change.
		 * Instead of alerting about a deadlock it will increase 
		 * the minimum added resources. 
		 * */
		//________ADDDED_________
		rn = rn - 1 ;//Adjust to indexes
		//_______________________

		
		int minR = 0;//By default no extra resources required
		//Banker algorithm
		final int RES_AMOUNT = allocation.length ;
		
		//Step 1: take check a resource 
		
		Queue<Integer> leftClaims = intArrayToQueue(claim[rn]);//Currently all proccess are not treated
		Queue<Integer> leftAllocations = intArrayToQueue(allocation[rn]);

		//Sign the start of a circular queue
		leftClaims.add(NULL);
		int prevLeftClaims = leftClaims.size();
		
		while(leftClaims.size() > 1) {
			Integer currentClaim = leftClaims.remove();
			

			//Step 2 : check if no change was made == deadlock
			if(currentClaim == NULL) {
				leftClaims.add(currentClaim);
				if(prevLeftClaims == leftClaims.size())//Sign for no change 
					//________ADDDED_________
					minR++ ;//Deadlock reached
					//________ADDDED_________

				prevLeftClaims = leftClaims.size();//Update before the next cycle
				continue;					
			}
			
			//Step 3: try to allocate resources to the current process
			Integer currentAllocation = leftAllocations.remove();
			
			//Failure, return the process to queue
			if(currentClaim.intValue() > available[rn] + currentAllocation + minR) {
				leftClaims.add(currentClaim);
				leftAllocations.add(currentAllocation);
			}
			//Success, redeem the process'es previous allocation
			else
				available[rn] += currentAllocation.intValue();
		}
		return minR;
		
	}
	
	public static boolean isSafe () {
		//Banker algorithm
		final int RES_AMOUNT = allocation.length ;
		
		//Step 1: take check a resource 
		for(int i = 0 ; i < RES_AMOUNT; i++) {
			Queue<Integer> leftClaims = intArrayToQueue(claim[i]);//Currently all proccess are not treated
			Queue<Integer> leftAllocations = intArrayToQueue(allocation[i]);

			//Sign the start of a circular queue
			leftClaims.add(NULL);
			int prevLeftClaims = leftClaims.size();
			
			while(leftClaims.size() > 1) {
				Integer currentClaim = leftClaims.remove();
				

				//Step 2 : check if no change was made == deadlock
				if(currentClaim == NULL) {
					leftClaims.add(currentClaim);
					if(prevLeftClaims == leftClaims.size())//Sign for no change 
						return false;//Deadlock reached
					prevLeftClaims = leftClaims.size();//Update before the next cycle
					continue;					
				}
				
				//Step 3: try to allocate resources to the current process
				Integer currentAllocation = leftAllocations.remove();
				
				//Failure, return the process to queue
				if(currentClaim.intValue() > available[i] + currentAllocation) {
					leftClaims.add(currentClaim);
					leftAllocations.add(currentAllocation);
				}
				//Success, redeem the process'es previous allocation
				else
					available[i] += currentAllocation.intValue();
			}
		}

		return true;
	}
	
	private static LinkedList<Integer> intArrayToQueue(Integer [] arr) {
		LinkedList<Integer> queue = new LinkedList<Integer>();

		for (int i = 0; i < arr.length; i++) {
		    queue.add(arr[i]);
		}
		return queue;
	}
	
	public static void getData() {		
		int validProcCount = Validator.getValidInt("Please enter the processes quantity:",NO_LIMIT);
		int validResCount = Validator.getValidInt("Please enter the resources type quantity:",NO_LIMIT);

		//Now initiate matrices
		available = new Integer [validResCount];//The available table is actually  1-dimensional
		claim = new Integer [validResCount][validProcCount];
		allocation = new Integer [validResCount][validProcCount];
		
		System.out.println("Available array");
		fillTable( available);
		System.out.println("Claim table");
		fillTable(claim);
		System.out.println("Allocation table");
		fillTable(allocation);
		
	}
	
	public static Integer [][] fillTable (Integer [][] matrix) {
		for(int i = 0; i < matrix.length ; i++) {
			for(int j = 0; j < matrix[0].length ; j++) {
				matrix[i][j] = Validator.getValidInt("for P" + (i+1) + " " + (char)('A' + j) , NO_LIMIT);
			}
		}
		return matrix;
	}
	public static Integer [] fillTable(Integer [] list) {
		for(int i = 0; i < list.length ; i++) 
			list[i] = Validator.getValidInt("for " +  (char) ('A' +  i) , NO_LIMIT);
		
		return list;
	}
	
	public static void printTable (String matName) {
		
		Integer [][] matrix = null;
		Integer [] array = null;
		
		switch (matName) {
		case "available": 
			array = available;
			break;
		case "claim": 
			matrix = claim;
			break;
		case "allocation": 
			matrix = allocation;
			break;
		default:
			System.out.println("No such a table");
			return;
		
		}
		if(matrix != null) {
			System.out.println("r/p #################################");
			for(int i = 0; i < matrix.length ; i++) {
				System.out.print("#");				
				for(int j = 0; j < matrix[0].length ; j++) 
					System.out.print( matrix[i][j] + "\t" );

				
				System.out.println();
			}	
		}
		
		if(array != null) {
			for(int num : array)
				System.out.print(num + "\t");
			System.out.println();
			
		}
	}
	
	
	

}
