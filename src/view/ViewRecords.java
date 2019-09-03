package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import constants.Type;
import interfaces.MainInterface;

public class ViewRecords implements MainInterface {
	
	public ViewRecords() {
		
	}
	
	public void viewRecord(Type recordType) {
		
		String filePath = "";
		
		switch(recordType) {
		
		case CUSTOMER:
			filePath = customersFilePath;
			System.out.println("\n* CUSTOMERS *\n-------------------------------\nID-NAME-ADDRESS-PHONE\n-------------------------------");
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
				
				//String[] arrOfUser = oneLine.split("-");
//				for(int i=0;i<arrOfUser.length;i++) {
//					System.out.println(arrOfUser[i]+" -- ");
//				}
				System.out.print(oneLine);
				System.out.println("\n-------------------------------");
					
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
