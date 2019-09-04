package com.Orders;

import java.util.List;
import java.util.Scanner;

import com.Admin.Admin;

import interfaces.ClassInterface;
import interfaces.MainInterface;

public class Order implements ClassInterface, MainInterface {

	private static Scanner input = new Scanner(System.in);
	
	@Override
	public void menu(Boolean isAdmin) {
		
		String option = "-1";
		
		while(MainInterface.isValidIntInput(option, 3) == false) {
			
				System.out.println("\n\n-- MENU --\n"
						+ "Enter 1 to Place New Order\n"
						+ "Enetr 2 to View All Customers Orders\n"
						+ "Enter 3 to Search Orders\n"
						+ "----\n"
						+ "Enter 0 to go back to main menu\n\n"
						+ "Enter your choice: ");
				
				option = input.next();

		}

		switch(Integer.parseInt(option)) {
			case 0:
				Admin a = new Admin();
				a.adminMainMenu();
				break;
			case 1:
				add();
				break;
			case 2:
				view();
				break;
			case 3:
				search();
				break;
			default:
				System.out.println("Something is wrong! We are not sure but try again.");
				break;
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
	public void edit(List<String> recordsToEdit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<String> recordsToDelete) {
		// TODO Auto-generated method stub
		
	}
	

	
}