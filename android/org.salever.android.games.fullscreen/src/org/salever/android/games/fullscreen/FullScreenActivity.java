package org.salever.android.games.fullscreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class FullScreenActivity extends Activity implements OnClickListener {  
    /** Called when the activity is first created. */  
    private Button button1, button2;  
    private TextView tv ;  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//隐去标题（应用的名字)  
        //此设定必须要写在setContentView之前，否则会有异常）  
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        setContentView(R.layout.main); //要先显示,然后再对其组件取出、处理操作  
        tv=(TextView)findViewById(R.id.textview);   
        button1 = (Button) findViewById(R.id.button1);  
        button1.setOnClickListener(this);//这里是监听按键，因为本类使用了OnClickListener接口  
        button2 = (Button) findViewById(R.id.button2);  
        button2.setOnClickListener(this);   
        /* 其实大家也可以不用本类使用接口，可以内部类来完成。 
         * 以下是不使用OnClickListener接口的绑定监听方式; 
        button2.setOnClickListener(new OnClickListener() { 
             
            @Override 
            public void onClick(View v) { 
                //这里处理按键操作 
                 
            } 
        }); 
        */   
    }   
    @Override  
    public void onClick(View v) {  
        if (v == button1) {  
            MySurfaceView.button_str = "button 1被触发";  
            tv.setText("button 1被触发");  
        } else if (v == button2) {  
            MySurfaceView.button_str = "button 2被触发";  
            tv.setText("button 2被触发");  
        }  
    }  
}  