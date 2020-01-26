package com.leebs.test.hc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStream {
	public static void main( String[] args ) throws Exception {
    	Test Test = new Test();
    	Test.test();
    }
}

class Test {
	public void test( ) {
		//collectCount = ccList.stream().filter(cc -> cc != null).mapToInt(cc -> cc).sum();
		//collectRateSince24 = ccRateList.stream().filter(ccRate -> ccRate != null).mapToDouble(ccRate -> ccRate).average().orElse(0.0d);
		//value = valueList.stream().filter(val -> val != null).mapToDouble(val -> val).average().orElse(0.0d);
		//n = valueList.stream().filter(val -> val != null).mapToDouble(val -> val).summaryStatistics().getCount();
		//avgValue = valueList.stream().filter(val -> val != null).mapToDouble(val -> val).summaryStatistics().getAverage();
		//stdDeviation = Math.sqrt(valueList.stream().filter(val -> val != null).mapToDouble(val -> Math.pow(val - temp, 2)).average().orElse(0.0d));
		//anomalyCode = anomalyCodeList.stream().filter(code -> code != null).mapToInt(code -> code).max().orElse(2);
		//anomalyScore = anomalyScoreList.stream().filter(score -> score != null).mapToDouble(score -> score).average().orElse(0.0d);
		
		List<String> list = Arrays.asList("a11", "a1", "a111", "a2", "b1", "c2", "c1", "a11", "a0");
		list.stream().filter(s -> s.startsWith("a")).map(String::toUpperCase).sorted().forEach(System.out::println);
		System.out.println("----------------------------------------------------");
		
		Stream.of("a1", "a2", "a3").map(s -> s.substring(1)).mapToInt(Integer::parseInt).max().ifPresent(System.out::println);
		IntStream.range(1, 4).mapToObj(i -> "a" + i).forEach(System.out::print);
		System.out.println("\n----------------------------------------------------");
		
		Stream.of("d2", "a2", "b2", "b1", "b3", "c").filter(s -> {
	        return s.startsWith("b");
	    })
	    .sorted((s1, s2) -> {
	        System.out.printf("sort: %s; %s\n", s1, s2);
	        return s1.compareTo(s2) * -1;
	    })
	    .map(s -> {
	        return s.toUpperCase();
	    })
	    .forEach(s -> System.out.println("forEach: " + s));
		System.out.println("----------------------------------------------------");
		
		Arrays.asList("a1", "b1", "a2", "c2", "a0", "c1")
		.stream()	// .stream() .parallelStream()
	    .filter(s -> {
	        return true;
	    })
	    .map(s -> {
	        return s.toUpperCase();
	    })
	    .sorted((s1, s2) -> {
	        System.out.format("sort: %s <> %s [%s]\n",s1, s2, Thread.currentThread().getName());
	        return s1.compareTo(s2);
	    })
	    .forEach(s -> System.out.format("forEach: %s [%s]\n",s, Thread.currentThread().getName()));
		System.out.println("----------------------------------------------------");
		
		List<Man> manList = new ArrayList<>();
		IntStream.range(1, 4).forEach(i -> manList.add(new Man("man_" + i)));
		manList.stream().forEach(m ->IntStream.range(1, 4).forEach(i -> m.itemList.add(new Item("item_" + i + " <- " + m.name))));
		manList.stream().flatMap(m -> m.itemList.stream()).forEach(t -> System.out.println(t.name));
        
		return;
    }
	
	class Man {
	    String name;
	    List<Item> itemList = new ArrayList<>();

	    Man(String name) {
	        this.name = name;
	    }
	}
	class Item {
	    String name;

	    Item(String name) {
	        this.name = name;
	    }
	}
	
}
