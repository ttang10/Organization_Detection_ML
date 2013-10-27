/*
 * Class containing functions
 * useful for analyzing the sentences
 * accordingly to the given frequency tables.
 */

package ngrams;

import java.util.ArrayList;
import java.util.HashMap;

public class sentence {
    public  String fullSentence;
    public  ArrayList<String>  parts =new ArrayList<String>();
    public  ArrayList<Integer> frequencies = new ArrayList<Integer>();
    public  ArrayList<Integer> totals = new ArrayList<Integer>();
    public  int                totalCount;
    public  int                result;
    
    /* "getAnalised" didn't really look very nice*/
    public sentence getDone(String sentence,HashMap<String, Integer> unigrams, HashMap<String, Integer> frequencyMap, int n){
        fullSentence = sentence;
        /*get the total size of the given corpora*/
        this.totalCount = frequencyMap.size();
        
        /*if unigrams*/
        if(n==1){
            String[] words = sentence.toLowerCase().split(" ");
            for(int j =0; j<words.length; j++){
                words[j]=words[j].replace('.', ' ');
                words[j]=words[j].replace(',', ' ');
                words[j]=words[j].trim();
                parts.add(words[j]);
                        
                 if(frequencyMap.get(words[j])!=null){
                     
                 
                   frequencies.add(frequencyMap.get(words[j]));
                   totals.add(unigrams.size());
                 }
                 else{
                     totals.add(unigrams.size());
                     frequencies.add(1);
                 }
            }
                 
        }else if(n==2){
            
          String[] words = sentence.toLowerCase().split(" ");
          for(int j =0; j<words.length; j++){
              words[j]=words[j].replace('.', ' ');
              words[j]=words[j].replace(',', ' ');
              words[j]=words[j].trim();
              String first="";
              String gram = "";
                if(j==0){
                    gram ="# "+words[0];
                    first = "#";
                }else{
                    gram = words[j-1] + " " + words[j]; 
                    first = words[j-1];
                }
                parts.add(gram);
               
                if(frequencyMap.get(gram)!=null){
                  totals.add(unigrams.get(first)+unigrams.size());
                  frequencies.add(frequencyMap.get(gram));
                } else {
                    frequencies.add(1);
                    if(unigrams.get(first)==null)totals.add(unigrams.size());
                    else totals.add(unigrams.get(first)+unigrams.size());
                }
                
             }
        }
        
        
        
        
        return this;
    }
    
    public  String getFullSentence(){
        return this.fullSentence;
    }
    public  ArrayList<String> getParts(){
        return this.parts;
    }
    
    public  ArrayList<Integer> getFrequencies(){
        return this.frequencies;
    }
    public  ArrayList<Integer> getTotals(){
        return this.totals;
    }
}
