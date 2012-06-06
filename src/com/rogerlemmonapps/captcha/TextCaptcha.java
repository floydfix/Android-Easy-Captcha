package com.rogerlemmonapps.captcha;

import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Bitmap.Config;

public class TextCaptcha extends Captcha {
	
	protected TextOptions options;
	private int wordLength;
	
	public enum TextOptions{
		UPPERCASE_ONLY,
		LOWERCASE_ONLY,
		NUMBERS_ONLY,
		LETTERS_ONLY,
		NUMBERS_AND_LETTERS
	}
	
	public TextCaptcha(int wordLength, TextOptions opt){
		TextCaptcha(0, 0, wordLength, opt);
	}
	
	public TextCaptcha(int width, int height, int wordLength, TextOptions opt){
    	setHeight(height);
    	setWidth(width);
    	this.options = opt;
    	usedColors = new ArrayList<Integer>();
    	this.wordLength = wordLength;
    	this.image = image();
	}
	
	@Override
	protected Bitmap image() {
	    LinearGradient gradient = new LinearGradient(0, 0, getWidth() / this.wordLength, getHeight() / 2, color(), color(), Shader.TileMode.MIRROR);
	    Paint p = new Paint();
	    p.setDither(true);
	    p.setShader(gradient);
	    Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
	    Canvas c = new Canvas(bitmap);
	    c.drawRect(0, 0, getWidth(), getHeight(), p);
	    Paint tp = new Paint();
	    tp.setDither(true);
	    tp.setTextSize(getWidth() / getHeight() * 20);
	    
	    Random r = new Random(System.currentTimeMillis());
	    CharArrayWriter cab = new CharArrayWriter();
	    this.answer = "";
		for(int i = 0; i < this.wordLength; i ++){
			int u_l_n = r.nextInt(3);
			char ch = ' ';
		    switch(u_l_n){
		    //UpperCase
		    case 0:
		    	ch = (char)(r.nextInt(91 - 65) + (65));
		    	break;
		    //LowerCase
		    case 1:
		    	ch = (char)(r.nextInt(123 - 97) + (97));
		    	break;
		    //Numbers
		    case 2:
		    	ch = (char)(r.nextInt(58 - 49) + (49));
		    	break;			    	
		    }
			cab.append(ch);
			this.answer += ch;
		}
		
	    char[] data = cab.toCharArray();
	    for (int i=0; i<data.length; i++) {
	    	this.x += (30 - (3 * this.wordLength)) + (Math.abs(r.nextInt()) % (65 - (1.2 * this.wordLength)));
	    	this.y = 50 + Math.abs(r.nextInt()) % 50;
	        Canvas cc = new Canvas(bitmap);
        	tp.setTextSkewX(r.nextFloat() - r.nextFloat());
	        tp.setColor(color());
	        cc.drawText(data, i, 1, this.x, this.y, tp);
	        tp.setTextSkewX(0);
	    }
	    return bitmap;
	}

}
