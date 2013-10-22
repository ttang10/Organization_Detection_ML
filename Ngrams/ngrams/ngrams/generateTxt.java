package ngrams;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class generateTxt {
	
    generateTxt(){}
    
    public static void unigrams2Txt(HashMap<String, Integer> input) throws FileNotFoundException{
        generate(input, "unigrams.txt");  
    }
    
    public static void bigrams2Txt(HashMap<String, Integer> input) throws FileNotFoundException{
        generate(input, "bigrams.txt");  
    }
    
    public void sentences2Txt(ArrayList<sentence> sentences) throws FileNotFoundException{
               
         String body ="";
         
         for(sentence temp : sentences){
            
              body+=temp.getFullSentence();
              int i=0;
              for(int x=0; x<temp.getParts().size(); x++){
                   body+= temp.getParts().get(x) + " - " + temp.getFrequencies().get(x) + "/" + temp.getTotals().get(x) + "\n";
              }
            
         }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("sentences.txt"));
            out.write(body);
            out.close();
        } catch (IOException e) {
        }
    }
    
    public static void generate(HashMap<String, Integer> ngrams, String filename) throws FileNotFoundException{
        
        
        String body ="";
        
        /*so lets sort the hashmap first*/
        ArrayList<ngram> sorted = sort(ngrams);
        /*sorted BANG!*/
        
        for(ngram tempG:sorted){  
             String f = String.valueOf(tempG.getFrequency());
             String temp = tempG.getGram() + " - " + f + "\n";
             body+=temp; 
        }

        PrintWriter out = new PrintWriter(filename);
        out.write(body);
        out.close();
    }
    
   /*grabs hashmap with ngrams and then sorts it into array list*/
   public static ArrayList<ngram> sort(HashMap<String, Integer> input){
       ArrayList<ngram> sorted = new ArrayList<ngram>();
       for (String key : input.keySet()){
         ngram temp = new ngram(input.get(key), 0.0, key);
         sorted.add(temp);
        }
       Collections.sort(sorted, new Comparator(){
           @Override
           public final int compare(Object a, Object b){
                return ((ngram)a).frequency - ((ngram)b).frequency;
            }
       });
       
       Collections.reverse(sorted);
      
       return sorted;
   }

}
