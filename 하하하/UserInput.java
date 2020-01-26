package com.leebs.test.hc;

public class UserInput {
    public static class TextInput {
    	//StringBuilder sb = new StringBuilder();
    	StringBuffer sb = new StringBuffer();
    	
    	public void add(char c) {
    		sb.append(String.valueOf(c));
    	 }    	
    	public String getValue() {
    		return sb.toString();
    	}
    }

    public static class NumericInput extends TextInput{
    	public void add(char c) {
    		String s = String.valueOf(c);
    		try {
    			Integer.parseInt(s);
    		} catch(NumberFormatException e) {
    			return;
    		}

    		sb.append(String.valueOf(c));
    	 }    	
    	public String getValue() {
    		return sb.toString();
    	}
    }

    public static void main(String[] args) {
        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        System.out.println(input.getValue());
    }
}
