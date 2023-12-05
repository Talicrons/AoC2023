import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class AOC4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(AOC1.class.getResourceAsStream("input.txt"));
		String s = null;
		int sum = 0;
		
		int[] weight = new int[197];
		Arrays.fill(weight,1);
		
		int counter = 0;
        while (scanner.hasNextLine()) {
        	Set <String> card = new HashSet<>();
        	s = scanner.nextLine();
        	counter++;
        	card.addAll(Arrays.asList(s.substring(10).trim().split("\s+"))); //10
        	sum += (int)Math.pow(2, (36-card.size()-1));	//36
        	
        	
        	int score = (36-card.size()); //36 14
        	System.out.println("Card: "+counter+" Score: "+score);
        	//System.out.println("Card: "+counter+" Weight: "+weight[counter-1]);
        	
        	
        	for (int i=0;i<score;i++) {
        		weight[counter+i] = weight[counter+i] + weight[counter-1];
        	}

        	

        }
        System.out.println(Arrays.toString(weight));

        sum = 0;
        for (int i : weight) {
        	sum += i;
        }
        System.out.println(sum);
        
	}
}
