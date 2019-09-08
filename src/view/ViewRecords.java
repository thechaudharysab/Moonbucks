package view;

import java.io.BufferedReader;
import java.io.FileReader;

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
			System.out.println("\n* PRODUCTS *\n-------------------------------\nID-NAME-PRICE-IS FRAGILE\n-------------------------------");
			break;
		}//end of switch
		
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String oneLine = null;
			
			while((oneLine = bufferedReader.readLine()) != null) {
				
				//String[] arrOfUser = oneLine.split("-");
//				for(int i=0;i<arrOfUser.length;i++) {
//					System.out.println(arrOfUser[i]+" -- ");
//				}
				System.out.print(oneLine);
				System.out.println("\n-------------------------------");
					
	        }//end of while
			
			bufferedReader.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
