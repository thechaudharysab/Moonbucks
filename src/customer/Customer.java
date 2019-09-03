package customer;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import interfaces.ClassInterface;
import interfaces.MainInterface;

public class Customer implements ClassInterface, MainInterface {
	
	private String customerID;
	private String customerName;
	private String customerAddress;
	private String customerPhone;
	
	protected String password;
	
	private static Scanner input = new Scanner(System.in);
	Writer output;
	
	//Constructors
	public Customer() {
		
	}
	
	public Customer(String username) {
		readAndSetCustomerValues(username);
	}
	
	public Customer(String username, String fullName, String address, String phone, String password) {
		
		this.customerID = username;
		this.customerName = fullName;
		this.customerAddress = address;
		this.customerPhone = phone;
		this.password = password;
		
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
	
	public void writeNewUserToFile() {
		
		try {
			output = new BufferedWriter(new FileWriter(customersFilePath, true));
			output.write(customerID+"-"
					+customerName+"-"
					+customerAddress+"-"
					+customerPhone+"\n");
			
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
	
	public Boolean editCustomerProfile(String username, String fullName, String address, String phone, String password) {
		
//		System.out.print("\n* Editing Customer *\nUsername: "+username
//							+"-- Name: "+fullName+"-- Address: "
//							+address+"-- Phone: "+phone+"\n");
//		
//		System.out.println("Name: ");
//		customerName = input.next();
//		customerName += input.nextLine();
//		
//		System.out.println("Address: ");
//		customerAddress = input.next();
//		customerAddress += input.nextLine();
//		
//		System.out.println("Phone: ");
//		customerPhone = input.next();
//		customerPhone += input.nextLine();
//		
//		System.out.println("Username (Case-sensitive): ");
//		customerID = input.next();
//		customerID += input.nextLine();
//		
//		while(MainClassInterface.isUsernameAvailable(customerID) == false) {
//			
//			System.out.println(customerID+" is already taken. Try another one! \nUsername: ");
//			customerID = input.next();
//			customerID += input.nextLine();
//			
//		}
//		System.out.println("Create Password: ");
//		password = input.next();
//		password += input.nextLine();
		
		return true;
	}

	
	@Override
	public void menu(Boolean isAdmin) {
		
		String option = "-1";
		
		if(isAdmin == false) {
			option = "0";
			
			while(MainInterface.isValidInput(option, 2) == false) {
				
					System.out.println("\n\n-- MAIN MENU --\n"
							+ "Enter 1 to Place a new order\n"
							+ "Enter 2 to view your orders\n----------\nEnter 0 to exit\n\n"
							+ "Enter your choice: ");
					
					option = input.next();
					
			} //end of while

			switch(option) {
				case "1":
					System.out.println("You have entered: "+option);
					break;
				case "2":
					myOrdersMenu();
					break;
				default:
					System.out.println("Default is running");
					break;
				}
		}//end of if isAdmin = false
		else {
			
			while(MainInterface.isValidInput(option, 3) == false) {
				
					System.out.println("\n\n-- CUSTOMERS MENU --\n"
							+ "Enter 1 to Add New Customer\n"
							+ "Enetr 2 to View All Customers\n"
							+ "Enter 3 to Search Customers (Edit/Delete Records)\n"
							+ "----\n"
							+ "Enter 0 to go back to main menu\n\n"
							+ "Enter your choice: ");
					
					option = input.next();
					
			}

			switch(option) {
				case "0":
					System.out.println("0 is running");
					break;
				case "1":
					System.out.println("1 is running");
					break;
				case "2":
					System.out.println("2 is running");
					break;
				case "3":
					System.out.println("3 is running");
					break;
				default:
					System.out.println("Default is running");
					break;
				}
		}
	}
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void view() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	
private void myOrdersMenu() {
		
		int option = 3;
		
		while(!(option == 2 || option == 1 || option == 0)) {
			
			try {
				System.out.println("\n\n-- VIEW ORDERS --\n"
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
			menu(false);
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
	
}
