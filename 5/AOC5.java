import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class AOC5 {

	public static void main(String[] args) {
		AOC5 aoc5 = new AOC5();
        
        List<Long> min = new ArrayList<>();
        for (String i : aoc5.seeds) {
        	min.add(Long.valueOf(aoc5.findLocFromSeed(i)));
        }
        System.out.println("Part1: "+Collections.min(min));  //Part1 ans
        
        
        
//        Long min2 = Long.valueOf(aoc5.findLocFromSeed(aoc5.seedPairs.get(0)));
//        for (String i : aoc5.seedPairs) {
//        	Long j = Long.valueOf(aoc5.findLocFromSeed(i));
//        	if (j<min2) {
//        		min2 = j;
//        	}
//        }             
//        System.out.println(min2); //Part2 ans

	}
	
    AlMap seedToSoil;
    AlMap soilToFertilizer;
    AlMap fertilizerToWater;
    AlMap waterToLight;
    AlMap lightToTemperature;
    AlMap TemperatureToHumidity;
    AlMap humidityToLocation;
    List<String> seeds;
    
    List<String> seedPairs = new ArrayList<>();
	public AOC5(){ //Load input and put into object
		Scanner scanner = new Scanner(AOC1.class.getResourceAsStream("input.txt"));
		String s = "";
		ArrayList<String> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
        	s = scanner.nextLine();
        	list.add(s);        	
        }
        seeds = Arrays.asList(list.get(0).substring(7).split(" "));        

        seedToSoil = new AlMap(list.subList(list.indexOf("seed-to-soil map:")+1, list.indexOf("soil-to-fertilizer map:")-1));
        soilToFertilizer = new AlMap(list.subList(list.indexOf("soil-to-fertilizer map:")+1, list.indexOf("fertilizer-to-water map:")-1));
        fertilizerToWater = new AlMap(list.subList(list.indexOf("fertilizer-to-water map:")+1, list.indexOf("water-to-light map:")-1));
        waterToLight = new AlMap(list.subList(list.indexOf("water-to-light map:")+1, list.indexOf("light-to-temperature map:")-1));
        lightToTemperature = new AlMap(list.subList(list.indexOf("light-to-temperature map:")+1, list.indexOf("temperature-to-humidity map:")-1));
        TemperatureToHumidity = new AlMap(list.subList(list.indexOf("temperature-to-humidity map:")+1, list.indexOf("humidity-to-location map:")-1));
        humidityToLocation = new AlMap(list.subList(list.indexOf("humidity-to-location map:")+1,list.size()));
        
        //Part2 stuff
        Queue<String> seedStack = new LinkedList<>();
        seedStack.addAll(seeds);
        List<Long> part2 = new ArrayList<>();
        while (!(seedStack.isEmpty())) {
        	Long a = Long.valueOf(seedStack.remove());
        	Long b = Long.valueOf(seedStack.remove());
        	//LongStream.range(0L, b).forEach(i -> this.seedPairs.add(Long.toString(i+a)));  //Protip: dont try and add 1.5b numbers into an array
        	
        	Long min2 = Long.valueOf(findLocFromSeed(Long.toString(a)));
        	for (Long i=0L;i<b;i++) {
        		Long j = Long.valueOf(findLocFromSeed(Long.toString(i+a)));
            	if (j<min2) {
            		min2 = j;
            	}
        		
        	}
        	part2.add(min2);        	
        }
        
        
        System.out.println("Part1: "+Collections.min(part2));
        


	}
	
	public String findLocFromSeed(String x) {
	    return humidityToLocation.getDest(TemperatureToHumidity.getDest(lightToTemperature.getDest(waterToLight.getDest(fertilizerToWater.getDest(soilToFertilizer.getDest(seedToSoil.getDest(x)))))));
	}

}


class AlMap {
	List<String> numbers;
	List<String> dest = new ArrayList<>();
	//List<String> source = new ArrayList<>();	
	//List<String> offset = new ArrayList<>();
	List<Long> preCompute = new ArrayList<>();
	List<Long> sourceLong = new ArrayList<>();
	AlMap(List<String> numbers){
		this.numbers = numbers;
		for (String s : numbers) {
			dest.add(s.split(" ")[0]);
			//source.add(s.split(" ")[1]);
			//offset.add(s.split(" ")[2]);
			preCompute.add((Long.sum(Long.valueOf(s.split(" ")[1]), Long.valueOf(s.split(" ")[2]))));
			sourceLong.add(Long.valueOf(s.split(" ")[1]));
		}
	}
	String getDest(String x){
		Long seed = Long.valueOf(x);
		int index = 0;
		for (index=0;index<dest.size();index++) {
			if (!(Long.compare(seed, sourceLong.get(index))<0)) {
				if (!(Long.compare(seed, preCompute.get(index))>0)){	
					break;
				}
			}
		}
		if (index==dest.size()) {
			return x;
		}
		Long result = Long.valueOf(dest.get(index)) + seed - sourceLong.get(index);
		return Long.toString(result);
	}
	void printMap() {
		System.out.println(numbers);
	}
	
}

