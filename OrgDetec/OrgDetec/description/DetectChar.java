package description;

import java.util.ArrayList;

public class DetectChar extends DetectGrams {

	public DetectChar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DetectChar(ArrayList<String> grams) {
		super(grams);
	}

	@Override
	public boolean detect(String sentence) {
		String chars = "";
		for(int i=0;i<grams.size();i++) {
			chars += grams.get(i);
		}
		String[] temp = sentence.split("[.?!\";() ]");
		for(int i=0;i<temp.length;i++) {
			String t = "#"+temp[i];
			if(t.contains(chars))
				return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
