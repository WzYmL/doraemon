package org.salever.android.step.musicplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	public static final String MUSIC_PLAY_SERVICE = "org.salever.android.step.musicplay.START_AUDIO_SERVICE";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button;
        button = (Button) findViewById(R.id.ButtonPlay);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startPlay();
			}
		});
        
        button = (Button) findViewById(R.id.ButtonStop);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopPlay();
			}
		});
    }

	/**
	 * 
	 */
	protected void stopPlay() {
		stopService(new Intent(MUSIC_PLAY_SERVICE));
		finish();
	}

	/**
	 * 
	 */
	protected void startPlay() {
		startService(new Intent(MUSIC_PLAY_SERVICE));
	}
}