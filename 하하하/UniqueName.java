package com.leebs.test.hc;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueName {
	 public static String firstUniqueName(String[] names) {
		 Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		 for(String name : names) {
			 if( map.containsKey(name)) {
				 map.put(name, map.get(name)+1);
			 } else {
				 map.put(name, 1);
			 }
		 }
		 
		 Iterator<String> keys = map.keySet().iterator();
		 while( keys.hasNext()) {
			 String key = keys.next();
			 if( map.get(key)==1) {
				 return key;
			 }
		 }
		 
		 return null;
	 }

    public static void main(String[] args) {
        System.out.println(firstUniqueName(new String[] { "Abbi", "Adeline", "Abbi", "Adalia"}));
    }
}
