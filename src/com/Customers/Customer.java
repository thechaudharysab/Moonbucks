package com.Customers;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import com.Admin.Admin;

import constants.Type;
import delete.Delete;
import edit.Edit;
import interfaces.ClassInterface;
import interfaces.MainInterface;
import search.Search;
import view.ViewRecords;

public class Customer implements ClassInterface, MainInterface {
	
	private String customerID;
	private String customerName;
	private String customerAddress;
	private String customerPhone;
	protected String password;
	
	//public static String currentUserName = "";
	
	private static Scanner input = new Scanner(System.in);
	Writer output;
	
	private Boolean isUserAdmin;
	
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
//	public String getCustomerID() {
//		return customerID;
//	}
//	
	public String getcustomerName() {
		return customerName;
	}
//	
//	public String getcustomerAddress() {
//		return customerAddress;
//	}
//	
//	public String getcustomerPhone() {
//		return customerPhone;
//	}
//	
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
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Methods

	@Override
	public void menu(Boolean isAdmin) {
		
		isUserAdmin = isAdmin;
		String option = "-1";
		
		if(isUserAdmin == false) {
			
			while(MainInterface.isValidIntInput(option, 2) == false) {
				
					System.out.println("\n\n-- MAIN MENU --\n"
							+ "Enter 1 to Place a new order\n"
							+ "Enter 2 to view your orders\n----------\nEnter 0 to exit\n\n"
							+ "Enter your choice: ");
					
					option = input.next();
					
			} //end of while

			switch(option) {
				case "0":
					System.exit(0);
					break;
				case "1":
					System.out.println("You have entered: "+option);
					break;
				case "2":
					myOrdersMenu();
					break;
				default:
					System.out.println("Something is wrong! We are not sure but try again.");
					break;
				}
		}//end of if isAdmin = false
		else {
			
			while(MainInterface.isValidIntInput(option, 3) == false) {
				
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
					Admin a = new Admin();
					a.adminMainMenu();
					break;
				case "1":
					add();
					break;
				case "2":
					view();
					break;
				case "3":
					search();
					break;
				default:
					System.out.println("Something is wrong! We are not sure but try again.");
					break;
				}
		}
	}
	
	@Override
	public void add() {
		//To add a new Customer
		
		System.out.print("\n*Create New Customer*\n");
		
		System.out.println("Name: ");
		customerName = input.next();
		customerName += input.nextLine();
		
		System.out.println("Address: ");
		customerAddress = input.next();
		customerAddress += input.nextLine();
		
		System.out.println("Phone: ");
		customerPhone = input.next();
		customerPhone += input.nextLine();
		
		System.out.println("Username (Case-sensitive): ");
		customerID = input.next();
		customerID += input.nextLine();
		
		
		while(MainInterface.isUsernameAvailable(customerID) == false) {
		
			System.out.println(customerID+" is already taken. Try another one! \nUsername: ");
			customerID = input.next();
			customerID += input.nextLine();
		
		}
	
		System.out.println("Create Password: ");
		password = input.next();
		password += input.nextLine();
	
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
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		menu(true);
		
	}//end of add()

	@Override
	public void view() {
				
		ViewRecords v = new ViewRecords();
		v.viewRecord(Type.CUSTOMER);
		menu(isUserAdmin);
		
	}

	@Override
	public void search() {  
		String searchQuery = "";
		
		System.out.print("\n* Search Customers *\n---------------------------\n");
		System.out.println("Enter Search Query: ");
		
		searchQuery = input.next();
		searchQuery += input.nextLine();
		
		Search s = new Search();
		s.search(Type.CUSTOMER, searchQuery, true);
		
		menu(isUserAdmin);
		
	}

	@Override
	public void edit(List<String> recordsToEdit) {
		// TODO Auto-generated method stub
		
		try {
		
		for(int i=0;i<recordsToEdit.size();i++) {
			
			FileReader customerFileReader = new FileReader(customersFilePath);
			BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
			String oneLine = null;
			
			while((oneLine = customerBufferedReader.readLine()) != null) {
				
				String[] arrOfUser = oneLine.split("-");
				String[] arrOfFoundRecords = recordsToEdit.get(i).split("-");
				
				if(arrOfUser[0].equals(arrOfFoundRecords[0])) {
					
					System.out.println("------\n*Editing Record*\n------");
					System.out.println(arrOfFoundRecords[1]+" -- "+arrOfFoundRecords[2]+" -- "+arrOfFoundRecords[3]+"\n");
					System.out.println("------------------------------------\n");
					
					System.out.println("Name ("+arrOfFoundRecords[1]+"): ");
					customerName = input.next();
					customerName += input.nextLine();
					
					System.out.println("Address ("+arrOfFoundRecords[2]+"): ");
					customerAddress = input.next();
					customerAddress += input.nextLine();
					
					System.out.println("Phone ("+arrOfFoundRecords[3]+"): ");
					customerPhone = input.next();
					customerPhone += input.nextLine();
					
					String updatedString = arrOfUser[0]+"-"+customerName+"-"+customerAddress+"-"+customerPhone;
					
					Edit.editRecords(oneLine, updatedString, customersFilePath);
					System.out.println("\nUpdated "+(i+1)+" of total "+recordsToEdit.size()+" records\n");
					}
            }//end of while
			
			customerBufferedReader.close();
			
		}//end of for-loop
		
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	@Override
	public void delete(List<String> recordsToDelete) {
		
		try {

			for(int i=0;i<recordsToDelete.size();i++) {
				
				FileReader customerFileReader = new FileReader(customersLoginFilePath);
				BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
				String oneLine = null;
				
				String[] arrOfFoundRecords = recordsToDelete.get(i).split("-");
				
				while((oneLine = customerBufferedReader.readLine()) != null) {
					String[] arrOfUser = oneLine.split("-");
					if(arrOfUser[0].equals(arrOfFoundRecords[0])) {
						Delete.deleteRecord(oneLine,customersLoginFilePath);
						}
	            }//end of while
				
				//Delete customers records
				Delete.deleteRecord(recordsToDelete.get(i),customersFilePath);
				System.out.println("\nDeleted "+(i+1)+" of total "+recordsToDelete.size()+" records\n");
				customerBufferedReader.close();
			} //end of for-loop of customers login
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	private void myOrdersMenu() {
		
//		int option = 3;
//		
//		while(!(option == 2 || option == 1 || option == 0)) {
//			
//			try {
//				System.out.println("\n\n-- VIEW ORDERS --\n"
//						+ "Enter 1 to search orders\n"
//						+ "Enter 2 to see all your orders\n"
//						+ "----\n"
//						+ "Enter 0 to go back to main menu\n\n"
//						+ "Enter your choice: ");
//				option = input.nextInt();
//				
//				if(option > 2 || option < 0) {
//					System.out.println("Invalid menu number entered. A valid menu option is required.");
//				}
//				
//			} catch (InputMismatchException ex) {
//				System.out.println("Invalid menu number entered. A valid menu option is required.");
//				if(input.hasNextInt()) {
//					option = input.nextInt();
//				} else {
//					input.next();
//					continue;
//				}
//				 //The error occurs here
//			}
//		}
//
//		switch(option) {
//		case 0:
//			menu(false);
//			break;
//		case 1:
//				System.out.println("You have entered: "+option);
//				break;
//		case 2:
//				System.out.println("You have entered: "+option);
//				break;
//		default:
//				System.out.println("Default is running");
//				break;
//			}
	}


}
