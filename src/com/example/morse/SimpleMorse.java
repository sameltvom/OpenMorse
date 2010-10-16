package com.example.morse;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
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
	
	/* When the previous morse tone stopped */
	private long previousEnd;
	
	/* Is this the first tone? */
	private boolean firstTone = true;
	
	/* Have we pressed but not released? */
	private boolean isPressed = false;
	
	/* All dashes, dots, spaces and word endings: {'.','-',' ','|'} */
	private StringBuilder morseCode = new StringBuilder();
	
	/* The current letter we are building */
	private StringBuilder letter = new StringBuilder();
	
	/* The letters that are finished */
	private StringBuilder message = new StringBuilder();
	
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
				handler.removeCallbacks(finishLetterOrWordWorker);
				handler.removeCallbacks(finishWordTask);
			}
			return false;
		}
	};
	
	private View.OnClickListener mOnImageClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			end = SystemClock.uptimeMillis();
			
			/* Set timer to check later if we should finish the letter and the word */
			handler.postDelayed(finishLetterOrWordWorker, Morse.SHORT_GAP_TIME);
			handler.postDelayed(finishWordTask, Morse.MEDIUM_GAP_TIME);
			
			if (!firstTone) {
				/* Was the previous tone the end of a tone, letter or a word? */
				long timeBetweenTones = start-previousEnd;
				
				Log.d("SimpleMorse", "Time between tones: "+timeBetweenTones);
				
				if (false) {
				/* End of letter? */
				if (timeBetweenTones > 3*Morse.UNIT_TIME) {
					morseCode.append(' ');
					
					char result = Morse.getLetter(letter.toString());
					message.append(result);
					
					/* Append the letter to the EditText field */
					editField.append(""+result);
				
					/* Reset the letter */
					letter.delete(0, letter.length());
					
					/* Reset the morse code presented to the user */
					morseCodeView.setText("");
					
					/* End of word? */
					if (timeBetweenTones > 7*Morse.UNIT_TIME) {
						morseCode.append(" | ");
						/* Append a space to the EditText field */
						editField.append(" ");
					}
				}
				}
			} else {
				firstTone = false;
			}
			
			
			long diff = end-start;
			isPressed = false;
			
			int toneType = Morse.dotOrDash(diff);
			char tone = ' ';
			switch (toneType) {
			case Morse.DOT:
				tone = '.';
				break;
			case Morse.DASH:
				tone = '-';
			default:
				break;
			}
			
			morseCode.append(tone);
			letter.append(tone);
			morseCodeView.append(""+tone);
			
			Log.d("SimpleMorse", "time: "+diff+" "+tone);
			Log.d("SimpleMorse", "morse code: "+morseCode.toString());
			Log.d("SimpleMorse", "message: "+message.toString());
			
			previousEnd = end;
		}	 
	};

	Runnable finishLetterOrWordWorker = new Runnable() {
		public void run() {
			Log.d("SimpleMorse", "finish letter?");
			/* End of letter? */
			morseCode.append(' ');

			char result = Morse.getLetter(letter.toString());
			message.append(result);

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
			Log.d("SimpleMorse", "finish word?");
			morseCode.append(" | ");
			/* Append a space to the EditText field */
			editField.append(" ");

		}
	};
}