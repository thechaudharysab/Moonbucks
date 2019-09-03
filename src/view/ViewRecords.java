package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import constants.Type;

public class ViewRecords {
	
	public ViewRecords() {
		
	}
	
	public void viewRecord(Type recordType) {
		
		String filePath = "";
		
		switch(recordType) {
		
		case CUSTOMER:
			filePath = "src/TextFiles/Customers.txt";
			System.out.println("\n* CUSTOMERS *\n-------------------------------\nNAME -- ADDRESS -- PHONE\n-------------------------------");
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
				
				String[] arrOfUser = oneLine.split("-");
				System.out.println(arrOfUser[1]+" -- "+arrOfUser[2]+" -- "+arrOfUser[3]+"\n-------------------------------");
					
	        }//end of while
			
			customerBufferedReader.close();
			
		} catch(FileNotFoundException ex) {
			System.out.println("*Unable to open file '" + filePath + "' " + ex.getMessage()+"*");
		}//end of FileNotFoundException
		catch(IOException ex) {
            System.out.println("Error reading file '" + filePath + "' " + ex.getMessage()+"*");
        }//end of IOException
		catch(NullPointerException ex) {
			System.out.println("Error: "+ ex.getMessage()+"*");
		}//end of NullPointerException
		
	}
	
}
