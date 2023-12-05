import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AOC3 {
	
	
	int matrixSizeX = 140;
	int matrixSizeY = 140;
	char[][] inputText = new char[matrixSizeY][matrixSizeX];
	

	public static void main(String[] args) {
		AOC3 aoc3 = new AOC3();
		ArrayList<Point> nums = new ArrayList<>();

		
		for (int y=0;y<aoc3.matrixSizeY;y++) {
			boolean newNum = false;
			for (int x=0;x<aoc3.matrixSizeX;x++) {
				if (Character.isDigit(aoc3.inputText[y][x])){ //Gets a list of all numbers
					if (!newNum) {
						nums.add(new Point(x,y));
						newNum = true;
					}
				} else {
					newNum = false;
				}
			}
		}
		int sum = 0;
		for (Point p : nums) {
			if (aoc3.isPart(p.y,p.x)) { //Check if number is next to symbol
				//System.out.println(pointToNum(inputText, p));
				sum += aoc3.pointToNum(p);
			}
		}

		System.out.println(sum); //============= PART 1 ===========
		
		
		
		

		ArrayList<Point> gears = new ArrayList<>(); //List of * in inputText
		for (int y=0;y<aoc3.matrixSizeY;y++) {
			for (int x=0;x<aoc3.matrixSizeX;x++) {
				if (aoc3.inputText[y][x]=='*') {
					gears.add(new Point(x,y));
				}
			}
		}
		sum = 0;
		for (Point p : gears) {
			Set <Point> i = new HashSet<>();
			if (aoc3.getAdjNumGear(p.y, p.x).size()>1) { //For each *, get points that has at least 2 numbers attached
				i = aoc3.getAdjNumGear(p.y, p.x); //Using set to prevent duplicates. Each set contains a list of coords of adjacent numbers.
				int a = 1;
				for (Point j : i) {
					a *= aoc3.pointToNum(j);
				}
				sum += a;
				if (aoc3.getAdjNumGear(p.y, p.x).size()>2) { //There are none has 3 numbers attached
					System.out.println("!!!!!!!");
				}
			}
		} //Since we iterate based on each * in the input, we don't have to worry about duplicates here
		System.out.println(sum); //======= PART 2 ===========

	}
	
	
	public AOC3(){ //Load input and put into object
		Scanner scanner = new Scanner(AOC1.class.getResourceAsStream("input.txt"));
		String s = "";
		ArrayList<String> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
        	s = scanner.nextLine();
        	list.add(s);        	
        }
		for (int i=0; i<matrixSizeY; i++) {			
			inputText[i] = list.get(i).toCharArray();
		}
		
	}
	
	
	//Check if current number is next to part
	//Breaks if you input not the first digit of a number
	public boolean isPart(int y, int x) {
		if (!Character.isDigit(inputText[y][x])) {
			return false;
		}

		while (Character.isDigit(inputText[y][x])) {
			//System.out.println(inputText[y][x]);
			if (checkAdj(y,x)) {
				return true;
			}
			if (x<matrixSizeX-1) {
				x++;
			} else {
				break;
			}
		}

		return false;
	}
	
	
	
	//Check if chars adjacent is symbol
	public boolean checkAdj(int y, int x) {
	
		if (x!=0) {
			if (isSymbol(inputText[y][x-1])) {
				return true;
			}
		}
		if (y!=0) {
			if (isSymbol(inputText[y-1][x])) {
				return true;
			}
		}
		if (y!=matrixSizeY-1) {
			if (isSymbol(inputText[y+1][x])) {
				return true;
			}
		}
		if (x!=matrixSizeX-1) {
			if (isSymbol(inputText[y][x+1])) {
				return true;
			}
		}
		if (x!=0 && y!=0) {
			if (isSymbol(inputText[y-1][x-1])) {
				return true;
			}
		}
		if (x!=matrixSizeX-1 && y!=0) {
			if (isSymbol(inputText[y-1][x+1])) {
				return true;
			}
		}
		if (x!=0 && y!=matrixSizeY-1) {
			if (isSymbol(inputText[y+1][x-1])) {
				return true;
			}
		}
		if (x!=matrixSizeX-1 && y!=matrixSizeX-1) {
			if (isSymbol(inputText[y+1][x+1])) {
				return true;
			}
		}
		return false;
	}
	
	//Returns a set of numbers adjacent to given point 
	public Set<Point> getAdjNumGear(int y, int x) {
		
		Set <Point> AdjNumList = new HashSet<>();
		
		if (x!=0) {
			if (Character.isDigit(inputText[y][x-1])) {
				AdjNumList.add (getFirst(new Point(x-1,y)));
			}
		}
		if (y!=0) {
			if (Character.isDigit(inputText[y-1][x])) {
				AdjNumList.add (getFirst(new Point(x,y-1)));
			}
		}
		if (y!=matrixSizeY-1) {
			if (Character.isDigit(inputText[y+1][x])) {
				AdjNumList.add (getFirst(new Point(x,y+1)));
			}
		}
		if (x!=matrixSizeX-1) {
			if (Character.isDigit(inputText[y][x+1])) {
				AdjNumList.add (getFirst(new Point(x+1,y)));
			}
		}
		if (x!=0 && y!=0) {
			if (Character.isDigit(inputText[y-1][x-1])) {
				AdjNumList.add (getFirst(new Point(x-1,y-1)));
			}
		}
		if (x!=matrixSizeX-1 && y!=0) {
			if (Character.isDigit(inputText[y-1][x+1])) {
				AdjNumList.add (getFirst(new Point(x+1,y-1)));
			}
		}
		if (x!=0 && y!=matrixSizeY-1) {
			if (Character.isDigit(inputText[y+1][x-1])) {
				AdjNumList.add (getFirst(new Point(x-1,y+1)));
			}
		}
		if (x!=matrixSizeX-1 && y!=matrixSizeX-1) {
			if (Character.isDigit(inputText[y+1][x+1])) {
				AdjNumList.add (getFirst(new Point(x+1,y+1)));
			}
		}
		return AdjNumList;
	}
	
	
	
	
	public boolean isSymbol(char c) {
		if (c == '.') {
			return false;
		}
		if (Character.isDigit(c) || (Character.isLetter(c))) {
			return false;
		}
		return true;
	}	
	
	
	public int pointToNum(Point p) {
		String s = "";
		int x = p.x;
		int y = p.y;
		while (Character.isDigit(inputText[y][x])) {
			s += inputText[y][x];
			
			if (x<matrixSizeX-1) {
				x+=1;
			} else {
				break;
			}

		}

		return Integer.valueOf(s);
	}
	
	//Get the first coord of a given number
	public Point getFirst(int y, int x) {
		if (!Character.isDigit(inputText[y][x])){
			return null;
		}
		if (x==0) {
			return (new Point(x,y));
		}
		while (!(x<=0) && (Character.isDigit(inputText[y][x]))) {
			x--;
			if (x==0) { //THIS IS SO JANK
				x--;
			}
		}
		x++;
		Point p = new Point(x,y);
		return p;
	}
	
	public Point getFirst(Point p) {
		return getFirst(p.y,p.x);
	}
	
}




