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
	
	public List<String> search(Type searchType, String searchQuery, Boolean showMenu) {
		
		//String searchQueryLowerCase = searchQuery.toLowerCase();
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
			
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String oneLine = "";
			
			while((oneLine = bufferedReader.readLine()) != null) {
				//System.out.println("Search Query: "+searchQuery.toLowerCase()+"------ \n");
				if(oneLine.toLowerCase().contains(searchQuery.toLowerCase())) {
					System.out.print(oneLine+"\n---------------------------\n");
					foundRecords.add(oneLine);
				}
				
			}//end of while
			
			System.out.println("\nSearch finished. "+foundRecords.size()+" record(s) found");
			bufferedReader.close();
			
			if(foundRecords.size() > 0 && showMenu ==  true) {
				searchMenu(searchType);
				return null;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return foundRecords;
		
	}
	
	private void searchMenu(Type searchType) throws FileNotFoundException, IOException {
		
		String option = "-1";
		
		do {
			if(foundRecords.size() > 1) {
				System.out.println("\nEnter 1 to edit all these records\nEnter 2 to delete all of these records\n--------------------\nEnter 0 to go back to menu\n\nEnter your choice: ");
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
