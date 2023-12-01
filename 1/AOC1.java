import java.util.ArrayList;
import java.util.Scanner;

//AOC day 1
public class AOC1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(AOC1.class.getResourceAsStream("input.txt"));
		ArrayList<String> list = new ArrayList<>();
		String s = null;
		
        while (scanner.hasNextLine()) {
        	s = scanner.nextLine();
        	String x = "";
        	s = convertNum(s); //part 2 requirement 
        	
        	for (char c : s.toCharArray()) {
        		if (Character.isDigit(c)) {
        			x += c;
        		}
    		}
        	x = x.charAt(0)+""+ x.charAt(x.length()-1);      
        	list.add(x);
        }
        
        System.out.println(list.toString());
        int total = 0;
        for (String i : list) {
        	total += Integer.parseInt(i);
        }
        System.out.println(total);	

	}
	
	
	public static String convertNum(String s) {
		
		s = s.replaceAll("(twone)", "21");
		s = s.replaceAll("(threeight)", "38");
		s = s.replaceAll("(nineight)", "98");
		s = s.replaceAll("(fiveight)", "58");
		s = s.replaceAll("(oneight)", "18");
		s = s.replaceAll("(eightwo)", "82");
		s = s.replaceAll("(eighthree)", "83");
		s = s.replaceAll("(one)", "1");
		s = s.replaceAll("(two)", "2");
		s = s.replaceAll("(three)", "3");
		s = s.replaceAll("(four)", "4");
		s = s.replaceAll("(five)", "5");
		s = s.replaceAll("(six)", "6");
		s = s.replaceAll("(seven)", "7");
		s = s.replaceAll("(eight)", "8");
		s = s.replaceAll("(nine)", "9");
		
		
		return s;
	}

}



