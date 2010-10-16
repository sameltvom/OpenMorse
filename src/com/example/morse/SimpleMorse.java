package com.example.morse;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SimpleMorse extends Activity {
	private final int DOT = 0;
	private final int DASH = 1;
	
	/* If the tone is shorter than DOT_TIME, it is a dot, otherwise a dash */
	private int DOT_TIME = 250;
	
	
	/* When the morse tone started */
	private long start;
	
	/* When the morse tone stopped */
	private long end;
	
	/* Have we pressed but not released? */
	private boolean isPressed = false;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageView image = (ImageView)findViewById(R.id.button_image);
        image.setOnTouchListener(mOnTouchImageListener);
        image.setOnClickListener(mOnImageClickListener);
        
    }
    
    
    private View.OnTouchListener mOnTouchImageListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (!isPressed) {
				start = SystemClock.uptimeMillis();
				isPressed = true;
			}
			return false;
		}
	};
	
	private View.OnClickListener mOnImageClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			/* Save the time when the previous tone stopped */
			long previousEnd = end;
			
			end = SystemClock.uptimeMillis();
			long diff = end-start;
			isPressed = false;
			
			int toneType = dotOrDash(diff);
			char tone = ' ';
			switch (toneType) {
			case DOT:
				tone = '.';
				break;
			case DASH:
				tone = '-';
			default:
				break;
			}
			
			Log.d("SimpleMorse", "time: "+diff+" "+tone);
		
			
		}

		/* Is the tone a dot or a dash? */
		private int dotOrDash(long toneLength) {
			if (toneLength < DOT_TIME) {
				return DOT;
			} else {
				return DASH;
			}
		}
	};
}