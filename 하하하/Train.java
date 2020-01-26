package com.leebs.test.hc;

import java.util.Hashtable;
import java.util.function.Function;

public class Train {
	private Hashtable<Integer, Integer> wagons;
	private Function<Integer, Integer> fillWagon;

    public Train(int wagonCount, Function<Integer, Integer> fillWagon) {
        this.wagons = new Hashtable<Integer, Integer>();
    	
        for(int i=0; i<wagonCount; ++i) {
        	//this.wagons.put(i, fillWagon.apply(i));
        }
        
        this.fillWagon = fillWagon;
        
        // this.wagons = IntStream.range(0, wagonCount).boxed().collect(Collectors.toMap(x->x, fillWagon));
        // https://www.testdome.com/t/c1a7a8531f7c4865b491b2c9320c8d9c
    }

    public int peekWagon(int wagonIndex) {
    	if( this.wagons.contains(wagonIndex)) {
    		return this.wagons.get(wagonIndex);
    	} else {
    		this.wagons.put(wagonIndex, this.fillWagon.apply(wagonIndex));
    		return this.fillWagon.apply(wagonIndex);
    	}
    }
    
    public static void main(String[] args) {
        Train train = new Train(Integer.MAX_VALUE, wagonIndex -> wagonIndex);

        for(int i=0; i<Integer.MAX_VALUE; ++i) {
            System.out.println("Wagon: " + i + ", cargo: " + train.peekWagon(i));
        }
    }
}
