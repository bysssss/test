package com.leebs.test.hc;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SortedSearch {
	public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
    }
    
    public static int countNumbers(int[] sortedArray, int lessThan) {
    	int count=0;
    	int length = sortedArray.length;
    	
    	// 1)
    	for(int i=0; i<length; ++i) {
    		if( sortedArray[i] < lessThan) {
    			count++;
    		} else {
    			break;
    		}
    	}
    	
    	// 2)    	
    	IntStream stream = Arrays.stream(sortedArray);
    	 stream.forEach(x -> {
    		 if( x<lessThan) {
    			 return;
    		 } else {
    			 return;
    		 }
    	 });
    	 IntStream stream3 = Arrays.stream(sortedArray).parallel();
    	 count = stream3.filter(x -> x<lessThan).map(x -> 1).sum();
    	 
    	 // 3)
    	 // int index = Collections.binarySearch(sortedList, key);
    	 int index = Arrays.binarySearch(sortedArray, lessThan);
    	 if( index < 0) {
    		 count = -1 * index - 1;
    	 } else {
    		 count = index;
    	 }
    	 //count = runBinarySearchIteratively(sortedArray, lessThan, 0, sortedArray.length);
    	
    	return count;
    }
    
    public static int runBinarySearchIteratively(int[] sortedArray, int key, int left, int right) {
    	int index = Integer.MAX_VALUE;
    	
    	while(left <= right) {
    		int mid = (left + right) / 2;
    		if (sortedArray[mid] < key) {
    			left = mid + 1;
    		} else if (sortedArray[mid] > key) {
    			right = mid - 1;
    		} else if (sortedArray[mid] == key) {
    			index = mid;
    			break;
    		}
    	}
		    
    	return index;
    }
}
