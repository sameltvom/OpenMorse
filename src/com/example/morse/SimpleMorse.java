package com.example.morse;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class SimpleMorse extends Activity {
	private final int DOT = 0;
	private final int DASH = 1;
	
	/* From wikipedia article Morse code
	 * 
	 * International Morse code is composed of five elements:
     * - short mark, dot or 'dit' (·) — one unit long
     * - longer mark, dash or 'dah' (–) — three units long
     * - inter-element gap between the dots and dashes within a character — one unit long
     * - short gap (between letters) — three units long
     * - medium gap (between words) — seven units long[11]
	 *
	 */
	
	private int UNIT_TIME = 250;
	
	
	/* When the morse tone started */
	private long start;
	
	/* When the morse tone stopped */
	private long end;
	
	/* When the previous morse tone stopped */
	private long previousEnd;
	
	/* Is this the first tone? */
	private boolean firstTone = true;
	
	/* Have we pressed but not released? */
	private boolean isPressed = false;
	
	/* The message we are creating */
	private StringBuilder message = new StringBuilder();
	
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
			end = SystemClock.uptimeMillis();
			
			if (!firstTone) {
				/* Was the previous tone the end of a tone, letter or a word? */
				long timeBetweenTones = start-previousEnd;
				
				Log.d("SimpleMorse", "Time between tones: "+timeBetweenTones);
				
				/* End of letter? */
				if (timeBetweenTones > 3*UNIT_TIME) {
					message.append(' ');
					/* End of word? */
					if (timeBetweenTones > 7*UNIT_TIME) {
						message.append(" | ");
					}
				}
			} else {
				firstTone = false;
			}
			
			
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
			
			message.append(tone);
			Log.d("SimpleMorse", "time: "+diff+" "+tone);
			Log.d("SimpleMorse", "message: "+message.toString());
			previousEnd = end;
		}

		/* Is the tone a dot or a dash? */
		private int dotOrDash(long toneLength) {
			/* If the tone is shorter than UNIT_TIME, it is a dot, otherwise a dash,
			 * actually a dash should be 3 units but whatever... */
			if (toneLength < UNIT_TIME) {
				return DOT;
			} else {
				return DASH;
			}
		} 
	};
}