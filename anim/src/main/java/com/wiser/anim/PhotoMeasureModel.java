package com.wiser.anim;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Wiser
 * 
 *         图片数据
 */
public class PhotoMeasureModel implements Parcelable {

	public Bitmap	coverBitmap;

	public int		coverPicId;

	public int		width;

	public int		height;

	public int		left;

	public int		top;

	public int		right;

	public int		bottom;

	protected PhotoMeasureModel(Parcel in) {
		coverBitmap = in.readParcelable(Bitmap.class.getClassLoader());
		coverPicId = in.readInt();
		width = in.readInt();
		height = in.readInt();
		left = in.readInt();
		top = in.readInt();
		right = in.readInt();
		bottom = in.readInt();
	}

	public PhotoMeasureModel() {}

	public static final Creator<PhotoMeasureModel> CREATOR = new Creator<PhotoMeasureModel>() {

		@Override public PhotoMeasureModel createFromParcel(Parcel in) {
			return new PhotoMeasureModel(in);
		}

		@Override public PhotoMeasureModel[] newArray(int size) {
			return new PhotoMeasureModel[size];
		}
	};

	@Override public int describeContents() {
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(coverBitmap, flags);
		dest.writeInt(coverPicId);
		dest.writeInt(width);
		dest.writeInt(height);
		dest.writeInt(left);
		dest.writeInt(top);
		dest.writeInt(right);
		dest.writeInt(bottom);
	}
}
