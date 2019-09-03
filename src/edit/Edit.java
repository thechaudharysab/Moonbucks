package edit;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Edit {
	
	public static void editRecords(String toUpdate, String updated, String filePath) {
		
		//File f = new File(filePath);
		
		try {
			
			BufferedReader file = new BufferedReader(new FileReader(filePath));
			String line;
		    String input = "";
			
		    while ((line = file.readLine()) != null)
		        input += line + System.lineSeparator();

		    input = input.replace(toUpdate, updated);

		    FileOutputStream os = new FileOutputStream(filePath);
		    os.write(input.getBytes());

		    file.close();
		    os.close();
			
		} catch (Exception e) {
			System.out.println("Problem reading file. "+ e);
		}
	}
}