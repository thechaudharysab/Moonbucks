package com.Functionality;

import java.io.BufferedReader;
import java.io.FileReader;

import com.Helpers.MainInterface;
import com.Helpers.Type;

public class ViewRecords implements MainInterface {
	
	String[] customersTag = {"ID: ","Name: ","Address: ", "Phone: "};
	String[] productsTag  = {"ID: ","Name: ","Price: ", "Fragile: "};
	
	public ViewRecords() {
		
	}
	
	public void viewRecord(Type recordType) {
		
		String filePath = "";
		
		switch(recordType) {
		
		case CUSTOMER:
			filePath = customersFilePath;
			System.out.println("\n* CUSTOMERS *\n----------------------------\n");
			break;
		case ORDER:
			filePath = ordersFilePath;
			break;
		case PRODUCT:
			filePath = productsFilePath;
			System.out.println("\n* PRODUCTS *\n----------------------------\n");
			break;
		}//end of switch
		
		//ID-NAME-PRICE-IS FRAGILE
		
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String oneLine = null;
			
			while((oneLine = bufferedReader.readLine()) != null) {
				
				String[] arrOfLine = oneLine.split("-");
//				for(int i=0;i<arrOfUser.length;i++) {
//					System.out.println(arrOfUser[i]+" -- ");
//				}
				if(recordType == Type.CUSTOMER) {
					System.out.println(customersTag[0]+arrOfLine[0]);
					System.out.println(customersTag[1]+arrOfLine[1]);
					System.out.println(customersTag[2]+arrOfLine[2]);
					System.out.println(customersTag[3]+arrOfLine[3]);
				} else if (recordType == Type.PRODUCT) {
					System.out.println(productsTag[0]+arrOfLine[0]);
					System.out.println(productsTag[1]+arrOfLine[1]);
					System.out.println(productsTag[2]+arrOfLine[2]);
					if(arrOfLine[3].equals("y")) {
						System.out.println(productsTag[3]+"Yes");
					} else {
						System.out.println(productsTag[3]+"No");
					}
					
				}
	
				System.out.println("\n||||||||||||||||||||||||||||||||||\n");
					
	        }//end of while
			
			bufferedReader.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
