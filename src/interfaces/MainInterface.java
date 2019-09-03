package interfaces;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public interface MainInterface {
	
	public static String adminLoginFilePath = "src/TextFiless/AdminLogin.txt";
	public static String customersLoginFilePath = "src/TextFiles/CustomerLogin.txt";
	public static String customersFilePath = "src/TextFiles/Customers.txt";
	public static String ordersFilePath = "src/TextFiles/Orders.txt";
	public static String productsFilePath = "src/TextFiles/Products.txt";
	
	// Methods
	
	public static Boolean isValidInput(String enteredInt, int limit) {
		
		try{
			int numericEnteredValue = Integer.parseInt(enteredInt);
			if(numericEnteredValue > limit || numericEnteredValue < 0) {
				System.out.println("Invalid menu number entered. A valid menu option is required.");
				return false;
			} else return true;
			
        } catch(Exception exception) {
        	System.out.println("Invalid menu number entered. A valid menu option is required.");
        	return false;
        }
	}
	
	public static Boolean isUsernameAvailable(String username) {
		
		String oneLine = null;
		Boolean doesExist = true;
		
		try {
			
			FileReader customerFileReader = new FileReader(customersLoginFilePath);
			BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
			
			while((oneLine = customerBufferedReader.readLine()) != null) {
				
				String[] arrOfAdmin = oneLine.split("-");
				
				if(arrOfAdmin[0].equals(username)) {
						doesExist = false;
					}
            }//end of while
			
			customerBufferedReader.close();
			
		}catch(FileNotFoundException ex) {
			System.out.println("*Unable to open file " + ex.getMessage()+"*");
		}//end of FileNotFoundException
		catch(IOException ex) {
            System.out.println("*Error reading file " + ex.getMessage()+"*");
        }//end of IOException
		catch(NullPointerException ex) {
			System.out.println("*Error: "+ ex.getMessage()+"*");
		}//end of NullPointerException
		
		return doesExist;
	}
	
	
//	public static boolean isNumeric(String strNum) {
//	    try {
//	        double d = Double.parseDouble(strNum);
//	    } catch (NumberFormatException | NullPointerException nfe) {
//	        return false;
//	    }
//	    return true;
//	}
	
}
