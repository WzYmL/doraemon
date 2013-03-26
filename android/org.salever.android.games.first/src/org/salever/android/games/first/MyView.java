/**
 * 
 */
package org.salever.android.games.first;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * @author LiXP
 * 
 */
public class MyView extends View {

	private Paint paint;

	public MyView(Context context) {
		super(context);
		paint = new Paint();
		paint.setAntiAlias(true);// ���û����޾��(��������ÿ��Կ���Ч���ܲ�)
		this.setKeepScreenOn(true);// ���ñ�������
		paint.setColor(Color.RED);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);// ����ˢ����ɫ
		Rect rect = new Rect(30, 30, 50, 50); // ������������������ǿ�ߡ����Ǿ������½ǵ�����
		canvas.drawRect(rect, paint);
		RectF rectF = new RectF(70f, 30f, 90f, 90f);// RectF ֻ�Ǿ��� float��ʽ
													// ֻ�Ǹ�Rect��ȷ�Ȳ�һ��
		canvas.drawArc(rectF, 0, 360, true, paint);
		canvas.drawCircle(150, 30, 20, paint);// ��Ҳ�ǻ�Բ ����������Ϊ�뾶
		float[] points = new float[] { 200f, 10f, 200f, 40f, 300f, 30f, 400f,
				70f };
		canvas.drawLines(points, paint);
		// canvas.drawLines(points, 1, 4, paint);//ѡȡ�ض�������������������һ��ֱ��
		canvas.drawText("Himi", 230, 30, paint);
	}
}
