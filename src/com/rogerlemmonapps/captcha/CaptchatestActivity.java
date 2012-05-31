package com.rogerlemmonapps.captcha;

import com.rogerlemmonapps.captcha.MathCaptcha.MathOptions;
import com.rogerlemmonapps.captcha.TextCaptcha.TextOptions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CaptchatestActivity extends Activity {

	ImageView im;
	Button btn;
	TextView ans;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        im = (ImageView)findViewById(R.id.imageView1);
        btn = (Button)findViewById(R.id.button1);
        ans = (TextView)findViewById(R.id.textView1);
        
        btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Captcha c = new MathCaptcha(300, 100, MathOptions.PLUS_MINUS_MULTIPLY); 
				//Captcha c = new TextCaptcha(300, 100, 5, TextOptions.NUMBERS_AND_LETTERS);
				im.setImageBitmap(c.image);
				im.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));
				ans.setText(c.answer);
			}
		});
    }
}