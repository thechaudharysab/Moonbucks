package search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Customers.Customer;
import com.Products.Product;

import constants.Type;
import interfaces.MainInterface;

public class Search implements MainInterface {
	
	List<String> foundRecords;
	String searchQuery = "";
	private static Scanner input = new Scanner(System.in);
	
	Customer c = new Customer();
	Product p = new Product();
	
	public Search() {
		
	}
	
	public void search(Type searchType, String searchQuery) {
		
		String searchQueryLowerCase = searchQuery.toLowerCase();
		String filePath = "";
		foundRecords = new ArrayList<String>();
		
		switch(searchType) {
		
		case CUSTOMER:
			filePath = customersFilePath;
			break;
		case ORDER:
			filePath = ordersFilePath;
			break;
		case PRODUCT:
			filePath = productsFilePath;
			break;
		}//end of switch
		
		try {
			
			FileReader customerFileReader = new FileReader(filePath);
			BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
			String oneLine = null;
			
			while((oneLine = customerBufferedReader.readLine()) != null) {
				
				if(oneLine.toLowerCase().contains(searchQueryLowerCase)) {
					System.out.print(oneLine+"\n---------------------------\n");
					foundRecords.add(oneLine);
				}
				
			}//end of while
			
			System.out.println("\nSearch finished. "+foundRecords.size()+" record(s) found");
			customerBufferedReader.close();
			
			if(foundRecords.size() > 0) {
				searchMenu(searchType);
			}
			
		} catch(Exception e) {
			System.out.println("*Unable to open file '" + filePath + "' " + e.getMessage()+"*");
		}
	}
	
	private void searchMenu(Type searchType) throws FileNotFoundException, IOException {
		
		String option = "-1";
		
		do {
			if(foundRecords.size() > 1) {
				System.out.println("\nEnter 1 to edit these records\nEnter 2 to delete all of these records\n--------------------\nEnter 0 to go back to menu\n\nEnter your choice: ");
			} else {
				System.out.println("\nEnter 1 to edit this record\nEnter 2 to delete this record\n--------------------\nEnter 0 to go back to menu\n\nEnter your choice: ");
			}
			
			option = input.next();
		} while(MainInterface.isValidIntInput(option, 2) == false); 
		
		switch(option) {
			case "0":
				break;
			case "1":
				//EDIT
				switch(searchType) {
				case CUSTOMER:
					c.edit(foundRecords);
					break;
				case ORDER:
					System.out.println("Coming Soon");
					break;
				case PRODUCT:
					p.edit(foundRecords);
					break;
				}//end of switch searchType
			break; //end of case 1
			
			case "2":
				//DELETE
				switch(searchType) {
				
				case CUSTOMER:
					c.delete(foundRecords);
					break;
				case ORDER:
					System.out.println("Coming Soon");
					break;
				case PRODUCT:
					p.delete(foundRecords);
					break;
				}//end of switch searchType
				break;
				
			default:
				System.out.println("Default is running");
				break;
			}
		
	}
	
	
	
}
