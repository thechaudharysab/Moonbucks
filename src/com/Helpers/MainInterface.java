package com.Helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface MainInterface {
	
	public static String adminLoginFilePath = "src/TextFiles/AdminLogin.txt";
	public static String customersLoginFilePath = "src/TextFiles/CustomerLogin.txt";
	public static String customersFilePath = "src/TextFiles/Customers.txt";
	public static String ordersFilePath = "src/TextFiles/Orders.txt";
	public static String orderItemsFilePath = "src/TextFiles/OrderItems.txt";
	public static String productsFilePath = "src/TextFiles/Products.txt";
	
	// Methods
	
	public static Boolean isValidIntInput(String enteredVal, int limit) {
		
		try{
			int numericEnteredValue = Integer.parseInt(enteredVal);
			
			//System.out.println(numericEnteredValue+" > "+limit+" < 0\n");
			//11 > 1 and 11 < 0
			if (numericEnteredValue == -1) {
				return false;
			} else if(numericEnteredValue > limit || numericEnteredValue < 0) {
				System.out.println("Invalid menu number entered. A valid menu option is required.");
				return false;
			} else return true;
			
        } catch(Exception exception) {
        	System.out.println("Invalid menu number entered. A valid menu option is required.");
        	return false;
        }
	}
	
	public static Boolean isValidFloatInput(String enteredVal) {
		
		float convertedFloat = Float.parseFloat(enteredVal);
		
		try{			
			if(convertedFloat < 0.0) {
				System.out.println("Invalid price entered. A valid price is required like 4.65");
				return false;
			} else return true;
			
        } catch(Exception exception) {
        	System.out.println("Invalid price entered. A valid price is required like 3.36.");
        	return false;
        }
	}
	
	public static Boolean nameAlreadyExist(Type type, String phrase) throws IOException {
		
		String filePath = null;
		Boolean doesExist = false;
		String oneLine = null;
		
		switch(type) {
		
		case CUSTOMER:
			filePath = customersFilePath;
			break;
		case ORDER:
			filePath = ordersFilePath;
			break;
		case PRODUCT:
			filePath = productsFilePath;
			break;
		}//end of switch
		
		try {
			FileReader customerFileReader = new FileReader(filePath);
			BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
			
			while((oneLine = customerBufferedReader.readLine()) != null) {
				
				String[] arrOfAdmin = oneLine.split("-");
				if(arrOfAdmin[1].toLowerCase().equals(phrase.toLowerCase())) {
					System.out.println("\nAlready Exist!\n");
					doesExist = true;
					break;
				}
				
			}// while
			
			customerBufferedReader.close();
		} catch(Exception ex) {
			System.out.println("*Unable to open file " + ex.getMessage()+"*");
		}
		
		return doesExist;
	}
	
	public static int generateID(Type type) throws IOException {
		
		String filePath = null;
		
		switch(type) {
		
		case CUSTOMER:
			filePath = customersFilePath;
			break;
		case ORDER:
			filePath = ordersFilePath;
			break;
		case PRODUCT:
			filePath = productsFilePath;
			break;
		}//end of switch
		
			FileReader customerFileReader = new FileReader(filePath);
			BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
			String currentLine = null;
			String lastLine = null;
			
			while((currentLine = customerBufferedReader.readLine()) != null) {
				lastLine = currentLine;
			}
			customerBufferedReader.close();
			
			if(lastLine != null) {
				String[] arrOfLastLine = lastLine.split("-");
				int intOfId = Integer.parseInt(arrOfLastLine[0]);
				return intOfId+1;
			} else {
				return 1;
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
			
		}catch(Exception ex) {
			System.out.println("*Unable to open file " + ex.getMessage()+"*");
		}
		
		return doesExist;
	}

	public static String getDateTimeNow() {
		
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   return dtf.format(now); 
		
	}
//	public static boolean isNumeric(String strNum) {
//	    try {
//	    	
//	    	Double.parseDouble(strNum);
//	    } catch (NumberFormatException | NullPointerException nfe) {
//	        return false;
//	    }
//	    return true;
//	}
	
}
