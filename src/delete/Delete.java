package delete;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Delete {
	
	public static void deleteRecord(String lineToRemove, String filePath) throws FileNotFoundException, IOException {
		
		File f = new File(filePath);
		StringBuilder sb = new StringBuilder();
		
		try (Scanner sc = new Scanner(f)) {
			
			String currentLine;
			while(sc.hasNext()) {
				currentLine = sc.nextLine();
				if(currentLine.equals(lineToRemove)) {
					continue; //skips lineToRemove
				}
				sb.append(currentLine).append("\n");
			}
		}
		
		PrintWriter pw = new PrintWriter(f);
		pw.close();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
		writer.append(sb.toString());
		writer.close();		
		
	}
	
}
