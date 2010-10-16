package com.example.morse;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SimpleMorse extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageView image = (ImageView)findViewById(R.id.button_image);
        image.setOnTouchListener(mOnTouchImageListener);
        
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(mOnButtonClickListener);
    }
    
    
    private View.OnTouchListener mOnTouchImageListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			Log.d("SimpleMorse", "touch");
			return false;
		}
	};
	
	private View.OnClickListener mOnButtonClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Log.d("SimpleMorse", "button click");
		}
	};
}