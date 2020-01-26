package com.leebs.test.hc;

import java.util.LinkedList;
import java.util.List;

public class TrainComposition {
	static List<Integer> gList = new LinkedList<Integer>();
	
    public static void main( String[] args ) throws Exception {
    	TrainComposition tree = new TrainComposition();
        tree.attachWagonFromLeft(7);
        tree.attachWagonFromLeft(13);
        System.out.println(tree.detachWagonFromRight()); // 7 
        System.out.println(tree.detachWagonFromLeft()); // 13
    }
    
    public void attachWagonFromLeft(int wagonId) {
    	gList.add(0, wagonId);
    }

    public void attachWagonFromRight(int wagonId) {
    	gList.add(wagonId);
    }

    public int detachWagonFromLeft() {
    	int res = gList.get(0);
    	gList.remove(0);
    	return res;
    }

    public int detachWagonFromRight() {
    	int res = gList.get(gList.size()-1);
    	gList.remove(gList.size()-1);
    	return res;
    }
}
