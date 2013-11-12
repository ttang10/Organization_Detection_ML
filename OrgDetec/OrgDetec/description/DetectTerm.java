package description;

import java.util.ArrayList;

public class DetectTerm extends DetectGrams {

	public DetectTerm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DetectTerm(ArrayList<String> grams) {
		super(grams);
	}

	@Override
	public boolean detect(String sentence) {
		sentence = "# " + sentence;
		String[] temp = sentence.split("[.?!\";() ]");
		for(int i=0;i<temp.length;i++){
			if(temp[i].equalsIgnoreCase(grams.get(0))) {
				if(grams.size()==1)
					return true;
				if(grams.size()==2)
					if(i<temp.length-1&&temp[i+1].equalsIgnoreCase(grams.get(1)))
						return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

}
