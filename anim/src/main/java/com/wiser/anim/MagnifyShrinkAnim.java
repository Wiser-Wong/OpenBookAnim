package com.wiser.anim;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author Wiser
 * 
 *         放大缩小动画
 */
public class MagnifyShrinkAnim extends Animation {

	private PhotoMeasureModel	photoMeasureModel;

	private View				contentView;

	private float				mPivotX;

	private float				mPivotY;

	private float				mPivotXValue;				// 控件左上角X

	private float				mPivotYValue;

	private float				scaleX;

	private float				scaleY;

	private boolean				reverse;

	private float				screenWidth, screenHeight;

	private boolean				isFirst	= true;

	public MagnifyShrinkAnim(PhotoMeasureModel photoMeasureModel, View contentView, float scaleX, float scaleY, boolean reverse) {
		this.photoMeasureModel = photoMeasureModel;
		this.contentView = contentView;
		if (photoMeasureModel != null) {
			this.mPivotXValue = photoMeasureModel.left;
			this.mPivotYValue = photoMeasureModel.top;
		}
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.reverse = reverse;
		screenWidth = getScreenWidth(contentView.getContext());
		screenHeight = getScreenHeight(contentView.getContext());
	}

	@Override protected void applyTransformation(float interpolatedTime, Transformation t) {
		System.out.println("-----interpolatedTime--X--" + (1 + (scaleX - 1) * interpolatedTime));
		System.out.println("-----interpolatedTime--Y--" + (1 + (scaleY - 1) * interpolatedTime));
		System.out.println("-----interpolatedTime----" + interpolatedTime);
		System.out.println("-----scaleY----" +scaleY);
		float vScaleX = 1 + (scaleX - 1) * interpolatedTime;
		float vScaleY = 1 + (scaleY - 1) * interpolatedTime;
		ViewGroup.MarginLayoutParams paramsContent = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
		paramsContent.width = (int) (vScaleX * paramsContent.width);
		paramsContent.height = (int) (vScaleY * paramsContent.height);
		paramsContent.leftMargin = (int) (paramsContent.leftMargin / vScaleX);
		paramsContent.topMargin = (int) (paramsContent.topMargin / vScaleY);
		paramsContent.rightMargin = (int) (paramsContent.rightMargin + ((screenWidth - paramsContent.rightMargin) - (screenWidth - paramsContent.rightMargin) / vScaleX));
		paramsContent.bottomMargin = (int) (paramsContent.bottomMargin + ((screenHeight - paramsContent.bottomMargin) - (screenHeight - paramsContent.bottomMargin) / vScaleY));
		contentView.setLayoutParams(paramsContent);
//		System.out.println("-----rightMargin----" + (paramsContent.rightMargin));
//		System.out.println("-----bottomMargin----" + (paramsContent.bottomMargin));
//		System.out.println("-----topMargin----" + (paramsContent.topMargin));
//		System.out.println("-----leftMargin----" + (paramsContent.leftMargin));
//		System.out.println("-----w----" + (screenWidth));
//		System.out.println("-----h----" + (screenHeight));
//		System.out.println("-----width----" + (vScaleX * paramsContent.width));
//		System.out.println("-----height----" + (vScaleY * paramsContent.height));
//		System.out.println("-----left----" + (paramsContent.leftMargin / vScaleX));
//		System.out.println("-----top----" + (paramsContent.topMargin / vScaleY));
//		System.out.println("-----right----" + (paramsContent.rightMargin + ((screenWidth - paramsContent.rightMargin) - (screenWidth - paramsContent.rightMargin) / vScaleX)));
		System.out.println("-----bottom----" + (paramsContent.bottomMargin + ((screenHeight - paramsContent.bottomMargin) - (screenHeight - paramsContent.bottomMargin) / vScaleY)));
//		Matrix matrix = t.getMatrix();// 缩放方法
//		if (reverse) {// 放大
//			matrix.postScale(1 + (scaleX - 1) * interpolatedTime, 1 + (scaleY - 1) * interpolatedTime, mPivotX - mPivotXValue, mPivotY - mPivotYValue);
//		} else {// 缩小
//			matrix.postScale(1 + (scaleX - 1) * (1.0f - interpolatedTime), 1 + (scaleY - 1) * (1.0f - interpolatedTime), mPivotX - mPivotXValue, mPivotY - mPivotYValue);
//		}
	}

	// 缩放点坐标值
	@Override public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mPivotX = resolvePivotX(mPivotXValue, parentWidth, width);
		mPivotY = resolvePivotY(mPivotYValue, parentHeight, height);
	}

	// 缩放点坐标值 缩放点到自身左边距离/缩放点到父控件左边的距离=缩放点自身右侧距离/缩放点到父控件右边的距离
	private float resolvePivotX(float marginLeft, int parentWidth, int width) {
		return (marginLeft * parentWidth) / (parentWidth - width);
	}

	private float resolvePivotY(float marginTop, int parentHeight, int height) {
		return (marginTop * parentHeight) / (parentHeight - height);
	}

	public void reverse() {
		reverse = !reverse;
	}

	public boolean isReverse() {
		return reverse;
	}

	/**
	 * 获得屏幕的宽度
	 *
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获得屏幕的高度
	 *
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

}
