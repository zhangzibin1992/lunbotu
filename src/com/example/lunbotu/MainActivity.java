package com.example.lunbotu;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;

public class MainActivity extends Activity {
	private MyGallery myGallery=null;
	private ArrayList<Integer> intageList;
	private ArrayList<ImageView> imagelist;
	private int preSelImgIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intageList = new ArrayList<Integer>();
		intageList.add(R.drawable.img1);
		intageList.add(R.drawable.img2);
		intageList.add(R.drawable.img3);
		ImgAdapter adapter = new ImgAdapter(MainActivity.this, intageList);
		myGallery = (MyGallery) findViewById(R.id.mygallery);
		myGallery.setAdapter(adapter);
		myGallery.setFocusable(true);
	}
}
