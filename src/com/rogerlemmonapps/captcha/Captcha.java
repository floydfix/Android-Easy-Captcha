package com.rogerlemmonapps.captcha;

import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Color;

public abstract class Captcha {
	protected Bitmap image;
	protected String answer = "";
	public int width = 300;
	public int height = 100;
	protected int x = 0;
	protected int y = 0;
	
	protected abstract Bitmap image();
	
	static List<Integer> usedColors;
    public static int color(){
    	Random r = new Random();
    	int number;
    	do{
    		number = r.nextInt(9);
    	}while(usedColors.contains(number));
    	usedColors.add(number);
    	switch(number){
	    	case 0: return Color.BLACK;
	    	case 1: return Color.BLUE;
	    	case 2: return Color.CYAN;
	    	case 3: return Color.DKGRAY;
	    	case 4: return Color.GRAY;
	    	case 5: return Color.GREEN;
	    	case 6: return Color.MAGENTA;
	    	case 7: return Color.RED;
	    	case 8: return Color.YELLOW;
	    	case 9: return Color.WHITE;
	    	default: return Color.WHITE;
    	}
    }
    
	public Bitmap getImage() {
		return image;
	}

	public boolean checkAnswer(String ans) {
		return ans.equals(answer);
	}
}
