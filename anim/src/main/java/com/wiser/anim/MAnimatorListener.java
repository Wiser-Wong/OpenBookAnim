package com.wiser.anim;

import android.animation.Animator;

/**
 * 打开书动画
 */
interface AnimatorListener {

	void startAnim(Animator animation);

	void resumeAnim(Animator animation);

	void pauseAnim(Animator animation);

	void repeatAnim(Animator animator);

	void endAnim(Animator animation);

	void cancelAnim(Animator animation);
}

public abstract class MAnimatorListener implements AnimatorListener {

	@Override public void startAnim(Animator animation) {

	}

	@Override public void resumeAnim(Animator animation) {

	}

	@Override public void pauseAnim(Animator animation) {

	}

	@Override public void repeatAnim(Animator animator) {

	}

	@Override public void endAnim(Animator animation) {

	}

	@Override public void cancelAnim(Animator animation) {

	}
}
