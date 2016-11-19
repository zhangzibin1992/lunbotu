package com.example.lunbotu;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class MyGallery extends Gallery{
	
	private Handler handler = new Handler(){
		@SuppressWarnings("deprecation")
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			switch (what) {
			case 100:
				int position = getSelectedItemPosition();
				Log.d("DDD", "position:"+position);
				if(position>=(getCount()-1)){
					onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
				}else{
					onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
				}
				break;
			}
		};
	};
	
	private final Timer timer = new Timer();//计时器
	private final TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			handler.sendEmptyMessage(100);
		}
	};
	
	public MyGallery(Context context) {
		super(context);
		timer.schedule(task, 3000, 3000);
	}
	
	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		timer.schedule(task, 3000, 3000);
	}

	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		timer.schedule(task, 3000, 3000);
	}
	
	/*当用户触摸屏幕时，将创建一个MotionEvent对象。MotionEvent包含关于发生触摸的位置和时间等细节信息。
	MotionEvent对象被传递到程序中合适的方法，比如View对象的onTouchEvent()方法中。
	MotionEvent对象是与用于触摸相关的时间序列，该序列从用户首次触摸屏幕开始，经历手指在屏幕表面的任何移动，直到手指离开屏幕时结束。
	移动过程中会产生大量事件，每个事件都会产生对应的MotionEvent对象，来记录发生的操作、触摸的位置、使用多大的压力、触摸的面积、
	发生的时间等相关信息。*/
	public boolean isScrollingLeft(MotionEvent motionEvent1,MotionEvent motionEvent2){
		float f1 = motionEvent1.getX();
		float f2 = motionEvent2.getX();
		if (f2>f1) 
			return true;
		
		return false;
	}
	
	public boolean onFling(MotionEvent motionEvent1,MotionEvent motionEvent2,float f1,float f2){
		int keyCode;
		if (isScrollingLeft(motionEvent1, motionEvent2)) {
			keyCode = KeyEvent.KEYCODE_DPAD_LEFT;
		}else{
			keyCode = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(keyCode, null);
		
		return true;
	}
	
	public void destroy(){
		timer.cancel();
	}

}
