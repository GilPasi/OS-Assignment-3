/*Authors:
 * Gil Pasi 	- 206500936
 * Heba Abu-Kaf - 323980441
 * */
package implementation;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class Banker {
	final static int  NULL = -1 ;
	final static int NO_LIMIT = Integer.MAX_VALUE;

	public static Integer [][] claim;
	public static Integer [][] allocation;
	public static Integer [] available;
	
	
	
	
	
	//============================question's  Algorithms==================================

	public static boolean isSafe() {
		Queue<Integer []> leftDemands = intArrayToQueue(claim);//Currently all proccess are not treated
		Queue<Integer []> leftAssignment = intArrayToQueue(allocation);
		
		int prevLeftDemandsSize = leftDemands.size();
		leftDemands.add(null);//To signify the start/ finish of a circular queue
		while(leftDemands.size() > 1) {
			Integer [] curDemand = leftDemands.remove();
			Integer [] curAssignment = leftAssignment.remove();
			
			if(curDemand == null) {
				/*If end of queue is reached and there was no change
				in the queue size, essentially no process was released.*/
				if(prevLeftDemandsSize == leftDemands.size())
					return false;
				else {
					leftDemands.add(curDemand);
					prevLeftDemandsSize = leftDemands.size();
					continue;
				}
			}
			if(!approveAllocation(curDemand , curAssignment)) {
				leftDemands.add(curDemand);
				leftAssignment.add(curAssignment);
			}
			else
				redeemResources(curDemand);
			
		}
		return true;		
	}
	public static int minDeadlockAvoidance(int rn) {//rn = resource number
		/**This method works very similarly to the isSafe method.
		 * It activates the banker's algorithm however there is a small change.
		 * Once a potential deadlock is reached, it will find the maximum need.
		 * if the maximum need is fulfilled, for sure, the rest will follow.
		 * */
		Queue<Integer []> leftDemands = intArrayToQueue(claim);//Currently all proccess are not treated
		Queue<Integer []> leftAssignment = intArrayToQueue(allocation);
		
		int prevLeftDemandsSize = leftDemands.size();
		leftDemands.add(null);//To signify the start/ finish of a circular queue
		while(leftDemands.size() > 1) {
			Integer [] curDemand = leftDemands.remove();
			Integer [] curAssignment = leftAssignment.remove();
			
			if(curDemand == null) {
				/*If end of queue is reached and there was no change
				in the queue size, essentially no process was released.*/
				if(prevLeftDemandsSize == leftDemands.size()) {
					
					//________________NEW_____________________
					int maxNeed = Integer.MIN_VALUE;
					while (!leftDemands.isEmpty() && !leftAssignment.isEmpty()) {
						int need = leftDemands.remove()[rn] - leftAssignment.remove()[rn];
						maxNeed = Math.max(need, maxNeed);
					}
					return maxNeed - available[rn];
					//________________________________________

				}
					
				else {
					leftDemands.add(curDemand);
					prevLeftDemandsSize = leftDemands.size();
					continue;
				}
			}
				if(!approveAllocation(curDemand , curAssignment)) {
					leftDemands.add(curDemand);
					leftAssignment.add(curAssignment);
				}
				else
					redeemResources(curDemand);
					
		}
		return 0;/*If there is no potential for 
				   a deadlock, there is no need for 
				   Extra resources */
	}
		
	public static boolean approveAllocation (Integer [] demand , Integer [] assignment) {
		for(int i = 0; i < available.length ; i++ )
			if(demand[i] > available[i] + assignment[i])
				return false;
		return true;
	}
	
	
	//==================================Aid functions==================================
	
	private static void redeemResources (Integer [] assignment) {
		for(int i = 0; i < available.length; i++)
			available[i] += assignment[i];
	}
	
	
	private static LinkedList<Integer> intArrayToQueue(Integer [] arr) {
		LinkedList<Integer> queue = new LinkedList<Integer>();

		for (int i = 0; i < arr.length; i++) {
		    queue.add(arr[i]);
		}
		return queue;
	}
	
	private static LinkedList<Integer []> intArrayToQueue(Integer [][] arr) {
		LinkedList<Integer []> queue = new LinkedList<Integer []>();

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
		claim = new Integer [validProcCount][validResCount];
		allocation = new Integer [validProcCount][validResCount];
		
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
				matrix[i][j] = Validator.getValidNatural("for P" + (i+1) + " " + (char)('A' + j) , NO_LIMIT);
			}
		}
		return matrix;
	}
	public static Integer [] fillTable(Integer [] list) {
		for(int i = 0; i < list.length ; i++) 
			list[i] = Validator.getValidInt("for " +  (char) ('A' +  i) , NO_LIMIT);
		
		return list;
	}
	
	public static void printTables() {
		System.out.println("\tAllocation\t\t\tClaim\t\t\tAvailable");
		
		int leftTables = 3;
		while(leftTables > 0) {
			for(int j = 0 ;j < allocation[0].length;j++) 
				System.out.print("\t" +(char) ('A' + j));
			System.out.print("|");
			leftTables --;
		}
			
			
		for(int i = 0; i < allocation.length;i++) {
			System.out.print("\nP" + (i+1) + "  ");
			
			for(int j = 0 ;j < allocation[0].length;j++) 
				System.out.print("\t" + allocation[i][j]);
			System.out.print("|");
			
			for(int j = 0 ;j < allocation[0].length;j++) 
				System.out.print("\t" + claim[i][j]);
			System.out.print("|");

			
			for(int j = 0 ;j < allocation[0].length;j++) 
				System.out.print("\t" +available[j]);
			System.out.print("|");

			
		}
		System.out.println("\n");
	}	
}
