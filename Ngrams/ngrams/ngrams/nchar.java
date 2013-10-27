
package ngrams;

import java.util.Comparator;

public class nchar{
    public int size; //bi, tri
    int        frequency;
    double     probability;
    String     chara;
    
    public nchar(int frequency, double probability, String chara){
        this.chara = chara;
        this.frequency = frequency;
        this.probability = probability;   
    }
    
    public boolean equals(int frequency, double probability, String chara){
        if(frequency == this.frequency && probability == this.probability && chara.equals(this.chara))
            return true;
        else return false;
    }
    
    public String getGram(){
        return this.chara;
    }
    public int getFrequency(){
        return this.frequency;
    } 
    
}