package com.example.morse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Settings extends Activity implements SeekBar.OnSeekBarChangeListener{
	private SeekBar seekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		seekBar = (SeekBar)findViewById(R.id.settings_seekbar);
		seekBar.setOnSeekBarChangeListener(this);
			
		Bundle extras = getIntent().getExtras();
		
		int speed = extras.getInt("speed");

		/* Transform speed to percentage and then invert -> right is faster i.e. unit time is less */
		int percentage = Math.round(100*(float)(speed-Morse.MIN_UNIT_TIME)/(Morse.MAX_UNIT_TIME-Morse.MIN_UNIT_TIME));
		int progress = 100-percentage;
		
		Log.d("OpenMorse", "speed: "+speed);
		Log.d("OpenMorse", "percentage: "+percentage);
		Log.d("OpenMorse", "progress: "+progress);
		
		
		seekBar.setProgress(progress);
		
		((Button)findViewById(R.id.settings_button)).setOnClickListener(buttonListener);
	}

	
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
        //mProgressText.setText(progress + "=" + fromTouch);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
    
    
    private View.OnClickListener buttonListener = new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			Bundle bundle = new Bundle();

			/* Reverse what we did in onCreate */
			int percentage = 100-seekBar.getProgress();
			int speed = Math.round((Morse.MAX_UNIT_TIME-Morse.MIN_UNIT_TIME)*percentage/(float)100)+Morse.MIN_UNIT_TIME;
			
			Log.d("OpenMorse", "speed: "+speed);
			Log.d("OpenMorse", "percentage: "+percentage);
			Log.d("OpenMorse", "progress: "+seekBar.getProgress());
			
			
            bundle.putInt("speed", speed);
            
            Intent mIntent = new Intent();
            mIntent.putExtras(bundle);
            setResult(RESULT_OK, mIntent);
            finish();

		}
	};
}
