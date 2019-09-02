package customer;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer {
	
	private String customerID;
	private String customerName;
	private String customerAddress;
	private String customerPhone;
	
	private String password;
	
	private static String customersFilePath = "src/TextFiles/Customers.txt";
	private static String customersLoginFilePath = "src/TextFiles/CustomerLogin.txt";
	
	private static Scanner input = new Scanner(System.in);
	Writer output;
	
	//Constructor
	public Customer(String username) {
		readAndSetCustomerValues(username);
	}
	
	public Customer(String username, String fullName, String address, String phone, String password) {
		
		this.customerID = username;
		this.customerName = fullName;
		this.customerAddress = address;
		this.customerPhone = phone;
		this.password = password;
		
		//writeNewUserToFile();
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
	private void readAndSetCustomerValues(String username) {
		
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
	public void showMainMenu() {
		
		int option = 0;
		
		while(!(option == 2 || option == 1)) {
			
			try {
				System.out.println("\n\n-- MENU --\n"
						+ "Enter 1 to Place a new order\n"
						+ "Enter 2 to view your orders\n\n"
						+ "Enter your choice: ");
				
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
				myOrdersMenu();
				break;
			default:
				System.out.println("Default is running");
				break;
			}
		
	}//end of show menu
	
	private void myOrdersMenu() {
		
		int option = 3;
		
		while(!(option == 2 || option == 1 || option == 0)) {
			
			try {
				System.out.println("\n\n-- MENU --\n"
						+ "Enter 1 to search orders\n"
						+ "Enter 2 to see all your orders\n"
						+ "----\n"
						+ "Enter 0 to go back to main menu\n\n"
						+ "Enter your choice: ");
				option = input.nextInt();
				
				if(option > 2 || option < 0) {
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
		case 0:
			showMainMenu();
			break;
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
	}
	
	public void writeNewUserToFile() {
		
		try {
			output = new BufferedWriter(new FileWriter(customersFilePath, true));
			output.write(customerID+"-"
					+customerName+"-"
					+customerAddress+"-"
					+customerPhone+"\n");
			
//			//output.append(customerID+"-"
//					+customerName+"-"
//					+customerAddress+"-"
//					+customerPhone+"\n");
			
			output.close();
			
			output = new BufferedWriter(new FileWriter(customersLoginFilePath, true));
			output.write(customerID+"-"
					+password+"\n");
			
			output.close();
			
			System.out.println("*Successfully Added New User*");
			
			
		} catch(IOException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
}
