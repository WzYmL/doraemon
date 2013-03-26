package org.salever.android.games.two;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.view.Window;
import android.view.WindowManager;

public class GameTwoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        //隐去电池等图标和一切修饰部分（状态栏部分）   
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(new MySurfaceView(this));
        
        Debug.startMethodTracing("yourActivityTrace");
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Debug.stopMethodTracing(); 
    }
}