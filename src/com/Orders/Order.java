package com.Orders;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Admin.Admin;
import com.Main.Login;

import constants.Type;
import delete.Delete;
import interfaces.MainInterface;
import search.Search;
import view.ViewRecords;

public class Order implements MainInterface {

	private static Scanner input = new Scanner(System.in);
	Boolean isAdmin = true;
	float totalPrice = 0;
	Writer output;
	String option = "-1";
	
	List<String> storedOrderIDs = new ArrayList<String>();
	List<String> searchResult = new ArrayList<String>();
	List<String> productsList = new ArrayList<String>();
	
	String searchQuery = null;
	
	public void getOrders() {
		
	}
	
	public void menu(Boolean isAdmin) {
		
		String option = "-1";
		
		
		if(isAdmin == true) {
			
			while(MainInterface.isValidIntInput(option, 3) == false) {
				
				System.out.println("\n\n-- ORDERS MENU --\n"
						+ "Enter 1 to Place New Order\n"
						+ "Enetr 2 to View Orders\n"
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

	public void add() {
		
		option = "-1";
		
		//MENU 1
		if(productsList.size()>0) {
			while(MainInterface.isValidIntInput(option, 3) == false) {
				
				System.out.println("\n\n-- ADD ITEM(s) TO ORDER --\n"
						+ "Enter 1 to search/enter product by name\n"
						+ "Enter 2 to view list of all products\n"
						+ "Enter 3 to view cart\n"
						+ "----\n"
						+ "Enter 0 to discard this order and go back to orders main menu\n\n"
						+ "Enter your choice: ");
				
				option = input.next();
				
			}//EO while
		} else {
			while(MainInterface.isValidIntInput(option, 2) == false) {
				
				System.out.println("\n\n-- ADD ITEM(s) TO ORDER --\n"
						+ "Enter 1 to search/enter product by name\n"
						+ "Enter 2 to view list of all products\n"
						+ "----\n"
						+ "Enter 0 to go back to orders main menu\n\n"
						+ "Enter your choice: ");
				
				option = input.next();
				
			}//EO while
		}//EO if-else product size > 0
		
		
		//Switch of MENU 1 \\\\\\\\\\\\\\\\\\
		switch(Integer.parseInt(option)) {
		case 0:
			totalPrice = 0;
			productsList.clear();
			menu(isAdmin);
			break;//case 0 of menu 1
		case 1:
			//search products
			searchProduct();
			
			option = "-1";
			while(MainInterface.isValidIntInput(option, 1) == false) {
				
				//MENU 2
				System.out.println("\n----\n"
							+ "Enter 1 to add this product to cart\n"
							+ "----\n"
							+ "Enter 0 to go back to orders menu\n\n"
							+ "Enter your choice: ");
					
					option = input.next();

				}//eo While
			
			//Switch of MENU 2 \\\\\\\\\\\\\\\\\\
			switch(Integer.parseInt(option)) {
			case 0:
				add();
				break; //case 0 of menu 2
			case 1:
				productsList.add(searchResult.get(0));
				System.out.println("* Product added to cart *");
				add();
				break; //case 1 of menu 2
			}
			//Switch of MENU 2 \\\\\\\\\\\\\\\\\\
			
			break;//case 1 of menu 1
		case 2:
			//View list of products
			ViewRecords v = new ViewRecords();
			v.viewRecord(Type.PRODUCT);
			add();
			break;//case 2 of menu 1
		case 3:
			viewCart();
			break;//case 3 of menu 1
		}
		//EO Switch of MENU 1 \\\\\\\\\\\\\\\\\\
	}//EO add()

	public void view() {
		
		//Show a menu
		option = "-1";
		storedOrderIDs.clear();
		
		while(MainInterface.isValidIntInput(option, 2) == false) {
			
			//TODO should we give an option to remove an item from cart?
			
			System.out.println("\n\n-- MENU --\n"
					+ "Enter 1 view all placed orders\n"
					+ "Enter 2 view only your orders\n"
					+ "----\n"
					+ "Enter 0 to go back to orders menu\n\n"
					+ "Enter your choice: ");
			
			option = input.next();
		}//eo While
		
		switch(Integer.parseInt(option)) {
		case 0:
			menu(isAdmin);
			break;
		case 1:
			
			try {
				
				FileReader orderFileReader = new FileReader(ordersFilePath);
				BufferedReader orderBufferedReader = new  BufferedReader(orderFileReader);
				String orderLine = null;
				
				while((orderLine = orderBufferedReader.readLine()) != null) {
					
					String[] splitedOrderLine = orderLine.split("-");
					printOrderSummary(splitedOrderLine[0]);
					
				}//end of while
				orderBufferedReader.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			menu(isAdmin);
			
			break;
		case 2:
			//Only My Orders
			
			try {
				
				FileReader orderFileReader = new FileReader(ordersFilePath);
				BufferedReader orderBufferedReader = new  BufferedReader(orderFileReader);
				String orderLine = null;
				
				while((orderLine = orderBufferedReader.readLine()) != null) {
					String[] splitedOrderLine = orderLine.split("-");
					if(splitedOrderLine[1].equals(Login.currentUserName)) {
						printOrderSummary(splitedOrderLine[0]);
					}
				}//end of while
				orderBufferedReader.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			menu(isAdmin);
			
			break;
		}
	}
	
	public void search() {
		// TODO Auto-generated method stub
		
	//	if(isAdmin == true) {
		option = "-1";
		storedOrderIDs.clear();
		
			while(MainInterface.isValidIntInput(option, 3) == false) {
				
				System.out.println("\n\n-- SEARCH ORDERS --\n"
						+ "Enter 1 to Search in all orders\n"
						+ "Enetr 2 to Search in your orders\n"
						+ "----\n"
						+ "Enter 0 to go back to main menu\n\n"
						+ "Enter your choice: ");
				
				option = input.next();

			}//eo While
			
			String searchQuery = "";
			
			System.out.print("\n* Search Orders *\n---------------------------\n");
			System.out.println("Enter Product Name: ");
			searchQuery = input.next();
			searchQuery += input.nextLine();
			
			switch(Integer.parseInt(option)) {
			case 0:
				menu(isAdmin);
				break;
			case 1:
				//Search in all products
				try {
					
					FileReader orderItemsFileReader = new FileReader(orderItemsFilePath);
					BufferedReader orderItemsBufferedReader = new  BufferedReader(orderItemsFileReader);
					String orderItemLine = null;
					
					while((orderItemLine = orderItemsBufferedReader.readLine()) != null) {
						
						String[] splitedOrderItemLine = orderItemLine.split("-");
						if(splitedOrderItemLine[2].toLowerCase().contains(searchQuery.toLowerCase())) {
							//This is the order that should be shown in the search result
							//Get order ID and pass it for printing
							printOrderSummary(splitedOrderItemLine[0]);
						}
						
					}//end of while
					orderItemsBufferedReader.close();
					
					//check if an order item with the same query is found print the complete order.
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(storedOrderIDs.size()>0) {
					showSearchResultMenu();
				} else {
					menu(isAdmin);
				}
				
				break;
			case 2:
				//Search in your products
				
				break;
			default:
				System.out.println("Something is wrong! We are not sure but try again.");
				break;
			}
			
	//	}//id isAdmin true
		
		//Show menu to search in all orders or only search in mine
		//search query
		//call view function and pass it the query.
		
	}

	
	public void edit(String orderID) {
		// TODO Auto-generated method stub
		
		//Will only call this function when searchResult == 1
		//if order placed order is by the same person editing it and the date is today only then allow to edit.
		// in editing mode you can only remove an order item
		
	}

	
	public void delete(String orderID) {
		// TODO Auto-generated method stub
		//in deleting you can delete a full order
		//Admin can delete all orders
		
		try {
			FileReader orderFileReader = new FileReader(ordersFilePath);
			BufferedReader orderBufferedReader = new  BufferedReader(orderFileReader);
			
			FileReader orderItemsFileReader = new FileReader(orderItemsFilePath);
			BufferedReader orderItemsBufferedReader = new  BufferedReader(orderItemsFileReader);
			
			String orderLine = null;
			String orderItemLine = null;
			
			while((orderLine = orderBufferedReader.readLine()) != null) {
				String[] splitedOrderLine = orderLine.split("-");
				if(splitedOrderLine[0].equals(orderID)) {
					Delete.deleteRecord(orderLine, ordersFilePath);
				}
			}//end of while
			orderBufferedReader.close();
			
			while((orderItemLine = orderItemsBufferedReader.readLine()) != null) {
				
				String[] splitedOrderItemLine = orderItemLine.split("-");
				
				if(splitedOrderItemLine[0].equals(orderID)) {
					Delete.deleteRecord(orderItemLine, orderItemsFilePath);
				}//eo if splitedorder id = splited order item						
			}//end of orderItemsBufferedReader while
			orderItemsBufferedReader.close();
			
			System.out.println("* Delete Operation Finished Successfully *");
			menu(isAdmin);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Other Methods
	private void printOrderSummary(String orderID) {
		try {
			storedOrderIDs.add(orderID);
			FileReader orderFileReader = new FileReader(ordersFilePath);
			BufferedReader orderBufferedReader = new  BufferedReader(orderFileReader);
			
			FileReader orderItemsFileReader = new FileReader(orderItemsFilePath);
			BufferedReader orderItemsBufferedReader = new  BufferedReader(orderItemsFileReader);
			
			String orderLine = null;
			String orderItemLine = null;
			//System.out.println("--------------------------------------------");
			while((orderLine = orderBufferedReader.readLine()) != null) {
				String[] splitedOrderLine = orderLine.split("-");
				if(splitedOrderLine[0].equals(orderID)) {		
					System.out.println("----PRODUCTS------------------");
					while((orderItemLine = orderItemsBufferedReader.readLine()) != null) {
						
						String[] splitedOrderItemLine = orderItemLine.split("-");
						
						if(splitedOrderLine[0].equals(splitedOrderItemLine[0])) {
							if(splitedOrderItemLine[4].equals("n")) {
								System.out.print(splitedOrderItemLine[2]+"(RM "+splitedOrderItemLine[3]+")\n");
							} else if(splitedOrderItemLine[4].equals("y")) {
								System.out.print(splitedOrderItemLine[2]+"(RM "+splitedOrderItemLine[3]+") + 5.00 fragile\n");
							}
						}//eo if splitedorder id = splited order item						
					}//end of orderItemsBufferedReader while
					System.out.println("-----SUMMARY-----------------");
					System.out.println("Total Bill: RM "+splitedOrderLine[2]
										+"\nOrder placed by: "+splitedOrderLine[1]
										+"\nDate Time: "+splitedOrderLine[3]);
					System.out.println("\n--------------------------------------------\n");
					orderItemsBufferedReader.close();
					
				}//EO if equals currentUserName
			}//end of orderBufferedReader whiles
			orderBufferedReader.close();
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void showSearchResultMenu() {
		
		option = "-1";
		
		while(MainInterface.isValidIntInput(option, 2) == false) {
			
			//TODO should we give an option to remove an item from cart?
			
			System.out.println("\n\n-- Additional Options --\n"
					+ "Enter 1 to EDIT these order(s)\n"
					+ "Enter 2 to DELETE these order(s)\n"
					+ "----\n"
					+ "Enter 0 to go back to orders menu\n\n"
					+ "Enter your choice: ");
			
			option = input.next();
		}//eo While
		
		switch(Integer.parseInt(option)) {
		case 0:
			menu(isAdmin);
			break;
		case 1:
			
			break;
		case 2:
			for(int i=0;i<storedOrderIDs.size();i++) {
				delete(storedOrderIDs.get(i));
			}
			break;
		default:
			System.out.println("Something is wrong! We are not sure but try again.");
			break;
		}
	}
	
	//Place Order Methods
	
	private void searchProduct() {
		
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
		
		
		}
	}
	
	private void viewCart() {
		
		totalPrice = 0;
		
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
		
		option = "-1";
		
		while(MainInterface.isValidIntInput(option, 2) == false) {
			//TODO should we give an option to remove an item from cart?
			System.out.println("\n\n-- OPTIONS --\n"
					+ "Enter 1 to add more items\n"
					+ "Enetr 2 to checkout\n"
					+ "----\n"
					+ "Enter 0 to discard this order and go back to orders main menu\n\n"
					+ "Enter your choice: ");
			
			option = input.next();
		}//eo While
		
		switch(Integer.parseInt(option)) {
			
		case 0:
			totalPrice = 0;
			productsList.clear();
			menu(isAdmin);
			break;
		case 1:
			break;
		case 2:
			checkOut();
			break;	
		}
	}
	
	private void checkOut()
	{
		
		try {
			String currentUserName = Login.currentUserName;
			int orderID = MainInterface.generateID(Type.ORDER);
			
			//Write OrderItems
			
			for(int i=0; i<productsList.size(); i++) {
				output = new BufferedWriter(new FileWriter(orderItemsFilePath, true));
				output.write(orderID+"-"+productsList.get(i)+"\n");
				
				output.close();
			}
			
			//Write to Order
			output = new BufferedWriter(new FileWriter(ordersFilePath, true));
			output.write(orderID+"-"+currentUserName+"-"+totalPrice+"-"+MainInterface.getDateTimeNow()+"\n");
			output.close();
			
			System.out.println("* Your order has been placed *");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}