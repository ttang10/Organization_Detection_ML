package description;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DescrExtra {
	
	final static String FILENAME = "500 labeled.txt";
	final static String OUTPUT1 = "personal.txt";
	final static String OUTPUT2 = "organization.txt";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		ArrayList<String> persons =  new ArrayList<String>();
		ArrayList<String> organizations = new ArrayList<String>();
		
		FileInputStream fstream = new FileInputStream(FILENAME);
		
		System.out.println("Loading file...");
	       
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        String file = "";
        while ((strLine = br.readLine()) != null){
               String[] temp = strLine.split("\t");
               if(temp[temp.length-1].equalsIgnoreCase("P")) 
            	   persons.add(temp[10] + "\n");
               else if(temp[temp.length-1].equalsIgnoreCase("O")) 
            	   organizations.add(temp[10] + "\n");
               }
        
        System.out.println("File loaded.");
        System.out.println("Writing files...");
        
        String body = "";
        
        for(int i=0;i<persons.size();i++)
        	body += persons.get(i);
        
        
        try {
        	PrintWriter out = new PrintWriter(OUTPUT1);
            out.write(body);
            out.close();
        } catch (IOException e) {
        }
        
        body = "";
        
        for(int i=0;i<organizations.size();i++)
        	body += organizations.get(i);
        
        
        try {
        	PrintWriter out = new PrintWriter(OUTPUT2);
            out.write(body);
            out.close();
        } catch (IOException e) {
        }
        
        System.out.println("Files are wirtten, please find the output files in the folder.");
		
		
		// TODO Auto-generated method stub

	}

}
