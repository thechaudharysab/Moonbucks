package com.Products;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

import admin.Admin;
import constants.Type;
import delete.Delete;
import edit.Edit;
import interfaces.ClassInterface;
import interfaces.MainInterface;
import search.Search;
import view.ViewRecords;

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
		ViewRecords v = new ViewRecords();
		v.viewRecord(Type.PRODUCT);
		menu(true);
	}

	@Override
	public void search() {
		String searchQuery = "";
		
		System.out.print("\n* Search Products *\n---------------------------\n");
		System.out.println("Enter Search Query: ");
		searchQuery = input.next();
		searchQuery += input.nextLine();
		Search s = new Search();
		
		System.out.println("Search Query 1: "+searchQuery.toLowerCase()+"------ \n");
		s.search(Type.PRODUCT, searchQuery);
		
		menu(true);
		
	}

	@Override
	public void edit(List<String> recordsToEdit) {
		
		//input.nextLine();
		
		try {
			
			for(int i=0;i<recordsToEdit.size();i++) {
				
				FileReader productFileReader = new FileReader(productsFilePath);
				BufferedReader productBufferedReader = new BufferedReader(productFileReader);
				String oneLine = null;
				
				while((oneLine = productBufferedReader.readLine()) != null) {
					
					String[] arrOfProduct = oneLine.split("-");
					String[] arrOfFoundRecords = recordsToEdit.get(i).split("-");
					
					if(arrOfProduct[0].equals(arrOfFoundRecords[0])) {
					System.out.println("------\n*Editing Record*\n------");
					System.out.println("------------------------------------\n");
					System.out.println(arrOfFoundRecords[1]+" -- "+arrOfFoundRecords[2]+" -- "+arrOfFoundRecords[3]+"\n");
					System.out.println("------------------------------------\n");
					
					System.out.println("Product Name ("+arrOfFoundRecords[1]+"): ");
					productName = input.nextLine();
					
					if(!(productName.toLowerCase().equals(arrOfFoundRecords[1].toLowerCase()))) {
						if(MainInterface.nameAlreadyExist(Type.PRODUCT, productName) == true) {
							do {
								System.out.println("Product Name: ");
								productName = input.nextLine();
								
							} while(MainInterface.nameAlreadyExist(Type.PRODUCT, productName) == true);
						}
					}
					
					do {
						System.out.println("Price (RM "+arrOfFoundRecords[2]+"): RM ");
						//input.nextLine();
						productRate = input.nextLine();
						
					} while(MainInterface.isValidFloatInput(productRate) == false);
					
					do {
						System.out.println("Is this product fragile? ("+arrOfFoundRecords[3]+") (y/n): ");
						isFragile = input.nextLine();
					} while(isFragile == "y" || isFragile == "n");
					
					String updatedString = arrOfProduct[0]+"-"+productName+"-"+productRate+"-"+isFragile;
					
					Edit.editRecords(oneLine, updatedString, productsFilePath);
					System.out.println("\nUpdated "+(i+1)+" of total "+recordsToEdit.size()+" records\n");
					
					}//end of if
				}//eo while
				
				productBufferedReader.close();
				
			}//eo for loop
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(List<String> recordsToDelete) {
		
		try {
			
			for(int i=0;i<recordsToDelete.size();i++) {
				
				FileReader productFileReader = new FileReader(productsFilePath);
				BufferedReader productBufferedReader = new BufferedReader(productFileReader);
				
				Delete.deleteRecord(recordsToDelete.get(i), productsFilePath);
				System.out.println("\nDeleted "+(i+1)+" of total "+recordsToDelete.size()+" records\n");
				productBufferedReader.close();
			}//eo For
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
