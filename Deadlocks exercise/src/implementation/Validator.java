package implementation;

//Assignment 1 
//Authors : Yulia Moshan - 319565610
//		   : Gil Pasi    - 206500936	
import java.util.Scanner;

public class Validator {
	public static int getValidNatural(String question, final int NUM_LIM) {
		int ret = 0;
		do ret = getValidInt(question, NUM_LIM);while(ret < 0 );
		return ret;
	}
	
	public static int getValidInt(String question , final int NUM_LIM) {
		String in ="" ;
		Scanner s = new Scanner(System.in);
		do {
			System.out.println(question);
			 in = s.nextLine();
		}while(!isValid(in , NUM_LIM) || toInt(in) >= NUM_LIM);
		
		
		int validated = toInt(in);
		
		return validated;
	}
	
	
	public static boolean isValid (String in , final int NUM_LIM) {
		if(in.length() == 0)
			return false;
		
		for(int i = 0 ;i < in.length(); i++) {	
			if(!Character.isDigit(in.charAt(i)))
				return false;
		}
		return true;
	}
	
    public static int toInt(String str){
        int val = 0;  
        try {
            val = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid String");
        }
        return val;
    }
	

}
