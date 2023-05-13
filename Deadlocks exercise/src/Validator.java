
//Assignment 1 
//Authors : Yulia Moshan - 319565610
//		   : Gil Pasi    - 206500936	
import java.util.Scanner;

public class Validator {
	
	public static int getValidData(String question) {
		String in ="" ;
		Scanner s = new Scanner(System.in);
		do {
			System.out.println(question);
			 in = s.nextLine();
		}while(!isValid(in));
		
		
		int validated = toInt(in);
		return validated;
	}
	
	
	public static boolean isValid (String in) {
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
