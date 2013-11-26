package urlChecking;

// this is a test class to check if url containing screen name

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UrlCheck {

	/**
	 * @param args
	 */
	final static String FILENAME = "refined.txt";
	final static String OUTPUT = "checked.txt";
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<String> lines =  new ArrayList<String>();
		ArrayList<String> check = new ArrayList<String>();
		
		FileInputStream fstream = new FileInputStream(FILENAME);
		
		System.out.println("Loading file...");
	       
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        while ((strLine = br.readLine()) != null){
               lines.add(strLine);
               String[] temp = strLine.split("\t");
               if(urlCheck(temp[16], temp[20])) 
            	   check.add(strLine + "\tTrue\n");
               else check.add(strLine + "\tFalse\n");
               }
        
        System.out.println("File loaded.");
        System.out.println("Writing file...");
        
        String body = "";
        
        for(int i=0;i<check.size();i++)
        	body += check.get(i);
        
        //File newfile = new File(OUTPUT);
        
        try {
        	PrintWriter out = new PrintWriter(OUTPUT);
            out.write(body);
            out.close();
        } catch (IOException e) {
        }
        
        System.out.println("File is wirtten, please find the output file in the folder.");
		
		
		// TODO Auto-generated method stub

	}
	
	public static boolean urlCheck(String id, String url) {
		System.out.println(id + " ********** " + url);
		if(url.toLowerCase().contains(id.toLowerCase()))
			return true;
		return false;
	}

}
