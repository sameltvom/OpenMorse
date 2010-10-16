package com.example.morse;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleMorse extends Activity {
	
	/* When the morse tone started */
	private long start;
	
	/* When the morse tone stopped */
	private long end;
		
	/* Have we pressed but not released? */
	private boolean isPressed = false;
	
	/* The current letter we are building */
	private StringBuilder letter = new StringBuilder();
	
	/* This is where our decoded morse code is presented to the user */
	private EditText editField;
	
	/* This is the morse code for the ongoing letter being generated */
	private TextView morseCodeView;
	
	private Handler handler = new Handler();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageView image = (ImageView)findViewById(R.id.button_image);
        image.setOnTouchListener(mOnTouchImageListener);
        image.setOnClickListener(mOnImageClickListener);
        
        editField = (EditText)findViewById(R.id.edit);
        morseCodeView = (TextView)findViewById(R.id.morse_code);
    }
    
    
    private View.OnTouchListener mOnTouchImageListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (!isPressed) {
				start = SystemClock.uptimeMillis();
				isPressed = true;
				
				/* No need any more to check if we should finish a letter,
				 * because we are now doing a new tone */
				handler.removeCallbacks(finishLetterTask);
				handler.removeCallbacks(finishWordTask);
			}
			return false;
		}
	};
	
	private View.OnClickListener mOnImageClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			end = SystemClock.uptimeMillis();
				
			long toneLength = end-start;
			
			/* A dash or a dot? */
			char tone;
			if (toneLength < Morse.UNIT_TIME) {
				tone = '.';
			} else{
				tone = '-';
			}
			
			letter.append(tone);
			morseCodeView.append(""+tone);
			
			/* Set timer to check later if we should finish the letter and the word */
			handler.postDelayed(finishLetterTask, Morse.SHORT_GAP_TIME);
			handler.postDelayed(finishWordTask, Morse.MEDIUM_GAP_TIME);
			
			/* We are now no longer holding down the morse key */
			isPressed = false;
		}	 
	};

	Runnable finishLetterTask = new Runnable() {
		public void run() {
			char result = Morse.getLetter(letter.toString());
			
			/* Append the letter to the EditText field */
			editField.append(""+result);

			/* Reset the letter */
			letter.delete(0, letter.length());

			/* Reset the morse code presented to the user */
			morseCodeView.setText("");
		}
	};
	
	Runnable finishWordTask = new Runnable() {
		public void run() {
			/* Append a space to the EditText field */
			editField.append(" ");
		}
	};
}