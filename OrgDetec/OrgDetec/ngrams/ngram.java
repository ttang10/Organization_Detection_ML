
package ngrams;

import java.util.Comparator;

public class ngram{
    public int size; //uni, bi, tri
    int        frequency;
    double     probability;
    String     gram;
    
    public ngram(int frequency, double probability, String gram){
        this.gram = gram;
        this.frequency = frequency;
        this.probability = probability;   
    }
    
    public boolean equals(int frequency, double probability, String gram){
        if(frequency == this.frequency && probability == this.probability && gram.equals(this.gram))
            return true;
        else return false;
    }
    
    public String getGram(){
        return this.gram;
    }
    public int getFrequency(){
        return this.frequency;
    } 
    
}
