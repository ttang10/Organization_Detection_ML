package ngrams;

// this is a test class for n-grams

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
    
    ArrayList<nchar> list2 = new ArrayList<nchar>();
    
    public static HashMap<String, Integer> unicharas = new HashMap<String, Integer>();
    
    public static HashMap<String, Integer> listMap2 = new HashMap<String, Integer>();
    
    public static HashMap<String, Integer> listMap3 = new HashMap<String, Integer>();
    
    public static ArrayList<String> words = new ArrayList<String>();
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
    	generateTxt generator = new generateTxt();
        System.out.print("=> Generating file list ... ");
        /*create the file list*/
        /*
        for(int i=1; i<=20; i++){
            fileList.add("C:\\CS\\CS 595 ML\\projectbus\\test"+i+".txt");
        }*/
        fileList.add("organization.txt");
        
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
        System.out.print("=> Analyzing sentences and words... ");
        
        //String article = readFile(fileList.get(0));
        parseBicharas(article);
        parseTricharas(article);
        System.out.print("done.\n");
        System.out.print("=> Analyzing sentences and words... ");
        
        //sentences.add("The company refused to protect the British public.");
        //sentences.add("The Chinese government plans are met.");
       
        //ArrayList<sentence> sentencesDone = findSentenceFrequency(sentences);
        //ArrayList<word> wordsDone = findWordFrequency(words);
        
        //generator.sentences2HTML(sentencesDone);
        //generator.sentences2Txt(sentencesDone, wordsDone);
        System.out.print("done.\n");

        System.out.print("=> Generating text raport ... ");
        
        /*generators gonna generate*/
        
        generateTxt.unigrams2Txt(unigrams, "unigrams.txt");
        generateTxt.bigrams2Txt(listMap, "bigrams.txt");
        //generateTxt.unicharas2Txt(unicharas);
        generateTxt.bicharas2Txt(listMap2, "bicharas.txt");
        generateTxt.tricharas2Txt(listMap3, "tricharas.txt");
        
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
    
    public static void parseBicharas(String s){
        String[] words = s.split("[.?!\";() ]");
      
      /*we don't need any spaces, trim it*/
      for(int i=0; i<words.length; i++){
          words[i] = words[i].trim();
          System.out.println(words[i]);
      }
      for(int i=0; i<words.length; i++){   
          words[i] = words[i].toLowerCase();
          
          int n =2;
          for(int j =0; j<words[i].length(); j++){
             
            /*get the ngram we're currently iterating on*/
              String chara = "";
              if(j==0)
                  chara ="# "+words[i].charAt(0);
              else 
                  chara = words[i].charAt(j-1) + " " + words[i].charAt(j);
              
            /*add to statistics*/
              if(listMap2.containsKey(chara)){
                  int occurences = (int)listMap2.get(chara)+1;
                  listMap2.put(chara, occurences);      
              } else listMap2.put(chara, 1);            
          }
      }
      
      //calculate the probability 
      for (String key : listMap.keySet()){   
          String newKey = key;
       //   double newValue = listMap.get(key)/listMap.size();
          listMap.put(key, listMap.get(key));
      }
  }
    
    public static void parseTricharas(String s){
        String[] words = s.split("[.?!\";() ]");
      
      /*we don't need any spaces, trim it*/
      for(int i=0; i<words.length; i++){
          words[i] = words[i].trim();
          System.out.println(words[i]);
      }
      for(int i=0; i<words.length; i++){   
          words[i] = words[i].toLowerCase();
          
          int n =3;
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
              if(listMap3.containsKey(chara)){
                  int occurences = (int)listMap3.get(chara)+1;
                  listMap3.put(chara, occurences);      
              } else listMap3.put(chara, 1);            
          }
      }
      
      //calculate the probability 
      for (String key : listMap.keySet()){   
          String newKey = key;
       //   double newValue = listMap.get(key)/listMap.size();
          listMap.put(key, listMap.get(key));
      }
      for (String key : listMap2.keySet()){   
          String newKey = key;
       //   double newValue = listMap.get(key)/listMap.size();
          listMap2.put(key, listMap2.get(key));
      }
      for (String key : listMap3.keySet()){   
          String newKey = key;
       //   double newValue = listMap.get(key)/listMap.size();
          listMap3.put(key, listMap3.get(key));
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
     
     public static ArrayList<word> findWordFrequency(ArrayList<String> words){
         ArrayList<word> done = new ArrayList<word>();
         
         for(int i=0; i<words.size(); i++){
             /*analize the sentence with unigrams first*/
           
            
             word temp = new word();  
             
             temp = temp.getDone(sentences.get(i), unicharas, unicharas, 1); 
           
             done.add(temp);
             
           
         }         
         
         for(int i=0; i<words.size(); i++){
             /*analize the sentence with unigrams first*/
           
            
             word temp = new word();  
             
             temp = temp.getDone(words.get(i), unicharas, listMap2, 2); 
           
             done.add(temp);
             
           
         }
           for(int i=0; i<sentences.size(); i++){
             /*analize the sentence with unigrams first*/
           
            
             word temp = new word();  
           
             
             temp = temp.getDone(sentences.get(i),unigrams, listMap3, 3); 
           
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
     
     public static void printListMap2(){
         Iterator iterator = listMap2.keySet().iterator();
         while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = listMap2.get(key).toString();

            System.out.println(key + " " + value);
         }            
   }
     
     public static void printListMap3(){
         Iterator iterator = listMap3.keySet().iterator();
         while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = listMap3.get(key).toString();

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
