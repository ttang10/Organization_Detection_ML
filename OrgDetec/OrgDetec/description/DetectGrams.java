package description;

import java.util.ArrayList;

public abstract class DetectGrams {
	
	protected ArrayList<String> grams;

	public DetectGrams() {
		grams = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}
	
	public DetectGrams(ArrayList<String> grams) {
		this.grams = grams;
	}
	
	public abstract boolean detect(String sentence);

}
