import java.io.*;
import java.util.Scanner;

public class Login {

	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		
		String adminLoginFile = "src/TextFiles/AdminLogin.txt";
		String oneLine = null;
		

			System.out.print("Welcom to MoonBucks! Please Login to continue:\n\nUsername: ");
			String username = input.next();		
			System.out.print("Password: ");
			String password = input.next();
			
			input.close();
		
		try {
			
			FileReader fileReader = new FileReader(adminLoginFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((oneLine = bufferedReader.readLine()) != null) {
                System.out.println(oneLine);
            }  
			
			bufferedReader.close();
			
		}//end of try
		catch(FileNotFoundException ex) {
			System.out.println(
	                "Unable to open file '" + 
	                		adminLoginFile + "'");    
		}//end of file not found exception
		catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + adminLoginFile + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}

}