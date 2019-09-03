package search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import constants.Type;

public class Search {
	
	private int totalRecordsFound = 0;
	
	String searchQuery = "";
	private static Scanner input = new Scanner(System.in);
	
	public Search() {
		
	}
	
	public void search(Type searchType, String searchQuery) {
		
		String searchQueryLowerCase = searchQuery.toLowerCase();
		String filePath = "";
		
		switch(searchType) {
		
		case CUSTOMER:
			filePath = "src/TextFiles/Customers.txt";
			break;
		case ORDER:
			filePath = "src/TextFiles/Orders.txt";
			break;
		case PRODUCT:
			filePath = "src/TextFiles/Products.txt";
			break;
		}//end of switch
		
		try {
			
			FileReader customerFileReader = new FileReader(filePath);
			BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
			String oneLine = null;
			
			while((oneLine = customerBufferedReader.readLine()) != null) {
				
				if(oneLine.toLowerCase().contains(searchQueryLowerCase)) {
					totalRecordsFound+=1;
					System.out.print(oneLine+"\n---------------------------\n");
				}
				
			}//end of while
			
			System.out.println("\nSearch finished. "+totalRecordsFound+" record(s) found");
			customerBufferedReader.close();
			
			if(totalRecordsFound > 0) {
				searchMenu();
			}
			
		} catch(FileNotFoundException ex) {
			System.out.println("*Unable to open file '" + filePath + "' " + ex.getMessage()+"*");
		}//end of FileNotFoundException
		catch(IOException ex) {
            System.out.println("*Error reading file '" + filePath + "' " + ex.getMessage()+"*");
        }//end of IOException
		catch(NullPointerException ex) {
			System.out.println("*Error: "+ ex.getMessage()+"*");
		}//end of NullPointerException
	}
	
	private void searchMenu() {
		
		int option = 3;
		
		while(!(option == 2 || option == 1 || option == 0)) {
			
			try {
				if(totalRecordsFound > 1) {
					System.out.println("\nEnter 1 to edit these records\nEnter 2 to delete all of these records\nEnter 0 to go back to menu\nEnter your choice: ");
				} else {
					System.out.println("\nEnter 1 to edit this record\nEnter 2 to delete this record\nEnter 0 to go back to menu\nEnter your choice: ");
				}
				
				option = input.nextInt();
				
				if(option > 2 || option < 0) {
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
				break;
			case 1:
				System.out.println("1 is running");
				break;
			case 2:
				System.out.println("2 is running");
				break;
			default:
				System.out.println("Default is running");
				break;
			}
		
	}
	
}
