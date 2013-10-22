package ngrams;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Ngrams {
    

    /*Arraylist that we use for storing bigger ngrams*/
    ArrayList<ngram> list = new ArrayList<ngram>();
    /*List of text files to be loaded into the corpora*/
    public static ArrayList<String> fileList =  new ArrayList<String>();
    /*Map for keeping the unigrams*/
    public static HashMap<String, Integer> unigrams = new HashMap<String, Integer>();
    /*List map for all them ngrams*/
    public static HashMap<String, Integer> listMap = new HashMap<String, Integer>();
    /*sentences to analize*/
    public static ArrayList<String> sentences = new ArrayList<String>();
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
    	generateTxt generator = new generateTxt();
        System.out.print("=> Generating file list ... ");
        /*create the file list*/
        /*
        for(int i=1; i<=20; i++){
            fileList.add("C:\\CS\\CS 595 ML\\projectbus\\test"+i+".txt");
        }*/
        fileList.add("test.txt");
        
        System.out.print("done.\n");
        System.out.print("=> Loading and parsing files ... ");
         /*analyze the unigrams and bigrams for each given file*/
       
        /*load up desired files*/ 
        //for(int i=0; i<20; i++){
          //String article = readFile(fileList.get(i)); 
          /*analyze the unigrams and bigrams for each given file*/
          //parseUnigrams(article);
          //parseBigrams(article);
        //}
        String article = readFile(fileList.get(0));
        parseUnigrams(article);
        parseBigrams(article);
        System.out.print("done.\n");
        System.out.print("=> Analyzing sentences ... ");
        
        
        //sentences.add("The company refused to protect the British public.");
        //sentences.add("The Chinese government plans are met.");
       
        ArrayList<sentence> sentencesDone = findSentenceFrequency(sentences);
        //generator.sentences2HTML(sentencesDone);
        generator.sentences2Txt(sentencesDone);
        System.out.print("done.\n");

        System.out.print("=> Generating text raport ... ");
        
        /*generators gonna generate*/
        
        generateTxt.unigrams2Txt(unigrams);
        generateTxt.bigrams2Txt(listMap);
        
        System.out.print("done.\n");
        
        System.out.println("Please find your raport in the folder test");
    }
    
    
    
    /*parse into unigrams hashmap*/
    public static void parseUnigrams(String s){
         String[] sentences = s.split("[.?,!;()]");
        
        /*we don't need any spaces, trim it*/
        for(int i=0; i<sentences.length; i++)
            sentences[i] = sentences[i].trim();
        
        /*for each sentence*/
        for(int i=0; i<sentences.length; i++){   
            /*split by spaces to get words*/
              if(unigrams.containsKey("#")){
                    int occurences = (int)unigrams.get("#")+1;
                    unigrams.put("#", occurences);
                } else unigrams.put("#", 1);    
              
            String words[] = sentences[i].toLowerCase().split(" ");
            for(int j =0; j<words.length-1; j++){      
                
                /*Put a unigram into the list or increase its frequency*/       
                if(unigrams.containsKey(words[j])){
                    int occurences = (int)unigrams.get(words[j])+1;
                    unigrams.put(words[j], occurences);
                } else unigrams.put(words[j], 1);     
            }
       }
        
       for (String key : unigrams.keySet()){   
            String newKey = key;
           
            unigrams.put(key, unigrams.get(key));
        }
    }
    
    /*method parses the text to the global hashmap of ngrams*/
    public static void parseBigrams(String s){
          String[] sentences = s.split("[.?!\";()]");
        
        /*we don't need any spaces, trim it*/
        for(int i=0; i<sentences.length; i++){
            sentences[i] = sentences[i].trim();
            System.out.println(sentences[i]);
        }
        for(int i=0; i<sentences.length; i++){   
            String words[] = sentences[i].toLowerCase().split(" ");
            
            int n =2;
            for(int j =0; j<words.length; j++){
               
              /*get the ngram we're currently iterating on*/
                String gram = "";
                if(j==0)
                    gram ="# "+words[0];
                else 
                    gram = words[j-1] + " " + words[j];
                
              /*add to statistics*/
                if(listMap.containsKey(gram)){
                    int occurences = (int)listMap.get(gram)+1;
                    listMap.put(gram, occurences);      
                } else listMap.put(gram, 1);            
            }
        }
        
        //calculate the probability 
        for (String key : listMap.keySet()){   
            String newKey = key;
         //   double newValue = listMap.get(key)/listMap.size();
            listMap.put(key, listMap.get(key));
        }
    }

    
     /*pass the list of sentences into*/
     public static ArrayList<sentence> findSentenceFrequency(ArrayList<String> sentences){
         ArrayList<sentence> done = new ArrayList<sentence>();
         
         
         for(int i=0; i<sentences.size(); i++){
             /*analize the sentence with unigrams first*/
           
            
             sentence temp = new sentence();  
             
             temp = temp.getDone(sentences.get(i), unigrams, unigrams, 1); 
           
             done.add(temp);
             
           
         }
           for(int i=0; i<sentences.size(); i++){
             /*analize the sentence with unigrams first*/
           
            
             sentence temp = new sentence();  
           
             
             temp = temp.getDone(sentences.get(i),unigrams, listMap, 2); 
           
             done.add(temp);
         }        
         return done;
        
     }
     
        
     /*Simple print*/
     public static void printListMap(){
            Iterator iterator = listMap.keySet().iterator();
            while (iterator.hasNext()) {
               String key = iterator.next().toString();
               String value = listMap.get(key).toString();

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
   
}
