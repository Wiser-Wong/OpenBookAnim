# OpenBookAnim
打开书籍动效

## 使用说明
### 项目下的build.gradle配置
    allprojects { repositories { ... maven { url 'https://jitpack.io' } } }
### app目录下build.gradle配置
    dependencies { implementation 'com.github.Wiser-Wong:Frame:1.4.0' }
## 方法调用
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
			AnimTools.openBookAnim(photoMeasureModel, ivCover, flContent, MainActivity.this, new        OpenBookAnimationListener() {

				@Override public void startAnim(Animation animation) {
					super.startAnim(animation);
					lContent.setVisibility(View.VISIBLE);
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

## 请看动图
![images](https://github.com/Wiser-Wong/OpenBookAnim/blob/master/images/anim.gif)
