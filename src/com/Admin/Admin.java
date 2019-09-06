package com.Admin;
import java.util.Scanner;

import com.Customers.Customer;
import com.Orders.Order;
import com.Products.Product;

import interfaces.MainInterface;

public class Admin implements MainInterface {
	
	private static Scanner input = new Scanner(System.in);
	Customer c = new Customer();
	Product p = new Product();
	Order o = new Order();
	
	//public static String currentUserName = "";
	
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
		} while(MainInterface.isValidIntInput(option, 3) == false); 
			
		//System.out.print("options here is: "+option+"\n");
		
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
