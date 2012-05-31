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
	
	public TextCaptcha(int width, int height, int wordLength, TextOptions opt){
    	this.height = height;
    	this.width = width;
    	options = opt;
    	usedColors = new ArrayList<Integer>();
    	this.wordLength = wordLength;
    	this.image = image();

	}
	
	@Override
	protected Bitmap image() {
	    LinearGradient gradient = new LinearGradient(0, 0, width / wordLength, height / 2, color(), color(), Shader.TileMode.MIRROR);
	    Paint p = new Paint();
	    p.setDither(true);
	    p.setShader(gradient);
	    Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    Canvas c = new Canvas(bitmap);
	    c.drawRect(0, 0, width, height, p);
	    Paint tp = new Paint();
	    tp.setDither(true);
	    tp.setTextSize(width / height * 20);
	    
	    Random r = new Random(System.currentTimeMillis());
	    CharArrayWriter cab = new CharArrayWriter();
	    answer = "";
		for(int i = 0; i < wordLength; i ++){
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
			answer += ch;
		}
		
	    char[] data = cab.toCharArray();
	    for (int i=0; i<data.length; i++) {
	        x += (30 - (3 * wordLength)) + (Math.abs(r.nextInt()) % (65 - (1.2 * wordLength)));
	        y = 50 + Math.abs(r.nextInt()) % 50;
	        Canvas cc = new Canvas(bitmap);
        	tp.setTextSkewX(r.nextFloat() - r.nextFloat());
	        tp.setColor(color());
	        cc.drawText(data, i, 1, x, y, tp);
	        tp.setTextSkewX(0);
	    }
	    return bitmap;
	}

}
