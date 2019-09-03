package admin;
import java.util.InputMismatchException;
import java.util.Scanner;

import customer.Customer;
import interfaces.MainInterface;

public class Admin implements MainInterface {
	
	private static Scanner input = new Scanner(System.in);
	Customer c = new Customer();
	
	public Admin() {
		
	}
	
	public void adminMainMenu() {
		
		String option = "-1";
		
		do {
			System.out.println("\n\n-- MAIN MENU --\n"
					+ "Enter 1 for Customers\n"
					+ "Enetr 2 for Products\n"
					+ "Enter 3 for Orders\n-----------\n"
					+ "Enter 0 to exit\n\n"
					+ "Enter your choice: ");
			
			option = input.next();
		} while(MainInterface.isValidInput(option, 3) == false); 
			
		//System.out.print("options here is: "+option+"\n");
		
		switch(Integer.parseInt(option)) {
		
			case 1:
				c.menu(true);
				break;
			case 2:
				productsMenu();
				break;
			case 3:
				ordersMenu();
				break;
			default:
				System.out.println("Default is running");
				break;
			}
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
}
