package com.wiser.anim;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author Wiser
 * 
 *         打开书翻开动画
 */
public class OpenBookRotate3DAnim extends Animation {

	private float	mFromDegrees;

	private float	mToDegrees;

	private float	mMarginLeft;

	private float	mMarginTop;

	private float	mScaleX;

	private float	mScaleY;

	private boolean	reverse;

	private Camera	mCamera;

	// 旋转中心
	private float	mPivotX;

	private float	mPivotY;

	private float	scale	= 1;	// <------- 像素密度

	public OpenBookRotate3DAnim(Context context, float mFromDegrees, float mToDegrees, float mMarginLeft, float mMarginTop, float mScaleX, float mScaleY, boolean reverse) {
		this.mFromDegrees = mFromDegrees;
		this.mToDegrees = mToDegrees;
		this.mMarginLeft = mMarginLeft;
		this.mMarginTop = mMarginTop;
		this.mScaleX = mScaleX;
		this.mScaleY = mScaleY;
		this.reverse = reverse;

		// 获取手机像素密度 （即dp与px的比例）
		scale = context.getResources().getDisplayMetrics().density;
	}

	@Override public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);

		mCamera = new Camera();
		mPivotX = calculatePivotX(mMarginLeft, parentWidth, width);
		mPivotY = calculatePivotY(mMarginTop, parentHeight, height);
	}

	@Override protected void applyTransformation(float interpolatedTime, Transformation t) {
		super.applyTransformation(interpolatedTime, t);

		float degrees = reverse ? mToDegrees + (mFromDegrees - mToDegrees) * interpolatedTime : mFromDegrees + (mToDegrees - mFromDegrees) * interpolatedTime;
		Matrix matrix = t.getMatrix();

		Camera camera = mCamera;
		camera.save();
		camera.rotateY(degrees);
		camera.getMatrix(matrix);
		camera.restore();

		// 修正失真，主要修改 MPERSP_0 和 MPERSP_1
		float[] mValues = new float[9];
		matrix.getValues(mValues); // 获取数值
		mValues[6] = mValues[6] / scale; // 数值修正
		mValues[7] = mValues[7] / scale; // 数值修正
		matrix.setValues(mValues); // 重新赋值

		if (reverse) {
			matrix.postScale(1 + (mScaleX - 1) * interpolatedTime, 1 + (mScaleY - 1) * interpolatedTime, mPivotX - mMarginLeft, mPivotY - mMarginTop);
		} else {
			matrix.postScale(1 + (mScaleX - 1) * (1 - interpolatedTime), 1 + (mScaleY - 1) * (1 - interpolatedTime), mPivotX - mMarginLeft, mPivotY - mMarginTop);
		}

	}

	/**
	 * 计算缩放的中心点的横坐标
	 *
	 * @param marginLeft
	 *            该View距离父布局左边的距离
	 * @param parentWidth
	 *            父布局的宽度
	 * @param width
	 *            View的宽度
	 * @return 缩放中心点的横坐标
	 */
	public float calculatePivotX(float marginLeft, float parentWidth, float width) {
		return parentWidth * marginLeft / (parentWidth - width);
	}

	/**
	 * 计算缩放的中心点的纵坐标
	 *
	 * @param marginTop
	 *            该View顶部距离父布局顶部的距离
	 * @param parentHeight
	 *            父布局的高度
	 * @param height
	 *            子布局的高度
	 * @return 缩放的中心点的纵坐标
	 */
	public float calculatePivotY(float marginTop, float parentHeight, float height) {
		return parentHeight * marginTop / (parentHeight - height);
	}

	public void reverse() {
		reverse = !reverse;
	}

	public boolean isReverse() {
		return reverse;
	}
}
