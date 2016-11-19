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
	
	private final Timer timer = new Timer();//��ʱ��
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
	
	/*���û�������Ļʱ��������һ��MotionEvent����MotionEvent�������ڷ���������λ�ú�ʱ���ϸ����Ϣ��
	MotionEvent���󱻴��ݵ������к��ʵķ���������View�����onTouchEvent()�����С�
	MotionEvent�����������ڴ�����ص�ʱ�����У������д��û��״δ�����Ļ��ʼ��������ָ����Ļ������κ��ƶ���ֱ����ָ�뿪��Ļʱ������
	�ƶ������л���������¼���ÿ���¼����������Ӧ��MotionEvent��������¼�����Ĳ�����������λ�á�ʹ�ö���ѹ���������������
	������ʱ��������Ϣ��*/
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
