package com.wiser.anim;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author Wiser
 * 
 *         放大缩小动画
 */
public class MagnifyShrinkAnim extends Animation {

	private float	mPivotX;

	private float	mPivotY;

	private float	mPivotXValue;	// 控件左上角X

	private float	mPivotYValue;

	private float	scaleX;

	private float	scaleY;

	private boolean	reverse;

	public MagnifyShrinkAnim(float mPivotXValue, float mPivotYValue, float scaleX, float scaleY, boolean reverse) {
		this.mPivotXValue = mPivotXValue;
		this.mPivotYValue = mPivotYValue;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.reverse = reverse;
	}

	@Override protected void applyTransformation(float interpolatedTime, Transformation t) {
		Matrix matrix = t.getMatrix();// 缩放方法
		if (reverse) {// 放大
			matrix.postScale(1 + (scaleX - 1) * interpolatedTime, 1 + (scaleY - 1) * interpolatedTime, mPivotX - mPivotXValue, mPivotY - mPivotYValue);
		} else {// 缩小
			matrix.postScale(1 + (scaleX - 1) * (1.0f - interpolatedTime), 1 + (scaleY - 1) * (1.0f - interpolatedTime), mPivotX - mPivotXValue, mPivotY - mPivotYValue);
		}
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

}
