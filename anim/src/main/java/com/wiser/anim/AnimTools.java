package com.wiser.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * @author Wiser
 * 
 *         动画工具
 */
public class AnimTools {

	private static final int time = 1000;

	/**
	 * 打开书籍动画
	 * 
	 * @param photoMeasureModel
	 * @param coverImg
	 * @param activity
	 */
	public static void openBookAnim(PhotoMeasureModel photoMeasureModel, final ImageView coverImg, final View contentView, final Activity activity) {
		coverImg.setImageBitmap(photoMeasureModel.coverBitmap);
		ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) coverImg.getLayoutParams();
		params.leftMargin = photoMeasureModel.left;
		params.topMargin = photoMeasureModel.top;
		params.rightMargin = photoMeasureModel.right;
		params.bottomMargin = photoMeasureModel.bottom;
		params.width = photoMeasureModel.width;
		params.height = photoMeasureModel.height;
		coverImg.setLayoutParams(params);

		float startScaleX = (float) getScreenWidth(activity) / photoMeasureModel.width;
		float startScaleY = (float) getScreenHeight(activity) / photoMeasureModel.height;
		final OpenBookRotate3DAnim openBookRotate3DAnim = new OpenBookRotate3DAnim(activity, -90, 0, photoMeasureModel.left, photoMeasureModel.top, startScaleX, startScaleY, true);
		openBookRotate3DAnim.setDuration(time); // 设置动画时长
		openBookRotate3DAnim.setFillAfter(true); // 保持旋转后效果
		openBookRotate3DAnim.setInterpolator(new DecelerateInterpolator());
		coverImg.startAnimation(openBookRotate3DAnim);

		magnifyAnim(activity, photoMeasureModel, contentView);
	}

	/**
	 * 关闭书动画
	 * 
	 * @param photoMeasureModel
	 * @param coverImg
	 * @param activity
	 */
	public static void closeBookAnim(PhotoMeasureModel photoMeasureModel, final ImageView coverImg, final View contentView, Activity activity) {
		float startScaleX = (float) getScreenWidth(activity) / photoMeasureModel.width;
		float startScaleY = (float) getScreenHeight(activity) / photoMeasureModel.height;
		final OpenBookRotate3DAnim openBookRotate3DAnim = new OpenBookRotate3DAnim(activity, -90, 0, coverImg.getLeft(), coverImg.getTop(), startScaleX, startScaleY, false);
		openBookRotate3DAnim.setDuration(time); // 设置动画时长
		openBookRotate3DAnim.setFillAfter(true); // 保持旋转后效果
		openBookRotate3DAnim.setInterpolator(new DecelerateInterpolator());
		coverImg.startAnimation(openBookRotate3DAnim);

		shrinkAnim(activity, photoMeasureModel, contentView);
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

	/**
	 * 放大动画
	 * 
	 * @param activity
	 * @param photoMeasureModel
	 * @param magnifyView
	 */
	public static void magnifyAnim(Activity activity, PhotoMeasureModel photoMeasureModel, final View magnifyView) {
		// 计算初始的缩放比例。最终的缩放比例为1。并调整缩放方向，使看着协调。
		float startScaleX = (float) photoMeasureModel.width / getScreenWidth(activity);
		float startScaleY = (float) photoMeasureModel.height / getScreenHeight(activity);
		// 将大图的缩放中心点移到左上角。默认是从中心缩放
		magnifyView.setPivotX(0f);
		magnifyView.setPivotY(0f);
		// 对大图进行缩放动画
		AnimatorSet set = new AnimatorSet();
		set.play(ObjectAnimator.ofFloat(magnifyView, View.X, photoMeasureModel.left, 0)).with(ObjectAnimator.ofFloat(magnifyView, View.Y, photoMeasureModel.top, 0))
				.with(ObjectAnimator.ofFloat(magnifyView, View.SCALE_X, startScaleX, 1f)).with(ObjectAnimator.ofFloat(magnifyView, View.SCALE_Y, startScaleY, 1f));
		set.setDuration(1100);
		set.setInterpolator(new DecelerateInterpolator());
		set.start();
		set.addListener(new AnimatorListenerAdapter() {

			@Override public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				magnifyView.bringToFront();
			}

			@Override public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
			}
		});
	}

	/**
	 * 缩小动画
	 * 
	 * @param activity
	 * @param photoMeasureModel
	 * @param shrinkView
	 */
	public static void shrinkAnim(Activity activity, PhotoMeasureModel photoMeasureModel, View shrinkView) {
		// 计算初始的缩放比例。最终的缩放比例为1。并调整缩放方向，使看着协调。
		float startScaleX = (float) photoMeasureModel.width / getScreenWidth(activity);
		float startScaleY = (float) photoMeasureModel.height / getScreenHeight(activity);
		// 将大图的缩放中心点移到左上角。默认是从中心缩放
		shrinkView.setPivotX(0f);
		shrinkView.setPivotY(0f);
		AnimatorSet set = new AnimatorSet();
		set.play(ObjectAnimator.ofFloat(shrinkView, View.X, 0, photoMeasureModel.left)).with(ObjectAnimator.ofFloat(shrinkView, View.Y, 0, photoMeasureModel.top))
				.with(ObjectAnimator.ofFloat(shrinkView, View.SCALE_X, 1f, startScaleX)).with(ObjectAnimator.ofFloat(shrinkView, View.SCALE_Y, 1f, startScaleY));
		set.setDuration(time);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter() {

			@Override public void onAnimationEnd(Animator animation) {}

			@Override public void onAnimationCancel(Animator animation) {}
		});
		set.start();
	}

}
