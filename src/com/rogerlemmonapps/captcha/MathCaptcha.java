package com.rogerlemmonapps.captcha;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

public class MathCaptcha extends Captcha {
	
	protected MathOptions options;
	
	public enum MathOptions{
		PLUS_MINUS,
		PLUS_MINUS_MULTIPLY
	}
	
	public MathCaptcha(int width, int height, MathOptions opt){
		this.height = height;
    	this.width = width;
    	options = opt;
		usedColors = new ArrayList<Integer>();
		this.image = image();
	}
	
	@Override
	protected Bitmap image() {
	    int one = 0;
	    int two = 0;
	    int math = 0;

	    LinearGradient gradient = new LinearGradient(0, 0, width / 2, height / 2, color(), color(), Shader.TileMode.MIRROR);
	    Paint p = new Paint();
	    p.setDither(true);
	    p.setShader(gradient);
	    Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    Canvas c = new Canvas(bitmap);
	    c.drawRect(0, 0, width, height, p);
	    
	    LinearGradient fontGrad = new LinearGradient(0, 0, width / 2, height / 2, color(), color(), Shader.TileMode.CLAMP);
	    Paint tp = new Paint();
	    tp.setDither(true);
	    tp.setShader(fontGrad);
	    tp.setTextSize(width / height * 20);
	    Random r = new Random(System.currentTimeMillis());
		one = r.nextInt(9) + 1;
		two = r.nextInt(9) + 1;
		math = r.nextInt((options == MathOptions.PLUS_MINUS_MULTIPLY)?3:2);
		if (one < two) {
			Integer temp = one;
			one = two;
			two = temp;
		}
		
		switch (math) {
			case 0:
				answer = (one + two) + "";
				break;
			case 1:
			    answer = (one - two) + "";
			    break;
			case 2:
			    answer = (one * two) + "";
			    break;
		}
	    char[] data = new char[]{String.valueOf(one).toCharArray()[0], oper(math), String.valueOf(two).toCharArray()[0]};
	    for (int i=0; i<data.length; i++) {
	        x += 30 + (Math.abs(r.nextInt()) % 65);
	        y = 50 + Math.abs(r.nextInt()) % 50;
	        Canvas cc = new Canvas(bitmap);
	        if(i != 1)
	        	tp.setTextSkewX(r.nextFloat() - r.nextFloat());
	        cc.drawText(data, i, 1, x, y, tp);
	        tp.setTextSkewX(0);
	    }
	    return bitmap;
	}
	
	public static char oper(Integer math) {
		switch (math) {
		case 0:
			return '+';
		case 1:
			return '-';
		case 2:
			return '*';
		}
		return '+';
	}
}
