package com.wiser.openbookanim;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wiser.anim.AnimTools;
import com.wiser.anim.PhotoMeasureModel;

public class MainActivity extends AppCompatActivity {

	private ImageView	ivClick, ivCover;

	private FrameLayout	flContent;

	private TextView	textView;

	private boolean		isOpen	= true;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ivClick = findViewById(R.id.iv_click);
		ivCover = findViewById(R.id.iv_cover);
		flContent = findViewById(R.id.fl_content);
		textView = findViewById(R.id.text);

		ivClick.setOnClickListener(new View.OnClickListener() {

			@Override public void onClick(View v) {
				PhotoMeasureModel photoMeasureModel = new PhotoMeasureModel();
				photoMeasureModel.width = ivClick.getMeasuredWidth();
				photoMeasureModel.height = ivClick.getMeasuredHeight();
				photoMeasureModel.left = ivClick.getLeft();
				photoMeasureModel.top = ivClick.getTop();
				photoMeasureModel.right = ivClick.getRight();
				photoMeasureModel.bottom = ivClick.getBottom();
				photoMeasureModel.coverBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.cover);
				if (isOpen) {
					isOpen = false;
					AnimTools.openBookAnim(photoMeasureModel, ivCover, flContent, MainActivity.this);
				} else {
					isOpen = true;
					AnimTools.closeBookAnim(photoMeasureModel, ivCover, flContent, MainActivity.this);
				}
			}
		});
	}

}
