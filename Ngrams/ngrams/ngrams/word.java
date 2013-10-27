/*
 * Class containing functions
 * useful for analyzing the sentences
 * accordingly to the given frequency tables.
 */

package ngrams;

import java.util.ArrayList;
import java.util.HashMap;

public class word {
    public  String fullWord;
    public  ArrayList<String>  parts =new ArrayList<String>();
    public  ArrayList<Integer> frequencies = new ArrayList<Integer>();
    public  ArrayList<Integer> totals = new ArrayList<Integer>();
    public  int                totalCount;
    public  int                result;
    
    /* "getAnalised" didn't really look very nice*/
    public word getDone(String word,HashMap<String, Integer> unicharas, HashMap<String, Integer> frequencyMap, int n){
        fullWord = word;
        /*get the total size of the given corpora*/
        this.totalCount = frequencyMap.size();
        
        /*if unigrams*/
        
        if(n==1){
            //String[] words = word.toLowerCase().split(" ");
            for(int j =0; j<word.length(); j++){
            	/*
                words[j]=words[j].replace('.', ' ');
                words[j]=words[j].replace(',', ' ');
                words[j]=words[j].trim();*/
                parts.add(String.valueOf(word.charAt(j)));
                        
                 if(frequencyMap.get(String.valueOf(word.charAt(j)))!=null){
                     
                 
                   frequencies.add(frequencyMap.get(String.valueOf(word.charAt(j))));
                   totals.add(unicharas.size());
                 }
                 else{
                     totals.add(unicharas.size());
                     frequencies.add(1);
                 }
            }
                 
        }
        else if(n==2){
            
          word = word.toLowerCase();
          for(int j =0; j<word.length(); j++){
        	  /*
              words[j]=words[j].replace('.', ' ');
              words[j]=words[j].replace(',', ' ');
              words[j]=words[j].trim();*/
              String first="";
              String chara = "";
                if(j==0){
                    chara ="# "+word.charAt(0);
                    first = "#";
                }else{
                    chara = word.charAt(j-1) + " " + word.charAt(j); 
                    first = String.valueOf(word.charAt(j-1));
                }
                parts.add(chara);
               
                if(frequencyMap.get(chara)!=null){
                  totals.add(unicharas.get(first)+unicharas.size());
                  frequencies.add(frequencyMap.get(chara));
                } else {
                    frequencies.add(1);
                    if(unicharas.get(first)==null)totals.add(unicharas.size());
                    else totals.add(unicharas.get(first)+unicharas.size());
                }
                
             }
        }else if(n==3){
            
            word = word.toLowerCase();
            for(int j =0; j<word.length(); j++){
                String first="";
                String chara = "";
                  if(j==0){
                      chara ="# "+"# "+word.charAt(0);
                      first = "#";
                  }else if(j==1){
                	  chara = "# "+word.charAt(0)+ " "+word.charAt(1);
                  }else{
                      chara = word.charAt(j-2) + " " + word.charAt(j-1) + " " + word.charAt(j); 
                      first = String.valueOf(word.charAt(j-2));
                  }
                  parts.add(chara);
                 
                  if(frequencyMap.get(chara)!=null){
                    totals.add(unicharas.get(first)+unicharas.size());
                    frequencies.add(frequencyMap.get(chara));
                  } else {
                      frequencies.add(1);
                      if(unicharas.get(first)==null)totals.add(unicharas.size());
                      else totals.add(unicharas.get(first)+unicharas.size());
                  }
                  
               }
        }
        
        
        
        
        return this;
    }
    
    public  String getFullWord(){
        return this.fullWord;
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
