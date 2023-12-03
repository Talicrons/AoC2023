import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ACO2 {

	public static void main(String[] args) {
		
		ArrayList<Game> masterList = setup();
		
		
		//Part1
		int maxR = 12;
		int maxG = 13;
		int maxB = 14;
		int count = 0;
		game: 
		for (Game g : masterList) {
			for (int[] i : g.sets) {
				if ((i[0] <= maxR) && (i[1] <= maxG) && (i[2] <= maxB)) {
					continue;
				} else {
					continue game;
				}
				
			}
			count += g.id;
		}
		System.out.println(count);
		
		
		
		
		
		//Part2
		count = 0;
		for (Game g : masterList) {
			int power = 0;
			int minR = 0;
			int minG = 0;
			int minB = 0;
			for (int[] i : g.sets) {			
				minR = Math.max(minR,i[0]);
				minG = Math.max(minG,i[1]);
				minB = Math.max(minB,i[2]);
			}
			
			power = minR * minG * minB;
			count += power;
//			System.out.println("MinR: "+minR);
//			System.out.println("MinG: "+minG);
//			System.out.println("MinB: "+minB);
//			System.out.println("Power: "+power);
		}
		System.out.println("Total power: "+count);
		
		
	}
	
	
	public static ArrayList<Game> setup(){ //Load input and put into object
		Scanner scanner = new Scanner(AOC1.class.getResourceAsStream("input.txt"));
		String s = null;
		ArrayList<Game> masterList = new ArrayList<>();

        while (scanner.hasNextLine()) {
        	s = scanner.nextLine();
        	
        	int gameID = Integer.parseInt(s.substring(5, s.indexOf(':')));
        	ArrayList<int[]> gameResults = new ArrayList<>();

        	String[] str = s.substring(s.indexOf(':')+2).split(";");
        	for (String set : str) {
        		gameResults.add(parseSet(set));
        	}
        		
        	Game g = new Game(gameID, gameResults);
        	masterList.add(g);
        	
        }
        return masterList;
		
	}
	
	
	//Parse text to int result
	//r,g,b
	public static int[] parseSet(String s) {
		s.split(",");
		int r = 0;
		int g = 0;
		int b = 0;
		
		for (String dice : s.split(",")) {
			if (dice.contains("red")) {
				r = parseResult(dice);
			} else if (dice.contains("green")) {
				g = parseResult(dice);
			} else if (dice.contains("blue")) {
				b = parseResult(dice);
			} else {
				System.out.println("something not right");
			}
				

		}
		int[] i = {r,g,b};
		return i;
		
	}
	
	
	public static int parseResult(String s) {
		String x = "";
    	for (char c : s.toCharArray()) {
    		if (Character.isDigit(c)) {
    			x += c;
    		}
    	}
    	return Integer.parseInt(x);
	}
    	
	
}
	class Game { //Object for each game
		int id;
		ArrayList<int[]> sets; //Each game has X sets played, each set is a int[]
		
		public Game (int id, ArrayList<int[]> i) {
			this.id = id;
			this.sets = i;
		}
		
		
		void print() {
			System.out.println(this.id);
			for (int[] i : sets) {
				for (int j : i) {
					System.out.print(j + ",");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
			
		}
	}

