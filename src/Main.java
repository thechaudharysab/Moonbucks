import java.io.*;
import java.util.Scanner;
import customer.Customer;
import interfaces.MainInterface;
import admin.Admin;

public class Main implements MainInterface {

	
	public static String username;
	private static String password;
	
	private static Scanner input = new Scanner(System.in);
	
	private static Boolean isAdminLoginSuccessful = false;
	private static Boolean isCustomerLoginSuccessful = false;
	
	public static void main(String[] args) throws IOException {
		
			System.out.print("Welcome to MoonBucks! Please Login to continue.\n");
			
			while(isAdminLoginSuccessful==false && isCustomerLoginSuccessful==false) {
				loginInput();
			}
			
			if(isAdminLoginSuccessful==true || isCustomerLoginSuccessful==true) {
				
				if(isCustomerLoginSuccessful==true) {
					
					Customer c = new Customer(username);
					System.out.print("Welcome "+c.getcustomerName()+"!");
					c.menu(false);
					
				} else {
					
					Admin a = new Admin();
					System.out.print("Welcome Admin");
					a.adminMainMenu();
					
				}
			}
			
	}//end of main
	
	private static void loginInput() {
		
		System.out.print("\nUsername: ");
		username = input.next();		
		System.out.print("Password: ");
		password = input.next();
		
		validateUserLogin();
		
	}
	
	private static void validateUserLogin() {
		
		String oneLine = null;
		
		try {
			
			FileReader adminFileReader = new FileReader(adminLoginFilePath);
			BufferedReader adminBufferedReader = new BufferedReader(adminFileReader);
			
			FileReader customerFileReader = new FileReader(customersLoginFilePath);
			BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
			
			//Check form AdminLogin Text File
			while((oneLine = adminBufferedReader.readLine()) != null && isAdminLoginSuccessful==false) {
				
				String[] arrOfAdmin = oneLine.split("-");
				if(arrOfAdmin[0].equals(username)  && arrOfAdmin[1].equals(password)) {
						System.out.println("\n*Login Successful*\n");
						isAdminLoginSuccessful = true;
					}
            }//end of while
			
			//If username and password doesn't match in AdminLogin.txt
			if(!isAdminLoginSuccessful) {
				
				//Check from CustomerLogin Text File
				while((oneLine = customerBufferedReader.readLine()) != null && isCustomerLoginSuccessful==false) {
					
					String[] arrOfUser = oneLine.split("-");
					if(arrOfUser[0].equals(username)  && arrOfUser[1].equals(password)) {
							System.out.println("\n*Login Successful*\n");
							isCustomerLoginSuccessful = true;
						}
	            }//end of while
				
				if(!isAdminLoginSuccessful && !isCustomerLoginSuccessful) {
					System.out.print("\n*Login Error! Invalid credentials, please try again.*\n");
				}
			}
			
			adminBufferedReader.close();
			customerBufferedReader.close();
			
		}//end of try
		catch(FileNotFoundException ex) {
			System.out.println("*Unable to open file '" + adminLoginFilePath + "' " + ex.getMessage()+"*");
			isAdminLoginSuccessful = false;
			isCustomerLoginSuccessful = false;
		}//end of FileNotFoundException
		catch(IOException ex) {
            System.out.println("Error reading file '" + adminLoginFilePath + "' " + ex.getMessage()+"*");
            isAdminLoginSuccessful = false;
            isCustomerLoginSuccessful = false;
        }//end of IOException
		catch(NullPointerException ex) {
			System.out.println("Error: "+ ex.getMessage()+"*");
			isAdminLoginSuccessful = false;
			isCustomerLoginSuccessful = false;
		}//end of NullPointerException
	}//end of checkUserLogin
	
	
}//end of class