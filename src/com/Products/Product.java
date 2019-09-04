package com.Products;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

import admin.Admin;
import constants.Type;
import interfaces.ClassInterface;
import interfaces.MainInterface;

public class Product implements ClassInterface, MainInterface {
	
	private String productName;
	private String productRate;
	private String isFragile;
	
	private static Scanner input = new Scanner(System.in);
	Writer output;
	
	public Product() {
		
	}
	
	@Override
	public void menu(Boolean isAdmin) {
		
		String option = "-1";
		
		while(MainInterface.isValidIntInput(option, 3) == false) {
			
				System.out.println("\n\n-- PRODUCTS MENU --\n"
						+ "Enter 1 to Add New Product\n"
						+ "Enetr 2 to View All Products\n"
						+ "Enter 3 to Search Products\n"
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
				//Add
				add();
				break;
			case 2:
				//View
				view();
				break;
			case 3:
				//Search
				search();
				break;
			default:
				System.out.println("Something is wrong! We are not sure but try again.");
				break;
			}
		
	}

	@Override
	public void add() {
		
		System.out.print("\n*Add New Product*\n");
		input.nextLine();
		
		try {
			do {
				System.out.println("Product Name: ");
				productName = input.nextLine();
			} while(MainInterface.nameAlreadyExist(Type.PRODUCT, productName) == true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		do {
			System.out.println("Price: RM");
			//input.nextLine();
			productRate = input.nextLine();
			
		} while(MainInterface.isValidFloatInput(productRate) == false);
		
		do {
			System.out.println("Is this product fragile? (y/n): ");
			isFragile = input.nextLine();
		} while(isFragile == "y" || isFragile == "n");
		
		try {
			output = new BufferedWriter(new FileWriter(productsFilePath, true));
			output.write(MainInterface.generateID(Type.PRODUCT)+"-"
							+productName+"-"
							+productRate+"-"
							+isFragile+"\n");
			output.close();
			
			System.out.println("* Successfully Added New Product *");
			

		} catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		
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
