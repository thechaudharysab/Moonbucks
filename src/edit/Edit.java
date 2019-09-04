package edit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;



public class Edit {
	
	public static void editRecords(String toUpdate, String updated, String filePath) {
				
		try {
			
			FileInputStream fstream = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String currentLine = "";
			StringBuilder fileContent = new StringBuilder();
			
			while((currentLine = br.readLine()) != null) {
				
				if(currentLine.equals(toUpdate)) {
					String newLine = updated + System.lineSeparator();
					fileContent.append(newLine);
				} else {
                    // update content as it is
                    fileContent.append(currentLine);
                    fileContent.append("\n");
                }
			}
			br.close();
			
			FileWriter fstreamWrite = new FileWriter(filePath);
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            //Close the input stream
			
			
		} catch (Exception e) {
			System.out.println("Problem reading file. "+ e);
		}
	}//Function Ends
}