package com.leebs.test.hc;

public class ShiningStar {
	// Write the code here
	private Double shineFactor;
	private String name;
    
    public ShiningStar(double shineFactor) {
    	this.shineFactor = shineFactor;
	}
    
    public double shine() {
    	if( shineFactor == null) {
    		throw new IllegalStateException();
    	}
    	return shineFactor;
    }
    
    public void fadeOut() {
    	shineFactor = null;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }

	public static void main(String[] args) {
        // Code for debugging the test case NewStarsCanShine
        double shineFactor = 1d;
        ShiningStar star = new ShiningStar(shineFactor);
        System.out.println(String.format("Are equal: %b, expected: %f, actual: %f", shineFactor == star.shine(), shineFactor, star.shine()));
        
        double shineFactor2 = 1.5d;
        ShiningStar star2 = new ShiningStar(shineFactor2);
        //star2.fadeOut();
        star2.shine();
        System.out.println("star.shine() : " + star2.shine());
        
        double shineFactor3 = 0.8d;
        ShiningStar star3 = new ShiningStar(shineFactor3);
        star3.setName("Sun");
        System.out.println("star3.getName() : " + star3.getName());
    }
}
