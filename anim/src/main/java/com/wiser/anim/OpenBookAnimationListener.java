package com.wiser.anim;

import android.view.animation.Animation;

/**
 * 打开书动画
 */
interface AnimationListener {

	void startAnim(Animation animation);

	void repeatAnim(Animation Animation);

	void endAnim(Animation animation);

}

public abstract class OpenBookAnimationListener implements AnimationListener {

	@Override public void startAnim(Animation animation) {

	}

	@Override public void repeatAnim(Animation Animation) {

	}

	@Override public void endAnim(Animation animation) {

	}

}
