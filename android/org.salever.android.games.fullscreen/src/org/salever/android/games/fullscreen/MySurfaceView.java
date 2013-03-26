/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *      - initial API and implementation
 *
 * Create on 2011-2-12 下午02:45:18
 *******************************************************************************/
package org.salever.android.games.fullscreen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Button;

/**
 * @author 
 */
public class MySurfaceView extends SurfaceView implements Callback, Runnable {

	private Button button1, button2;
	public static String button_str = "Himi_在SurfaceView中添加组件练习";
	private int move_x = 2, x = 80;
	private Thread th;
	private SurfaceHolder sfh;
	private Canvas canvas;
	private Paint p;

	/*
	 * public MySurfaceView(Context context) { super(context); }//备注1（这里一定要引起注意，仔细看下文对备注1的解释 ）
	 */

	public MySurfaceView(Context context, AttributeSet attrs) {//备注1
		// 如果是new出来的此类实例肯定是没有问题，但是我们为了能在显示SurfaceView同时显示别的组件，所以把自定义的SurfaceView也当作组件注册在了main——xml中，
		// 所以这里需要注意，当在xml中注册的就必须加上这种初始化方法， 初始化的时候会调用这个方法
		// 当时这个问题困扰了一天的研究时间，最后在一个群友的帮助下才发现是这里出了问题
		// 那么第二个参数指的自定义的组件的一些属性,就像长宽一样，你可以给组件属性,就是通过这个来传递的
		super(context, attrs);
		p = new Paint();
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		p.setAntiAlias(true);
		sfh = this.getHolder();
		sfh.addCallback(this);
		th = new Thread(this);
		this.setKeepScreenOn(true);
		setFocusable(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		th.start(); 
	} 
	public void draw() {
		canvas = sfh.lockCanvas();
		canvas.drawColor(Color.WHITE);
		canvas.drawText(button_str, x + move_x, 100, p);
		sfh.unlockCanvasAndPost(canvas);
	}

	private void logic() { // 自己写代码的习惯，用来处理逻辑的函数
		x += move_x;
		if (x > 200 || x < 80) {
			move_x = -move_x;
		}
	}

	@Override
	public boolean onKeyDown(int key, KeyEvent event) {

		return super.onKeyDown(key, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			draw();
			logic();
			try {
				Thread.sleep(100);
			} catch (Exception ex) {
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}
