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
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//��ȥ���⣨Ӧ�õ�����)  
        //���趨����Ҫд��setContentView֮ǰ����������쳣��  
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        setContentView(R.layout.main); //Ҫ����ʾ,Ȼ���ٶ������ȡ�����������  
        tv=(TextView)findViewById(R.id.textview);   
        button1 = (Button) findViewById(R.id.button1);  
        button1.setOnClickListener(this);//�����Ǽ�����������Ϊ����ʹ����OnClickListener�ӿ�  
        button2 = (Button) findViewById(R.id.button2);  
        button2.setOnClickListener(this);   
        /* ��ʵ���Ҳ���Բ��ñ���ʹ�ýӿڣ������ڲ�������ɡ� 
         * �����ǲ�ʹ��OnClickListener�ӿڵİ󶨼�����ʽ; 
        button2.setOnClickListener(new OnClickListener() { 
             
            @Override 
            public void onClick(View v) { 
                //���ﴦ�������� 
                 
            } 
        }); 
        */   
    }   
    @Override  
    public void onClick(View v) {  
        if (v == button1) {  
            MySurfaceView.button_str = "button 1������";  
            tv.setText("button 1������");  
        } else if (v == button2) {  
            MySurfaceView.button_str = "button 2������";  
            tv.setText("button 2������");  
        }  
    }  
}  