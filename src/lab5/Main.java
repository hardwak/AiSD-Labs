package lab5;
import java.util.Comparator;


import sortCode.core.SortingAlgorithm;
import sortCode.testing.*;
import sortCode.testing.comparators.*;
import sortCode.testing.generation.*;
import sortCode.testing.generation.conversion.*;

public class Main {

	public static void main(String[] args) {

		System.out.println("\nBinary Search");
		SortBinarySearch();

		System.out.println("\nMin Max Choice");
		//SortMinMaxChoice();

		System.out.println("\nShaker Sort");
		//SortShaker();
	}

	public static void SortShaker(){
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

		Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(10));

		SortingAlgorithm<MarkedValue<Integer>> algorithm = new SortShaker<MarkedValue<Integer>>(markedComparator);

		Result result = Tester.runNTimes(algorithm, generator, 10, 20);

		allStats(result);
	}

	public static void SortMinMaxChoice(){
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

		Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(10));

		SortingAlgorithm<MarkedValue<Integer>> algorithm = new SortMinMaxChoice<MarkedValue<Integer>>(markedComparator);

		Result result = Tester.runNTimes(algorithm, generator, 10, 20);

		allStats(result);
	}

	public static void SortBinarySearch(){
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

		Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(10));

		SortingAlgorithm<MarkedValue<Integer>> algorithm = new SortBinarySearch<MarkedValue<Integer>>(markedComparator);

		Result result = Tester.runNTimes(algorithm, generator, 15, 20);

		allStats(result);
	}


	public static void exampleBubbleSort(){
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

		Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(10));

		SortingAlgorithm<MarkedValue<Integer>> algorithm = new BubbleSort<MarkedValue<Integer>>(markedComparator);

		Result result = Tester.runNTimes(algorithm, generator, 1000, 50);

		allStats(result);
	}
	
	private static void printStatistic(String label, double average, double stdDev) {
		System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
	}
	
	private static String double2String(double value) {
		return String.format("%.12f", value);
	}

	public static void allStats(Result result){
		printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
		printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
		printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

		System.out.println("always sorted: " + result.sorted());
		System.out.println("always stable: " + result.stable());
	}

}
