package com.hrtvbic.adpop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.webkit.WebView;
import android.widget.ImageView;
import com.ant.liao.GifView;
import com.example.adbar.MessageType;
import com.example.adbar.PopThread;

public class MyDockPanelEng {

	private Context context;
	private WindowManager windowManager = null;
	private WindowManager.LayoutParams windowManagerParams = null;
	View root;
	// GifView iv_gif;
	GifView gif;
	ImageView i;
	ImageView image_top;
	ImageView image_inch;
	private BroadcastReceiver myReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub

			if (intent.getAction().equals("com.haier.launcher.HOTKEY.TV")) {
				context.sendBroadcast(new Intent("com.my.ad.bar"));
				Log.d("TAG", "�ź�Դ");
				stop();
			}

		}

	};

	/**
	 * 构�?函数
	 * 
	 * @param context
	 *            Context对象
	 */
	public MyDockPanelEng(Context context) {
		this.context = context;
	}

	public void start() {

		new PopThread(handler).start();
		Log.v("sssssssssssssssssssssssss", "sssssssssssssssssss");

	}

	private void createView() {
		// floatView = new GifView(getApplicationContext());//new
		// FloatView(getApplicationContext());
		// floatView.setOnClickListener(this);
		// floatView.setGifImage(R.drawable.gif3);

		root = LayoutInflater.from(context).inflate(R.layout.eng_layout, null);
		// floatView.setImageResource(R.drawable.ic_launcher); //
		
		//for new e-pop
		/*// ����򵥵����Դ���icon������ʾ
		DisplayMetrics dm = new DisplayMetrics();
		// getWindowManager().getDefaultDisplay().getMetrics(dm);
		dm = context.getResources().getDisplayMetrics();

		float density = dm.density;
		density600 = String.valueOf(density);
		wView = (WebView) root.findViewById(R.id.iv_web);
		wView.setHorizontalScrollBarEnabled(false);
		wView.setVerticalScrollBarEnabled(false);
		if (density600.contains("1.0")) {
			Log.d("mogu", "600");
			wView.loadUrl("file:///android_asset/gif.html");
		} else {
			Log.d("mogu", "801");
			wView.loadUrl("file:///android_asset/gif801.html");
		}
		dm = null;
		density = 0;*/

		// gif = (GifView) root.findViewById(R.id.iv_gif1);
		// gif.setGifImage(R.drawable.k_04);

		i = (ImageView) root.findViewById(R.id.iv_main);
		image_top = (ImageView)root.findViewById(R.id.iv_top);
		image_inch = (ImageView)root.findViewById(R.id.iv_inch);

		AnimationDrawable ad = (AnimationDrawable) i.getDrawable();
		ad.start();
		i.setFocusable(true);
		i.setFocusableInTouchMode(true);
		i.setOnKeyListener(keyListener);
//		wView.setOnKeyListener(keyListener);
		
		
		AnimationSet animationset = new AnimationSet(false);
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
//		alpha.setRepeatCount(Animation.INFINITE);
//		alpha.setRepeatMode(Animation.REVERSE);
		animationset.addAnimation(alpha);
		animationset.setDuration(2000);
//		animationset.setStartOffset(3000);
		animationset.setInterpolator(new AccelerateInterpolator());	
		
		image_top.startAnimation(animationset);
		image_top.setFocusable(true);
		image_top.setFocusableInTouchMode(true);
		image_top.setOnKeyListener(keyListener);
		
		image_inch.startAnimation(animationset);
		image_inch.setFocusable(true);
		image_inch.setFocusableInTouchMode(true);
		image_inch.setOnKeyListener(keyListener);
		
		// ��ȡWindowManager
		windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		// ����LayoutParams(ȫ�ֱ�������ز���
		windowManagerParams = new WindowManager.LayoutParams();
		windowManagerParams.type = LayoutParams.TYPE_PHONE; // ����window type
		windowManagerParams.format = PixelFormat.RGBA_8888; // ����ͼƬ��ʽ��Ч��Ϊ����͸��
		// ����Window flag

		// windowManagerParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
		// | LayoutParams.FLAG_NOT_FOCUSABLE;
		/*
		 * ע�⣬flag��ֵ����Ϊ�� LayoutParams.FLAG_NOT_TOUCH_MODAL ��Ӱ�������¼�
		 * LayoutParams.FLAG_NOT_FOCUSABLE ���ɾ۽� LayoutParams.FLAG_NOT_TOUCHABLE
		 * ���ɴ���
		 */
		// �����������������Ͻǣ����ڵ�������
		// windowManagerParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
		// ����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ
		// windowManagerParams.x = 800;
		// windowManagerParams.y = 0;
		// �����������ڳ�������
		// windowManagerParams.width = 853;
		// windowManagerParams.height =250;
		// ��ʾmyFloatViewͼ��
		windowManager.addView(root, windowManagerParams);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.haier.launcher.HOTKEY.TV");
		context.registerReceiver(myReceiver, intentFilter);
	}

	// ���������������ʱ ���㷵�ص��ı���
	public OnKeyListener keyListener = new OnKeyListener() {

		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == event.ACTION_DOWN) {
				context.sendBroadcast(new Intent("com.my.ad.bar"));
				Log.v("sssssssssssssss", "keyListener+sssssssssssssss");
				stop();
				return true;
			}
			return false;
		}
	};

	public void stop() {
		System.out.println("stop");
		if (root != null & root.getParent() != null) {
			windowManager.removeView(root);
			windowManager = null;
			i = null;
			gif = null;
			System.gc();
			Log.e("mogu", "removeview");
		} else {
			// Toast.makeText(mContext, "stop",
			// Toast.LENGTH_SHORT).show();
		}
	}

	private Handler handler = new Handler() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			// ȫ������
			case MessageType.Pop:
				if (windowManager == null) {
					createView();
				}
				break;

			}
			super.handleMessage(msg);
		}

	};

}
