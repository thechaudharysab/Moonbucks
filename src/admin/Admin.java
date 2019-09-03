package admin;
import java.util.InputMismatchException;
import java.util.Scanner;

import constants.Type;
import customer.Customer;
import interfaces.MainInterface;
import search.Search;

public class Admin implements MainInterface {
	
	private static Scanner input = new Scanner(System.in);
	
	public Admin() {
		System.out.print("Welcome Admin");
	}
	
	public void adminMainMenu() {
		
		String option = "-1";
		
		while(MainInterface.isValidInput(option, 3) == false) {
			
				System.out.println("\n\n-- MAIN MENU --\n"
						+ "Enter 1 for Customers\n"
						+ "Enetr 2 for Products\n"
						+ "Enter 3 for Orders\n-----------\n"
						+ "Enter 0 to exit\n\n"
						+ "Enter your choice: ");
				
				option = input.next();
				
		}//end of while

		switch(option) {
			case "1":
				customersMenu();
				break;
			case "2":
				productsMenu();
				break;
			case "3":
				ordersMenu();
				break;
			default:
				System.out.println("Default is running");
				break;
			}
	}
	
	private void customersMenu() {
		Customer c = new Customer();
		c.menu(true);
	}
	private void productsMenu() {
		
		int option = 4;
		
		while(!(option == 2 || option == 1 || option == 3 || option == 0)) {
			
			try {
				System.out.println("\n\n-- PRODUCTS MENU --\n"
						+ "Enter 1 to Add New Product\n"
						+ "Enetr 2 to View All Products\n"
						+ "Enter 3 to Search Products\n"
						+ "----\n"
						+ "Enter 0 to go back to main menu\n\n"
						+ "Enter your choice: ");
				
				option = input.nextInt();
				
				if(option > 4 || option < 0) {
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
				adminMainMenu();
				break;
			case 1:
				System.out.println("You have entered: "+ option);
				break;
			case 2:
				System.out.println("You have entered: "+ option);
				break;
			case 3:
				System.out.println("You have entered: "+ option);
				break;
			default:
				System.out.println("Default is running");
				break;
			}
	}
	private void ordersMenu() {
		int option = 4;
		
		while(!(option == 2 || option == 1 || option == 3 || option == 0)) {
			
			try {
				System.out.println("\n\n-- MENU --\n"
						+ "Enter 1 to Place New Order\n"
						+ "Enetr 2 to View All Orders\n"
						+ "Enter 3 to Search Orders\n"
						+ "----\n"
						+ "Enter 0 to go back to main menu\n\n"
						+ "Enter your choice: ");
				
				option = input.nextInt();
				
				if(option > 4 || option < 0) {
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
				adminMainMenu();
				break;
			case 1:
				System.out.println("You have entered: "+ option);
				break;
			case 2:
				System.out.println("You have entered: "+ option);
				break;
			case 3:
				System.out.println("You have entered: "+ option);
				break;
			default:
				System.out.println("Default is running");
				break;
			}
	}
	
	
	//Customer Methods
	
//	private void createNewUser() {
//		
//		String customerID = "";
//		String customerName = "";
//		String customerAddress = "";
//		String customerPhone = "";
//		String password = "";
//		
//		System.out.print("\n*Create New Customer*\n");
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
////		while(MainClassInterface.isUsernameAvailable(customerID) == false) {
////			
////			System.out.println(customerID+" is already taken. Try another one! \nUsername: ");
////			customerID = input.next();
////			customerID += input.nextLine();
////			
////		}
//		System.out.println("Create Password: ");
//		password = input.next();
//		password += input.nextLine();
//		
//		Customer c = new Customer(customerID, customerName, customerAddress, customerPhone, password);
//		c.writeNewUserToFile();
//		
//		adminMainMenu();
//		
//		
//	}
//	
//	private void viewAllCustomers() {
//		
//		ViewRecords v = new ViewRecords();
//		v.viewRecord(Type.CUSTOMER);
//		customersMenu();
//	}
	
	private void searchCustomer() {
		
		String searchQuery = "";
		
		System.out.print("\n* Search Customers *\n---------------------------\n");
		System.out.println("Enter Search Query: ");
		
		searchQuery = input.next();
		searchQuery += input.nextLine();
		
		Search s = new Search();
		s.search(Type.CUSTOMER, searchQuery);
		
		customersMenu();
		
	}

	
	
}
