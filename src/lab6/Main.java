package lab6;

import lab5.BubbleSort;
import sortCode.core.SortingAlgorithm;
import sortCode.testing.MarkedValue;
import sortCode.testing.Result;
import sortCode.testing.Tester;
import sortCode.testing.comparators.IntegerComparator;
import sortCode.testing.comparators.MarkedValueComparator;
import sortCode.testing.generation.Generator;
import sortCode.testing.generation.RandomIntegerArrayGenerator;
import sortCode.testing.generation.conversion.LinkedListGenerator;
import sortCode.testing.generation.conversion.MarkingGenerator;

import java.util.Comparator;

public class Main {

	public static void main(String[] args) {

		System.out.println("\nMerging Sort");
		MergingSort();

		System.out.println("\nQuick Sort");
		QuickSort(false);

	}

	public static void QuickSort(boolean randomElement){
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

		Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new LinkedListGenerator<>(new RandomIntegerArrayGenerator(100)));

		SortingAlgorithm<MarkedValue<Integer>> algorithm = new QuickSort<MarkedValue<Integer>>(markedComparator, randomElement);

		Result result = Tester.runNTimes(algorithm, generator, 50, 20);

		allStats(result);
	}

	public static void MergingSort(){
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

		Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new LinkedListGenerator<>(new RandomIntegerArrayGenerator(100)));

		SortingAlgorithm<MarkedValue<Integer>> algorithm = new MergingSort<MarkedValue<Integer>>(markedComparator);

		Result result = Tester.runNTimes(algorithm, generator, 	10, 20);

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
