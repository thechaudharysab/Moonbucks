package com.Admin;
import java.util.Scanner;

import com.Customers.Customer;
import com.Helpers.MainInterface;
import com.Orders.Order;
import com.Products.Product;

public class Admin implements MainInterface {
	
	private static Scanner input = new Scanner(System.in);
	Customer c = new Customer();
	Product p = new Product();
	Order o = new Order();
	
	public void adminMainMenu() {
		
		String option = "-1";
		
		do {
			System.out.println("\n-- MAIN MENU --\n"
					+ "Enter 1 for CUSTOMERS\n"
					+ "Enetr 2 for PRODUCTS\n"
					+ "Enter 3 for ORDERS\n"
					+ "Enter 0 to EXIT\n\n"
					+ "Enter your choice: ");
			
			option = input.next();
		} while(MainInterface.isValidIntInput(option, 3) == false); 
		
		switch(Integer.parseInt(option)) {
			case 0:
				System.exit(0);
			case 1:
				c.menu(true);
				break;
			case 2:
				p.menu(true);
				break;
			case 3:
				o.menu(true);
				break;
			default:
				System.out.println("Default is running");
				break;
			}
	}
	
}
