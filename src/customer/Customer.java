package customer;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer {
	
	String customerID;
	String customerName;
	String customerAddress;
	String customerPhone;
	
	private static String customersFilePath = "src/TextFiles/Customers.txt";
	private static Scanner input = new Scanner(System.in);
	
	//Constructor
	public Customer(String username) {
		
		setCustomerValues(username);
		
	}
	
	//Getter
	public String getCustomerID() {
		return customerID;
	}
	
	public String getcustomerName() {
		return customerName;
	}
	
	public String getcustomerAddress() {
		return customerAddress;
	}
	
	public String getcustomerPhone() {
		return customerPhone;
	}
	
	//Setter
	private void setCustomerValues(String username) {
		
		try {
			FileReader customerFileReader = new FileReader(customersFilePath);
			BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
			String oneLine = null;
			
			
			while((oneLine = customerBufferedReader.readLine()) != null) {
				
				String[] arrOfUser = oneLine.split("-");
				if(arrOfUser[0].equals(username)) {
					customerID = arrOfUser[0];
					customerName = arrOfUser[1];
					customerAddress = arrOfUser[2];
					customerPhone = arrOfUser[3];
					
				}
	        }//end of while
			
			customerBufferedReader.close();
			
		} catch(FileNotFoundException ex) {
			System.out.println("*Unable to open file '" + customersFilePath + "' " + ex.getMessage()+"*");
		}//end of FileNotFoundException
		catch(IOException ex) {
            System.out.println("Error reading file '" + customersFilePath + "' " + ex.getMessage()+"*");
        }//end of IOException
		catch(NullPointerException ex) {
			System.out.println("Error: "+ ex.getMessage()+"*");
		}//end of NullPointerException
		
		
	}
	
	//Methods
	public void showMenu() {
		
		int option = 0;
		
		while(!(option == 2 || option == 1)) {
			
			try {
				System.out.println("\n\n-- MENU --\n1. Place a new order\n2. My Orders\n\nEnter your choice (1 or 2): ");
				option = input.nextInt();
				
				if(option > 2 || option < 1) {
					System.out.println("Invalid menu number entered. A valid menu option is required.");
				}
				
			} catch (InputMismatchException ex) {
				System.out.println("Invalid menu number entered. A valid menu option is required.");
				if(input.hasNextInt()) {
					option = input.nextInt();
				} else {
					input.next();
					continue;
				}
				 //The error occurs here
			}
		}

			
		switch(option) {
			case 1:
				System.out.println("You have entered: "+option);
				break;
			case 2:
				System.out.println("You have entered: "+option);
				break;
			default:
				System.out.println("Default is running");
				break;
			}
		
	}//end of show menu
	

}
