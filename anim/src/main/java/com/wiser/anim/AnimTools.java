package com.wiser.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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
	public static void openBookAnim(PhotoMeasureModel photoMeasureModel, final ImageView coverImg, final View contentView, final Activity activity, OpenBookAnimationListener animationListener) {
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

		magnifyAnimation(activity, photoMeasureModel, contentView, animationListener);
	}

	/**
	 * 关闭书动画
	 * 
	 * @param photoMeasureModel
	 * @param coverImg
	 * @param activity
	 */
	public static void closeBookAnim(PhotoMeasureModel photoMeasureModel, final ImageView coverImg, final View contentView, Activity activity, OpenBookAnimationListener animationListener) {
		float startScaleX = (float) getScreenWidth(activity) / photoMeasureModel.width;
		float startScaleY = (float) getScreenHeight(activity) / photoMeasureModel.height;
		final OpenBookRotate3DAnim openBookRotate3DAnim = new OpenBookRotate3DAnim(activity, -90, 0, coverImg.getLeft(), coverImg.getTop(), startScaleX, startScaleY, false);
		openBookRotate3DAnim.setDuration(time); // 设置动画时长
		openBookRotate3DAnim.setFillAfter(true); // 保持旋转后效果
		openBookRotate3DAnim.setInterpolator(new DecelerateInterpolator());
		coverImg.startAnimation(openBookRotate3DAnim);

		shrinkAnimation(activity, photoMeasureModel, contentView, animationListener);
	}

	/**
	 * 放大动画
	 * 
	 * @param activity
	 * @param photoMeasureModel
	 * @param magnifyView
	 */
	public static void magnifyAnimation(Activity activity, PhotoMeasureModel photoMeasureModel, final View magnifyView, final OpenBookAnimationListener animationListener) {
		// 计算初始的缩放比例。最终的缩放比例为1。并调整缩放方向，使看着协调。
		float startScaleX = (float) photoMeasureModel.width / getScreenWidth(activity);
		float startScaleY = (float) photoMeasureModel.height / getScreenHeight(activity);
		ScaleAnimation scaleAnimation = new ScaleAnimation(startScaleX, 1f, startScaleY, 1f, 0f, 0f);
		TranslateAnimation translateAnimation = new TranslateAnimation(photoMeasureModel.left, 0, photoMeasureModel.top, 0);
		scaleAnimation.setFillAfter(true);
		translateAnimation.setFillAfter(true);
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(scaleAnimation);
		set.addAnimation(translateAnimation);
		set.setDuration(time);
		set.setInterpolator(new DecelerateInterpolator());
		magnifyView.startAnimation(set);
		set.setAnimationListener(new Animation.AnimationListener() {

			@Override public void onAnimationStart(Animation animation) {
				if (animationListener != null) animationListener.startAnim(animation);
			}

			@Override public void onAnimationEnd(Animation animation) {
				if (animationListener != null) animationListener.endAnim(animation);
			}

			@Override public void onAnimationRepeat(Animation animation) {
				if (animationListener != null) animationListener.repeatAnim(animation);
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
	public static void shrinkAnimation(Activity activity, PhotoMeasureModel photoMeasureModel, View shrinkView, final OpenBookAnimationListener animationListener) {
		// 计算初始的缩放比例。最终的缩放比例为1。并调整缩放方向，使看着协调。
		float startScaleX = (float) photoMeasureModel.width / getScreenWidth(activity);
		float startScaleY = (float) photoMeasureModel.height / getScreenHeight(activity);
		ScaleAnimation scaleAnimation = new ScaleAnimation(1f, startScaleX, 1f, startScaleY, 0f, 0f);
		TranslateAnimation translateAnimation = new TranslateAnimation(0, photoMeasureModel.left, 0, photoMeasureModel.top);
		scaleAnimation.setFillAfter(true);
		translateAnimation.setFillAfter(true);
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(scaleAnimation);
		set.addAnimation(translateAnimation);
		set.setDuration(time);
		set.setInterpolator(new DecelerateInterpolator());
		shrinkView.startAnimation(set);
		set.setAnimationListener(new Animation.AnimationListener() {

			@Override public void onAnimationStart(Animation animation) {
				if (animationListener != null) animationListener.startAnim(animation);
			}

			@Override public void onAnimationEnd(Animation animation) {
				if (animationListener != null) animationListener.endAnim(animation);
			}

			@Override public void onAnimationRepeat(Animation animation) {
				if (animationListener != null) animationListener.repeatAnim(animation);
			}
		});
	}

	/**
	 * 放大动画
	 *
	 * @param activity
	 * @param photoMeasureModel
	 * @param magnifyView
	 */
	public static void magnifyAnimator(Activity activity, PhotoMeasureModel photoMeasureModel, final View magnifyView, final MAnimatorListener animListener) {
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
		set.setDuration(time);
		set.setInterpolator(new DecelerateInterpolator());
		set.start();
		set.addListener(new AnimatorListenerAdapter() {

			@Override public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				// magnifyView.bringToFront();
				if (animListener != null) animListener.startAnim(animation);
			}

			@Override public void onAnimationPause(Animator animation) {
				super.onAnimationPause(animation);
				if (animListener != null) animListener.pauseAnim(animation);
			}

			@Override public void onAnimationRepeat(Animator animation) {
				super.onAnimationRepeat(animation);
				if (animListener != null) animListener.repeatAnim(animation);
			}

			@Override public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				if (animListener != null) animListener.endAnim(animation);
			}

			@Override public void onAnimationCancel(Animator animation) {
				super.onAnimationCancel(animation);
				if (animListener != null) animListener.cancelAnim(animation);
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
	public static void shrinkAnimator(Activity activity, PhotoMeasureModel photoMeasureModel, View shrinkView, final MAnimatorListener animListener) {
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

			@Override public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				if (animListener != null) animListener.startAnim(animation);
			}

			@Override public void onAnimationPause(Animator animation) {
				super.onAnimationPause(animation);
				if (animListener != null) animListener.pauseAnim(animation);
			}

			@Override public void onAnimationRepeat(Animator animation) {
				super.onAnimationRepeat(animation);
				if (animListener != null) animListener.repeatAnim(animation);
			}

			@Override public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				if (animListener != null) animListener.endAnim(animation);
			}

			@Override public void onAnimationCancel(Animator animation) {
				super.onAnimationCancel(animation);
				if (animListener != null) animListener.cancelAnim(animation);
			}
		});
		set.start();
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
