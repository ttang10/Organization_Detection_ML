package removeUnknown;

// this is a test class for removing unknown profiles

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RemUnkn {
	
	final static String FILENAME = "3000 labeled.txt";
	final static String OUTPUT = "refined.txt";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		FileInputStream fstream = new FileInputStream(FILENAME);
		
		System.out.println("Loading file...");
		
	    String body = "";   
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        String file = "";
        while ((strLine = br.readLine()) != null){
               String[] temp = strLine.split("\t");
               if(temp[temp.length-1].equalsIgnoreCase("P")||temp[temp.length-1].equalsIgnoreCase("O")) 
            	   body += strLine + "\n";
               }
        
        System.out.println("File loaded.");
        System.out.println("Writing file...");
        
        
        try {
        	PrintWriter out = new PrintWriter(OUTPUT);
            out.write(body);
            out.close();
        } catch (IOException e) {
        }
        
        System.out.println("File is wirtten, please find the output file in the folder.");
		
		
		// TODO Auto-generated method stub

	}


}
