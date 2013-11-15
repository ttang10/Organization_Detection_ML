package execute;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import description.DetectTerm;

import ngrams.generateTxt;
import ngrams.nchar;
import ngrams.ngram;
import ngrams.sentence;
import ngrams.word;

public class Executor {
	
	final static int TOTAL_PROFILES_NUMBER = 3000;
	final static int NORMALIZED_MIN__OCC_FAC = 50;
	final static int NORMALIZED_MIN__DIF_FAC = 50;
	
	final static String FILENAME = "3000 labeled.txt";
	final static String REFINED_FILE = "refined.txt";
	final static String OUTPUT1 = "personal.txt";
	final static String OUTPUT2 = "organization.txt";
	
	final static String CHECKED_FILE = "checked.txt";
	final static String FILTERED_FILE = "twitter.csv";
	
	/*Arraylist that we use for storing bigger ngrams*/
    ArrayList<ngram> list_o = new ArrayList<ngram>();
    /*List of text files to be loaded into the corpora*/
    public static ArrayList<String> fileList_o =  new ArrayList<String>();
    /*Map for keeping the unigrams*/
    public static HashMap<String, Integer> unigrams_o = new HashMap<String, Integer>();
    /*List map for all them ngrams*/
    public static HashMap<String, Integer> listMap_o = new HashMap<String, Integer>();
    /*sentences to analyze*/
    public static ArrayList<String> sentences_o = new ArrayList<String>();
    
    ArrayList<nchar> list2_o = new ArrayList<nchar>();
    
    public static HashMap<String, Integer> unicharas_o = new HashMap<String, Integer>();
    
    public static HashMap<String, Integer> listMap2_o = new HashMap<String, Integer>();
    
    public static HashMap<String, Integer> listMap3_o = new HashMap<String, Integer>();
    
    public static ArrayList<String> words_o = new ArrayList<String>();
    
    /*Arraylist that we use for storing bigger ngrams*/
    ArrayList<ngram> list_p = new ArrayList<ngram>();
    /*List of text files to be loaded into the corpora*/
    public static ArrayList<String> fileList_p =  new ArrayList<String>();
    /*Map for keeping the unigrams*/
    public static HashMap<String, Integer> unigrams_p = new HashMap<String, Integer>();
    /*List map for all them ngrams*/
    public static HashMap<String, Integer> listMap_p = new HashMap<String, Integer>();
    /*sentences to analyze*/
    public static ArrayList<String> sentences_p = new ArrayList<String>();
    
    ArrayList<nchar> list2_p = new ArrayList<nchar>();
    
    public static HashMap<String, Integer> unicharas_p = new HashMap<String, Integer>();
    
    public static HashMap<String, Integer> listMap2_p = new HashMap<String, Integer>();
    
    public static HashMap<String, Integer> listMap3_p = new HashMap<String, Integer>();
    
    public static ArrayList<String> words_p = new ArrayList<String>();
    

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		// remove unknown profiles
		FileInputStream fstream = new FileInputStream(FILENAME);
		
		System.out.println("Loading file...");
		
	    String body = "";   
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;

        while ((strLine = br.readLine()) != null){
               String[] temp = strLine.split("\t");
               if(temp[temp.length-1].equalsIgnoreCase("P")||temp[temp.length-1].equalsIgnoreCase("O")) 
            	   body += strLine + "\n";
               }
        
        System.out.println("File loaded.");
        System.out.println("Writing file...");
        
        
        try {
        	PrintWriter out = new PrintWriter(REFINED_FILE);
            out.write(body);
            out.close();
        } catch (IOException e) {
        }
        
        System.out.println("Unknown profiles removed...");
        
        // write descriptions of personal and organizations into files respectively
        ArrayList<String> persons =  new ArrayList<String>();
		ArrayList<String> organizations = new ArrayList<String>();
		
		FileInputStream fstream1 = new FileInputStream(REFINED_FILE);
		
		System.out.println("Loading file...");
	       
        DataInputStream in1 = new DataInputStream(fstream1);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
        String strLine1;
        while ((strLine1 = br1.readLine()) != null){
               String[] temp = strLine1.split("\t");
               if(temp[temp.length-1].equalsIgnoreCase("P")) 
            	   persons.add(temp[10] + "\n");
               else if(temp[temp.length-1].equalsIgnoreCase("O")) 
            	   organizations.add(temp[10] + "\n");
               }
        
        System.out.println("File loaded.");
        System.out.println("Writing files...");
        
        String body1 = "";
        
        for(int i=0;i<persons.size();i++)
        	body1 += persons.get(i);
        
        
        try {
        	PrintWriter out = new PrintWriter(OUTPUT1);
            out.write(body1);
            out.close();
        } catch (IOException e) {
        }
        
        body1 = "";
        
        for(int i=0;i<organizations.size();i++)
        	body1 += organizations.get(i);
        
        
        try {
        	PrintWriter out = new PrintWriter(OUTPUT2);
            out.write(body1);
            out.close();
        } catch (IOException e) {
        }
        
        System.out.println("Descriptions are classified...");
        
        System.out.print("=> Generating file list ... ");

        fileList_o.add("organization.txt");
        
        System.out.print("done.\n");
        System.out.print("=> Loading and parsing files ... ");
         /*analyze the unigrams and bigrams for each given file*/
       
        /*load up desired files*/ 
        
        String article_o = readFile(fileList_o.get(0));
        parseUnigrams_o(article_o);
        parseBigrams_o(article_o);
        System.out.print("done.\n");
        System.out.print("=> Analyzing sentences and words for organization... ");
        
        //String article = readFile(fileList.get(0));
        parseBicharas_o(article_o);
        parseTricharas_o(article_o);
        System.out.print("done.\n");
        System.out.print("=> Analyzing sentences and words... ");
        
        System.out.print("done.\n");

        System.out.print("=> Generating text reports for organization ... ");
        
        /*generators gonna generate*/
        
        generateTxt.unigrams2Txt(unigrams_o, "unigrams_o.txt");
        generateTxt.bigrams2Txt(listMap_o, "bigrams_o.txt");
        generateTxt.bicharas2Txt(listMap2_o, "bicharas_o.txt");
        generateTxt.tricharas2Txt(listMap3_o, "tricharas_o.txt");
        
        System.out.print("done.\n");
        
        System.out.println("Please find your reports for organization in the folder test");
        
        
        System.out.print("=> Generating file list ... ");
        /*create the file list*/
        fileList_p.add("personal.txt");
        
        System.out.print("done.\n");
        System.out.print("=> Loading and parsing files for personal ... ");
         /*analyze the unigrams and bigrams for each given file*/
       
        /*load up desired files*/ 
        
        String article_p = readFile(fileList_p.get(0));
        parseUnigrams_p(article_p);
        parseBigrams_p(article_p);
        System.out.print("done.\n");
        System.out.print("=> Analyzing sentences and words... ");
        
        parseBicharas_p(article_p);
        parseTricharas_p(article_p);
        System.out.print("done.\n");
        System.out.print("=> Analyzing sentences and words... ");
        
        System.out.print("done.\n");

        System.out.print("=> Generating text reports for personal ... ");
        
        /*generators gonna generate*/
        
        generateTxt.unigrams2Txt(unigrams_p, "unigrams_p.txt");
        generateTxt.bigrams2Txt(listMap_p, "bigrams_p.txt");
        generateTxt.bicharas2Txt(listMap2_p, "bicharas_p.txt");
        generateTxt.tricharas2Txt(listMap3_p, "tricharas_p.txt");
        
        System.out.print("done.\n");
        
        System.out.println("Please find your reports for personal in the folder test");
		// TODO Auto-generated method stub
        
        // find ngrams features
        HashMap<String, Integer> unigram_o = mapping("unigrams_o.txt");
        HashMap<String, Integer> bigram_o = mapping("bigrams_o.txt");
        HashMap<String, Integer> unigram_p = mapping("unigrams_p.txt");
        HashMap<String, Integer> bigram_p = mapping("bigrams_p.txt");
        
        HashMap<String, Integer> newuni_o = new HashMap<String, Integer>();
        HashMap<String, Integer> newuni_p = new HashMap<String, Integer>();
        HashMap<String, Integer> newbi_o = new HashMap<String, Integer>();
        HashMap<String, Integer> newbi_p = new HashMap<String, Integer>();
        
        // normalizing the occurrence
        for(String key: unigram_o.keySet()) {
        	int temp = unigram_o.get(key) * TOTAL_PROFILES_NUMBER / getFileLines("organization.txt");
        	newuni_o.put(key, temp);
        }
        
        for(String key: unigram_p.keySet()) {
        	int temp = unigram_p.get(key) * TOTAL_PROFILES_NUMBER / getFileLines("personal.txt");
        	newuni_p.put(key, temp);
        }
        
        for(String key: bigram_o.keySet()) {
        	int temp = bigram_o.get(key) * TOTAL_PROFILES_NUMBER / getFileLines("organization.txt");
        	newbi_o.put(key, temp);
        }
        
        for(String key: bigram_p.keySet()) {
        	int temp = bigram_p.get(key) * TOTAL_PROFILES_NUMBER / getFileLines("personal.txt");
        	newbi_p.put(key, temp);
        }
        
        ArrayList<String> uniFeatures = mergeMap(newuni_o, newuni_p);
        ArrayList<String> biFeatures = mergeMap(newbi_o, newbi_p);
        
        // check if url contains screen name
        
        ArrayList<String> lines =  new ArrayList<String>();
		ArrayList<String> check = new ArrayList<String>();
		
		FileInputStream fstream2 = new FileInputStream(REFINED_FILE);
		
		System.out.println("Loading file...");
	       
        DataInputStream in2 = new DataInputStream(fstream2);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
        String strLine2;
        while ((strLine2 = br2.readLine()) != null){
               lines.add(strLine2);
               String[] temp = strLine2.split("\t");
               if(urlCheck(temp[16], temp[20])) 
            	   check.add(strLine2 + "\tTrue\n");
               else check.add(strLine2 + "\tFalse\n");
               }
        
        System.out.println("File loaded.");
        System.out.println("Writing file...");
        
        String body2 = "";
        
        for(int i=0;i<check.size();i++)
        	body2 += check.get(i);
        
        try {
        	PrintWriter out = new PrintWriter(CHECKED_FILE);
            out.write(body2);
            out.close();
        } catch (IOException e) {
        }
        
        System.out.println("URL are checked...");
        
        csvGenerator(CHECKED_FILE, FILTERED_FILE, uniFeatures, biFeatures);

	}
	
	/*parse into unigrams hashmap*/
    public static void parseUnigrams_o(String s){
         String[] sentences = s.split("[.?,!;()]");
        
        /*we don't need any spaces, trim it*/
        for(int i=0; i<sentences.length; i++)
            sentences[i] = sentences[i].trim();
        
        /*for each sentence*/
        for(int i=0; i<sentences.length; i++){   
            /*split by spaces to get words*/
              if(unigrams_o.containsKey("#")){
                    int occurences = (int)unigrams_o.get("#")+1;
                    unigrams_o.put("#", occurences);
                } else unigrams_o.put("#", 1);    
              
            String words[] = sentences[i].toLowerCase().split(" ");
            for(int j =0; j<words.length-1; j++){      
                
                /*Put a unigram into the list or increase its frequency*/       
                if(unigrams_o.containsKey(words[j])){
                    int occurences = (int)unigrams_o.get(words[j])+1;
                    unigrams_o.put(words[j], occurences);
                } else unigrams_o.put(words[j], 1);     
            }
       }
        
       for (String key : unigrams_o.keySet())   
            unigrams_o.put(key, unigrams_o.get(key));
    }
    
    /*method parses the text to the global hashmap of ngrams*/
    public static void parseBigrams_o(String s){
          String[] sentences = s.split("[.?!\";()]");
        
        /*we don't need any spaces, trim it*/
        for(int i=0; i<sentences.length; i++)
            sentences[i] = sentences[i].trim();
        for(int i=0; i<sentences.length; i++){   
            String words[] = sentences[i].toLowerCase().split(" ");
            
            for(int j =0; j<words.length; j++){
               
              /*get the ngram we're currently iterating on*/
                String gram = "";
                if(j==0)
                    gram ="# "+words[0];
                else 
                    gram = words[j-1] + " " + words[j];
                
              /*add to statistics*/
                if(listMap_o.containsKey(gram)){
                    int occurences = (int)listMap_o.get(gram)+1;
                    listMap_o.put(gram, occurences);      
                } else listMap_o.put(gram, 1);            
            }
        }
        
        //calculate the probability 
        for (String key : listMap_o.keySet())
            listMap_o.put(key, listMap_o.get(key));
    }
    
    public static void parseBicharas_o(String s){
        String[] words = s.split("[.?!\";() ]");
      
      /*we don't need any spaces, trim it*/
      for(int i=0; i<words.length; i++)
          words[i] = words[i].trim();
      for(int i=0; i<words.length; i++){   
          words[i] = words[i].toLowerCase();
          for(int j =0; j<words[i].length(); j++){
             
            /*get the ngram we're currently iterating on*/
              String chara = "";
              if(j==0)
                  chara ="# "+words[i].charAt(0);
              else 
                  chara = words[i].charAt(j-1) + " " + words[i].charAt(j);
              
            /*add to statistics*/
              if(listMap2_o.containsKey(chara)){
                  int occurences = (int)listMap2_o.get(chara)+1;
                  listMap2_o.put(chara, occurences);      
              } else listMap2_o.put(chara, 1);            
          }
      }
      
      //calculate the probability 
      for (String key : listMap_o.keySet()) 
          listMap_o.put(key, listMap_o.get(key));
  }
    
    public static void parseTricharas_o(String s){
        String[] words = s.split("[.?!\";() ]");
      
      /*we don't need any spaces, trim it*/
      for(int i=0; i<words.length; i++)
          words[i] = words[i].trim();
      for(int i=0; i<words.length; i++){   
          words[i] = words[i].toLowerCase();
          for(int j =0; j<words[i].length(); j++){
             
            /*get the ngram we're currently iterating on*/
              String chara = "";
              if(j==0)
                  chara ="# "+"# "+words[i].charAt(0);
              else if(j==1)
            	  chara ="# "+words[i].charAt(0)+" "+words[i].charAt(1);
              else 
                  chara = +words[i].charAt(j-2) + " " + words[i].charAt(j-1) + " " + words[i].charAt(j);
              
            /*add to statistics*/
              if(listMap3_o.containsKey(chara)){
                  int occurences = (int)listMap3_o.get(chara)+1;
                  listMap3_o.put(chara, occurences);      
              } else listMap3_o.put(chara, 1);            
          }
      }
      
      //calculate the probability 
      for (String key : listMap_o.keySet())
          listMap_o.put(key, listMap_o.get(key));
      for (String key : listMap2_o.keySet())
          listMap2_o.put(key, listMap2_o.get(key));
      for (String key : listMap3_o.keySet())
          listMap3_o.put(key, listMap3_o.get(key));
      
  }

    
     /*pass the list of sentences into*/
     public static ArrayList<sentence> findSentenceFrequency_o(ArrayList<String> sentences){
         ArrayList<sentence> done = new ArrayList<sentence>();
         
         
         for(int i=0; i<sentences.size(); i++){           
            
             sentence temp = new sentence();  
             
             temp = temp.getDone(sentences.get(i), unigrams_o, unigrams_o, 1); 
           
             done.add(temp);
             
           
         }
           for(int i=0; i<sentences.size(); i++){
             /*analyze the sentence with unigrams first*/
           
            
             sentence temp = new sentence();  
           
             
             temp = temp.getDone(sentences.get(i),unigrams_o, listMap_o, 2); 
           
             done.add(temp);
         }        
         return done;
        
     }
     
     public static ArrayList<word> findWordFrequency_o(ArrayList<String> words){
         ArrayList<word> done = new ArrayList<word>();
         
         for(int i=0; i<words.size(); i++){
             /*analyze the sentence with unigrams first*/
           
            
             word temp = new word();  
             
             temp = temp.getDone(sentences_o.get(i), unicharas_o, unicharas_o, 1); 
           
             done.add(temp);
             
           
         }         
         
         for(int i=0; i<words.size(); i++){
             /*analyze the sentence with unigrams first*/
           
            
             word temp = new word();  
             
             temp = temp.getDone(words.get(i), unicharas_o, listMap2_o, 2); 
           
             done.add(temp);
             
           
         }
           for(int i=0; i<sentences_o.size(); i++){
             /*analyze the sentence with unigrams first*/
           
            
             word temp = new word();  
           
             
             temp = temp.getDone(sentences_o.get(i),unigrams_o, listMap3_o, 3); 
           
             done.add(temp);
         }        
         return done;
        
     }
     
        
     /*Simple print*/
     public static void printListMap_o(){
            Iterator iterator = listMap_o.keySet().iterator();
            while (iterator.hasNext()) {
               String key = iterator.next().toString();
               String value = listMap_o.get(key).toString();

               System.out.println(key + " " + value);
            }            
      }
     
     public static void printListMap2_o(){
         Iterator iterator = listMap2_o.keySet().iterator();
         while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = listMap2_o.get(key).toString();

            System.out.println(key + " " + value);
         }            
   }
     
     public static void printListMap3_o(){
         Iterator iterator = listMap3_o.keySet().iterator();
         while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = listMap3_o.get(key).toString();

            System.out.println(key + " " + value);
         }            
   }
	
	/*parse into unigrams hashmap*/
    public static void parseUnigrams_p(String s){
         String[] sentences = s.split("[.?,!;()]");
        
        /*we don't need any spaces, trim it*/
        for(int i=0; i<sentences.length; i++)
            sentences[i] = sentences[i].trim();
        
        /*for each sentence*/
        for(int i=0; i<sentences.length; i++){   
            /*split by spaces to get words*/
              if(unigrams_p.containsKey("#")){
                    int occurences = (int)unigrams_p.get("#")+1;
                    unigrams_p.put("#", occurences);
                } else unigrams_p.put("#", 1);    
              
            String words[] = sentences[i].toLowerCase().split(" ");
            for(int j =0; j<words.length-1; j++){      
                
                /*Put a unigram into the list or increase its frequency*/       
                if(unigrams_p.containsKey(words[j])){
                    int occurences = (int)unigrams_p.get(words[j])+1;
                    unigrams_p.put(words[j], occurences);
                } else unigrams_p.put(words[j], 1);     
            }
       }
        
       for (String key : unigrams_p.keySet()){           
            unigrams_p.put(key, unigrams_p.get(key));
        }
    }
    
    /*method parses the text to the global hashmap of ngrams*/
    public static void parseBigrams_p(String s){
          String[] sentences = s.split("[.?!\";()]");
        
        /*we don't need any spaces, trim it*/
        for(int i=0; i<sentences.length; i++){
            sentences[i] = sentences[i].trim();
            System.out.println(sentences[i]);
        }
        for(int i=0; i<sentences.length; i++){   
            String words[] = sentences[i].toLowerCase().split(" ");
            for(int j =0; j<words.length; j++){
               
              /*get the ngram we're currently iterating on*/
                String gram = "";
                if(j==0)
                    gram ="# "+words[0];
                else 
                    gram = words[j-1] + " " + words[j];
                
              /*add to statistics*/
                if(listMap_p.containsKey(gram)){
                    int occurences = (int)listMap_p.get(gram)+1;
                    listMap_p.put(gram, occurences);      
                } else listMap_p.put(gram, 1);            
            }
        }
        
        //calculate the probability 
        for (String key : listMap_p.keySet())
            listMap_p.put(key, listMap_p.get(key));
    }
    
    public static void parseBicharas_p(String s){
        String[] words = s.split("[.?!\";() ]");
      
      /*we don't need any spaces, trim it*/
      for(int i=0; i<words.length; i++)
          words[i] = words[i].trim();
      for(int i=0; i<words.length; i++){   
          words[i] = words[i].toLowerCase();
          
          for(int j =0; j<words[i].length(); j++){
             
            /*get the ngram we're currently iterating on*/
              String chara = "";
              if(j==0)
                  chara ="# "+words[i].charAt(0);
              else 
                  chara = words[i].charAt(j-1) + " " + words[i].charAt(j);
              
            /*add to statistics*/
              if(listMap2_p.containsKey(chara)){
                  int occurences = (int)listMap2_p.get(chara)+1;
                  listMap2_p.put(chara, occurences);      
              } else listMap2_p.put(chara, 1);            
          }
      }
      
      //calculate the probability 
      for (String key : listMap_p.keySet())
          listMap_p.put(key, listMap_p.get(key));
  }
    
    public static void parseTricharas_p(String s){
        String[] words = s.split("[.?!\";() ]");
      
      /*we don't need any spaces, trim it*/
      for(int i=0; i<words.length; i++){
          words[i] = words[i].trim();
          System.out.println(words[i]);
      }
      for(int i=0; i<words.length; i++){   
          words[i] = words[i].toLowerCase();
          for(int j =0; j<words[i].length(); j++){
             
            /*get the ngram we're currently iterating on*/
              String chara = "";
              if(j==0)
                  chara ="# "+"# "+words[i].charAt(0);
              else if(j==1)
            	  chara ="# "+words[i].charAt(0)+" "+words[i].charAt(1);
              else 
                  chara = +words[i].charAt(j-2) + " " + words[i].charAt(j-1) + " " + words[i].charAt(j);
              
            /*add to statistics*/
              if(listMap3_p.containsKey(chara)){
                  int occurences = (int)listMap3_p.get(chara)+1;
                  listMap3_p.put(chara, occurences);      
              } else listMap3_p.put(chara, 1);            
          }
      }
      
      //calculate the probability 
      for (String key : listMap_p.keySet())
          listMap_p.put(key, listMap_p.get(key));
      for (String key : listMap2_p.keySet())
          listMap2_p.put(key, listMap2_p.get(key));
      for (String key : listMap3_p.keySet())
          listMap3_p.put(key, listMap3_p.get(key));      
  }

    
     /*pass the list of sentences into*/
     public static ArrayList<sentence> findSentenceFrequency_p(ArrayList<String> sentences){
         ArrayList<sentence> done = new ArrayList<sentence>();
         
         
         for(int i=0; i<sentences.size(); i++){
             /*analyze the sentence with unigrams first*/
           
            
             sentence temp = new sentence();  
             
             temp = temp.getDone(sentences.get(i), unigrams_p, unigrams_p, 1); 
           
             done.add(temp);
             
           
         }
           for(int i=0; i<sentences.size(); i++){
             /*analyze the sentence with unigrams first*/
           
            
             sentence temp = new sentence();  
           
             
             temp = temp.getDone(sentences.get(i),unigrams_p, listMap_p, 2); 
           
             done.add(temp);
         }        
         return done;
        
     }
     
     public static ArrayList<word> findWordFrequency_p(ArrayList<String> words){
         ArrayList<word> done = new ArrayList<word>();
         
         for(int i=0; i<words.size(); i++){
             /*analyze the sentence with unigrams first*/
           
            
             word temp = new word();  
             
             temp = temp.getDone(sentences_p.get(i), unicharas_p, unicharas_p, 1); 
           
             done.add(temp);
             
           
         }         
         
         for(int i=0; i<words.size(); i++){
             /*analyze the sentence with unigrams first*/
           
            
             word temp = new word();  
             
             temp = temp.getDone(words.get(i), unicharas_p, listMap2_p, 2); 
           
             done.add(temp);
             
           
         }
           for(int i=0; i<sentences_p.size(); i++){
             /*analyze the sentence with unigrams first*/
           
            
             word temp = new word();  
           
             
             temp = temp.getDone(sentences_p.get(i),unigrams_p, listMap3_p, 3); 
           
             done.add(temp);
         }        
         return done;
        
     }
     
        
     /*Simple print*/
     public static void printListMap_p_p(){
            Iterator iterator = listMap_p.keySet().iterator();
            while (iterator.hasNext()) {
               String key = iterator.next().toString();
               String value = listMap_p.get(key).toString();

               System.out.println(key + " " + value);
            }            
      }
     
     public static void printListMap2_p(){
         Iterator iterator = listMap2_p.keySet().iterator();
         while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = listMap2_p.get(key).toString();

            System.out.println(key + " " + value);
         }            
   }
     
     public static void printListMap3_p(){
         Iterator iterator = listMap3_p.keySet().iterator();
         while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = listMap3_p.get(key).toString();

            System.out.println(key + " " + value);
         }            
   }
        
      public static String readFile(String filename) throws FileNotFoundException, IOException{
              FileInputStream fstream = new FileInputStream(filename);
             
              DataInputStream in = new DataInputStream(fstream);
              BufferedReader br = new BufferedReader(new InputStreamReader(in));
              String strLine;
              String file = "";
              while ((strLine = br.readLine()) != null){
                     file+=strLine;
              }
              return file;
      }
      
      public static HashMap<String, Integer> mapping(String filename) throws FileNotFoundException, IOException{
          FileInputStream fstream = new FileInputStream(filename);
          HashMap<String, Integer> amap = new HashMap<String, Integer>();
         
          DataInputStream in = new DataInputStream(fstream);
          BufferedReader br = new BufferedReader(new InputStreamReader(in));
          String strLine;
          while ((strLine = br.readLine()) != null){
                 String[] temp = strLine.split(" - ");
                 if(temp[0].length()>1&&strLine!=""&&isNumeric(temp[1])) {
                	 String gram = temp[0].trim();
                	 int occurence = Integer.parseInt(temp[1]);
                	 amap.put(gram, occurence);
                 }
          }
          return amap;
      }
      
      public static boolean isNumeric(String str){
    	  for (int i = str.length();--i>=0;){
    		  if (!Character.isDigit(str.charAt(i))){
    			  return false;
    			  }
    		  }
    	  return true;
    	  }
      
      public static int getFileLines(String filename) {
    	  File test= new File(filename);
    	  long fileLength = test.length();
    	  LineNumberReader rf = null; 
		  int lines = 0;
    	  try {
    		  rf = new LineNumberReader(new FileReader(test));
    		  if (rf != null) {
        		  rf.skip(fileLength);
        		  lines = rf.getLineNumber();
        		  rf.close(); 
        	  }
    		  } catch (IOException e) {
    		  if (rf != null) {
    		  try {
    			  rf.close();
    		  } 
    		  catch (IOException ee) {}
    		  }
    		  }
    		  return lines;
      }
      
      public static ArrayList<String> mergeMap(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
    	  ArrayList<String> merged = new ArrayList<String>();
          for(String key: map1.keySet()) {
        	  if(map1.get(key) >= TOTAL_PROFILES_NUMBER/NORMALIZED_MIN__OCC_FAC) {
        		  if(!map2.containsKey(key)) 
        			  merged.add(key);
        		  else if(map1.get(key) - map2.get(key) >= TOTAL_PROFILES_NUMBER/NORMALIZED_MIN__DIF_FAC) 
        			  merged.add(key);
        	  }
          }
          for(String key: map2.keySet()) {
        	  if(map2.get(key) >= TOTAL_PROFILES_NUMBER/NORMALIZED_MIN__OCC_FAC) {
        		  if(!map1.containsKey(key)) 
        			  merged.add(key);
        		  else if(map2.get(key) - map1.get(key) >= TOTAL_PROFILES_NUMBER/NORMALIZED_MIN__DIF_FAC) 
        			  merged.add(key);
        	  }
          }
          return merged;
      }
      
      // check if url contains screen name
      public static boolean urlCheck(String id, String url) {
  		if(url.toLowerCase().contains(id.toLowerCase()))
  			return true;
  		return false;
      }
      
      public static void csvGenerator(String filename, String output, ArrayList<String> unigrams, ArrayList<String> bigrams) throws IOException {
    	  FileInputStream fstream = new FileInputStream(filename);
  		
  		  System.out.println("Loading file...");
  	       
          DataInputStream in = new DataInputStream(fstream);
          BufferedReader br = new BufferedReader(new InputStreamReader(in));
          String strLine;
          String file = "";
          file += "verified,followers_count,protected,statuses_count,friends_count,notification,profile_background_tile,favourites_count,following,url_contain_name";
          for(int i=0;i<unigrams.size();i++) {
        	  file += ",unigram" + i;
          }
          for(int i=0;i<bigrams.size();i++) {
        	  file += ",bigram" + i;
          }
          file += ",class\n";
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
                 
                 for(int i=0;i<unigrams.size();i++) {
                	 ArrayList<String> ug = new ArrayList<String>();
                	 ug.add(unigrams.get(i));
                	 DetectTerm dt = new DetectTerm(ug);
                	 if(dt.detect(temp[10]))
                		 file += "True,";
                	 else
                		 file += "False,";
                 }
                 
                 for(int i=0;i<bigrams.size();i++) {
                	 String[] t = bigrams.get(i).split(" ");
                	 ArrayList<String> bg = new ArrayList<String>();
                	 bg.add(t[0]);
                	 if(t.length==1)
                		 bg.add(" ");
                	 else
                		 bg.add(t[1]);
                	 DetectTerm dt = new DetectTerm(bg);
                	 if(dt.detect(temp[10]))
                		 file += "True,";
                	 else
                		 file += "False,";
                 }
                 
                 file += temp[26].toUpperCase() + "\n";
          }
          
          System.out.println("File loaded.");
          System.out.println("Writing files...");
          
          
          
          try {
          	  PrintWriter out = new PrintWriter(output);
              out.write(file);
              out.close();
          } catch (IOException e) {
          }
          
          System.out.println("CSV file is written, all work is done.");
      }

}
