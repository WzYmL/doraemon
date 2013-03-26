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
        //��ȥ��ص�ͼ���һ�����β��֣�״̬�����֣�   
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