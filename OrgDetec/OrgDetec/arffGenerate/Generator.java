package arffGenerate;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import description.DetectTerm;

public class Generator {

	final static String FILENAME = "checked.txt";
	final static String OUTPUT = "filtered.txt";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		FileInputStream fstream = new FileInputStream(FILENAME);
		
		System.out.println("Loading file...");
	       
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        String file = "";
        while ((strLine = br.readLine()) != null){
               String[] temp = strLine.split("\t");
               file += temp[1] + ",";
               file += temp[4] + ",";
               file += temp[5] + ",";
               file += temp[9] + ",";
               file += temp[11] + ",";
               file += temp[14] + ",";
               file += temp[17] + ",";
               file += temp[18] + ",";
               file += temp[24] + ",";
               file += temp[temp.length-1] + ",";
               String formatted = "# " + temp[10];
               ArrayList<String> bg1 = new ArrayList<String>();
               bg1.add("#");
               bg1.add("i");
               DetectTerm t1 = new DetectTerm(bg1);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> bg2 = new ArrayList<String>();
               bg2.add("#");
               bg2.add("i'm");
               t1 = new DetectTerm(bg2);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> bg3 = new ArrayList<String>();
               bg3.add("i");
               bg3.add("am");
               t1 = new DetectTerm(bg3);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> bg4 = new ArrayList<String>();
               bg4.add("internet");
               bg4.add("marketing");
               t1 = new DetectTerm(bg4);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> bg5 = new ArrayList<String>();
               bg5.add("#");
               bg5.add("internet");
               t1 = new DetectTerm(bg5);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> bg6 = new ArrayList<String>();
               bg6.add("i");
               bg6.add("love");
               t1 = new DetectTerm(bg6);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> bg7 = new ArrayList<String>();
               bg7.add("#");
               bg7.add("we");
               t1 = new DetectTerm(bg7);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> bg8 = new ArrayList<String>();
               bg8.add("from");
               bg8.add("the");
               t1 = new DetectTerm(bg8);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> bg9 = new ArrayList<String>();
               bg9.add("official");
               bg9.add("twitter");
               t1 = new DetectTerm(bg9);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> bg10 = new ArrayList<String>();
               bg10.add("the");
               bg10.add("official");
               t1 = new DetectTerm(bg10);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug1 = new ArrayList<String>();
               ug1.add("i");
               t1 = new DetectTerm(ug1);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug2 = new ArrayList<String>();
               ug2.add("internet");
               t1 = new DetectTerm(ug2);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug3 = new ArrayList<String>();
               ug3.add("my");
               t1 = new DetectTerm(ug3);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug4 = new ArrayList<String>();
               ug4.add("marketing");
               t1 = new DetectTerm(ug4);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug5 = new ArrayList<String>();
               ug5.add("i'm");
               t1 = new DetectTerm(ug5);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug6 = new ArrayList<String>();
               ug6.add("business");
               t1 = new DetectTerm(ug6);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug7 = new ArrayList<String>();
               ug7.add("from");
               t1 = new DetectTerm(ug7);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug8 = new ArrayList<String>();
               ug8.add("twitter");
               t1 = new DetectTerm(ug8);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug9 = new ArrayList<String>();
               ug9.add("officail");
               t1 = new DetectTerm(ug9);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug10 = new ArrayList<String>();
               ug10.add("news");
               t1 = new DetectTerm(ug10);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug11 = new ArrayList<String>();
               ug11.add("follow");
               t1 = new DetectTerm(ug11);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               ArrayList<String> ug12 = new ArrayList<String>();
               ug12.add("by");
               t1 = new DetectTerm(ug12);
               if(t1.detect(formatted))
            	   file += "True,";
               else
            	   file += "False,";
               file += temp[26].toUpperCase() + "\n";
        }
        
        System.out.println("File loaded.");
        System.out.println("Writing files...");
        
        
        
        try {
        	PrintWriter out = new PrintWriter(OUTPUT);
            out.write(file);
            out.close();
        } catch (IOException e) {
        }
        
        System.out.println("File is wirtten, please find the output file in the folder.");
		
		// TODO Auto-generated method stub

	}

}
