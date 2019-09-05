package com.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Admin.Admin;

import constants.Type;
import interfaces.ClassInterface;
import interfaces.MainInterface;
import search.Search;
import view.ViewRecords;

public class Order implements ClassInterface, MainInterface {

	private static Scanner input = new Scanner(System.in);
	Boolean isAdmin = true;
	float totalPrice = 0;
	List<String> searchResult = new ArrayList<String>();
	List<String> productsList = new ArrayList<String>();
	
	@Override
	public void menu(Boolean isAdmin) {
		
		String option = "-1";
		
		
		if(isAdmin == true) {
			
			while(MainInterface.isValidIntInput(option, 3) == false) {
				
				System.out.println("\n\n-- ORDERS MENU --\n"
						+ "Enter 1 to Place New Order\n"
						+ "Enetr 2 to View All Customers Orders\n"
						+ "Enter 3 to Search Orders\n"
						+ "----\n"
						+ "Enter 0 to go back to main menu\n\n"
						+ "Enter your choice: ");
				
				option = input.next();

			}//eo While
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
		
		//Show Menu i.e. press 1 to search a product 2 to view list of all products
		
		String option = "-1";
		
		while(MainInterface.isValidIntInput(option, 2) == false) {
			//Menu 1
			System.out.println("\n\n-- ADD ITEM(s) TO ORDER --\n"
					+ "Enter 1 to search/enter product by name\n"
					+ "Enetr 2 to view list of all products\n"
					+ "----\n"
					+ "Enter 0 to go back to orders menu\n\n"
					+ "Enter your choice: ");
			
			option = input.next();

		}//eo While
		
		//switch of Menu 1
		switch(Integer.parseInt(option)) {
		
		case 0:
			totalPrice = 0;
			productsList.clear();
			menu(isAdmin);
			break;
		case 1:
			//Search Product
			
			String searchQuery = "";
			
			System.out.println("Enter Product Name: ");
			
			searchQuery = input.next();
			searchQuery += input.nextLine();
			
			Search s = new Search();
			searchResult.clear();
			searchResult = s.search(Type.PRODUCT, searchQuery, false);
			
			if(searchResult.size() !=1) {
				searchResult.clear();
				System.out.println("Which product do you want to buy? Be specific.");
				add();
			} else if(searchResult.size() == 1) {
				
				//Print result
				String[] product = searchResult.get(0).split("-");
				System.out.println("\n----------------------------");
				System.out.println("Product Name: "+product[1]
									+"\nPrice: RM "+product[2]);
				if(product[3].equals("y")) {
					System.out.println("This Product is fragile an extra RM 5.00 will be charged.");
				}
				System.out.println("----------------------------");
				//print result end
				
				option = "-1";
				
				while(MainInterface.isValidIntInput(option, 1) == false) {
					//Menu 2
					System.out.println("\n----\n"
							+ "Enter 1 to add this product to cart\n"
							+ "----\n"
							+ "Enter 0 to discard changes and go back to orders menu\n\n"
							+ "Enter your choice: ");
					
					option = input.next();

				}//eo While
				
				//switch of menu 2
				switch(Integer.parseInt(option)) {
				case 0:
					totalPrice = 0;
					productsList.clear();
					menu(isAdmin);
					break;
				case 1:
					//add to cart
					
					productsList.add(searchResult.get(0));
					System.out.println("* Product added to cart *");
					
					option = "-1";
					
					while(MainInterface.isValidIntInput(option, 2) == false) {
						//menu 3
						System.out.println("\n\n-- MENU --\n"
								+ "Enter 1 to add more items\n"
								+ "Enetr 2 to view cart\n"
								+ "----\n"
								+ "Enter 0 to discard changes and go back to orders menu\n\n"
								+ "Enter your choice: ");
						
						option = input.next();

					}//eo While
					
					//switch of menu 3s
					switch(Integer.parseInt(option)) {
					case 0:
						totalPrice = 0;
						productsList.clear();
						menu(isAdmin);
						break;
					case 1:
						//Add More Items
						add();
						break;
					case 2:
						//View Cart
						
						System.out.println("---- CART("+productsList.size()+") ----\n");
						
						for(int i=0;i<productsList.size();i++) {
							String[] oneProduct = productsList.get(i).split("-");
							if(oneProduct[3].equals("y")) {
								totalPrice += 5 + Float.parseFloat(oneProduct[2]);
								System.out.println("Product: "+oneProduct[1]+" Price: RM "+oneProduct[2]+" +RM 5.00 (fragile)");
							} else if(oneProduct[3].equals("n")) {
								totalPrice += Float.parseFloat(oneProduct[2]);
								System.out.println("Product: "+oneProduct[1]+" Price: RM "+oneProduct[2]);
							} else {
								System.out.println("Something is wrong! We are not sure but try again. 6");
							}//eo if-ifelse-else
						}//eo For
						System.out.println("--------\nTotal bill: RM "+totalPrice+"\n--------");
						
						//Menu 4
						
						option = "-1";
						
						while(MainInterface.isValidIntInput(option, 2) == false) {
							//menu 3
							System.out.println("\n\n-- MENU --\n"
									+ "Enter 1 to add more items\n"
									+ "Enetr 2 to checkout\n"
									+ "----\n"
									+ "Enter 0 to discard changes and go back to orders menu\n\n"
									+ "Enter your choice: ");
							
							option = input.next();
						}//eo While
						
						switch(Integer.parseInt(option)) {
						case 0:
							totalPrice = 0;
							menu(isAdmin);
							break;
						case 1:
							//add more items
							add();
							break;
						case 2:
							//checkout
							checkOut();
							break;
						default:
							System.out.println("Something is wrong! We are not sure but try again.");
							break;
						}
						
						break;
					default:
						System.out.println("Something is wrong! We are not sure but try again. 5");
						break;
					}//eo switch of menu 3
					break;
				default:
					System.out.println("Something is wrong! We are not sure but try again. 4");
					break;
				}//eo switch of menu 2
			}//eo else if
			
			break;
		case 2:
			//Products List
			ViewRecords v = new ViewRecords();
			v.viewRecord(Type.PRODUCT);
			add();
			break;
		default:
			System.out.println("Something is wrong! We are not sure but try again. 3");
			break;
		}//eo Switch of Menu 1
	}

	@Override
	public void view() {
		
		
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
	
	
	private void checkOut()
	{
		//if 2 is entered generate order ID
		
		//write orderitems.txt with orderitems and product array unzipping
	}
	
}