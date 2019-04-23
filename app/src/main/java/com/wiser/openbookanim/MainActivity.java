package com.wiser.openbookanim;

import com.wiser.anim.AnimTools;
import com.wiser.anim.OpenBookAnimationListener;
import com.wiser.anim.PhotoMeasureModel;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private ImageView	ivClick, ivCover;

	private FrameLayout	flContent;

	private boolean		isOpen	= true;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ivClick = findViewById(R.id.iv_click);
		ivCover = findViewById(R.id.iv_cover);
		flContent = findViewById(R.id.fl_content);
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
				AnimTools.setDuration(1500);
				if (isOpen) {
					isOpen = false;
					AnimTools.openBookAnim(photoMeasureModel, ivCover, flContent, MainActivity.this, new OpenBookAnimationListener() {

						@Override public void startAnim(Animation animation) {
							super.startAnim(animation);
							flContent.setVisibility(View.VISIBLE);
						}

						@Override public void endAnim(Animation animation) {
							Toast.makeText(MainActivity.this, "打开动画结束", Toast.LENGTH_LONG).show();
						}
					});
				} else {
					isOpen = true;
					AnimTools.closeBookAnim(photoMeasureModel, ivCover, flContent, MainActivity.this, new OpenBookAnimationListener() {

						@Override public void endAnim(Animation animation) {
							flContent.setVisibility(View.GONE);
							Toast.makeText(MainActivity.this, "关闭动画结束", Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		});
	}

}
