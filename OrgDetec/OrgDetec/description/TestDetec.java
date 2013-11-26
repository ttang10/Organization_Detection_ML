package description;

// This is a test class for description processing

import java.util.ArrayList;

public class TestDetec {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("#");
		a1.add("is");
		DetectTerm t1 = new DetectTerm(a1);
		if(t1.detect("He is a good student."))
			System.out.println("Right!");
		else
			System.out.println("Wrong!");
		
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add("#");
		a2.add("g");
		a2.add("o");
		DetectChar t2 = new DetectChar(a2);
		if(t2.detect("He is a godlen boy."))
			System.out.println("Right!");
		else
			System.out.println("Wrong!");
		// TODO Auto-generated method stub

	}

}
