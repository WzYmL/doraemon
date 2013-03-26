/**
 * 
 */
package org.salever.android.step.toast;

import org.salever.android.step.MainActivity;
import org.salever.android.step.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author LiXiaopeng
 * 
 */
public class ToastActivity extends Activity {

	private static final int NOTIFICATION_ID = R.layout.toast_layout;

	private NotificationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toast_layout);
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Button button = (Button) findViewById(R.id.ButtonShowToast);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTitle("短时间显示Toast");
				showToast(Toast.LENGTH_SHORT);
				showNotification();
			}
		});

		button = (Button) findViewById(R.id.ButtonShowNotification);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTitle("长时间显示Toast");
				showToast(Toast.LENGTH_LONG);
			}
		});
	}

	protected void showNotification() {
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		String title = "Toast 测试";
		String content = "Toast 长时间测试";
		Notification notification = new Notification(R.drawable.icon, content,
				System.currentTimeMillis());
		notification.setLatestEventInfo(this, title, content, contentIntent);
		notification.vibrate = new long[] { 100, 200, 100, 200 };
		manager.notify(NOTIFICATION_ID, notification);

	}

	protected void showToast(int type) {
		LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = li.inflate(R.layout.toast_layout, null);
		TextView tv = (TextView) view.findViewById(R.id.TextViewToastContent);
		tv.setText("短时间显示Toast测试");
		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(type);
		toast.show();
	}
}
