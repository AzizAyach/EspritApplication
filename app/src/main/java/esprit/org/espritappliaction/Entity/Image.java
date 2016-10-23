package esprit.org.espritappliaction.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Image implements Parcelable{
String photo1 ;
String photo2;
String photo3;
String photo4;

public Image(){}
public Image(String photo1, String photo2, String photo3, String photo4) {
	this.photo1 = photo1;
	this.photo2 = photo2;
	this.photo3 = photo3;
	this.photo4 = photo4;
}
	public Image(Parcel in) {
		this.photo1 = in.readString();
		this.photo2 = in.readString();
		this.photo3 = in.readString();
		this.photo4 = in.readString();



	}
public String getPhoto1() {
	return photo1;
}
public void setPhoto1(String photo1) {
	this.photo1 = photo1;
}
public String getPhoto2() {
	return photo2;
}
public void setPhoto2(String photo2) {
	this.photo2 = photo2;
}
public String getPhoto3() {
	return photo3;
}
public void setPhoto3(String photo3) {
	this.photo3 = photo3;
}
public String getPhoto4() {
	return photo4;
}
public void setPhoto4(String photo4) {
	this.photo4 = photo4;
}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(photo1);
		dest.writeString(photo2);
		dest.writeString(photo3);
		dest.writeString(photo4);
	}

	public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>()
	{
		@Override
		public Image createFromParcel(Parcel source)
		{
			return new Image(source);
		}

		@Override
		public Image[] newArray(int size)
		{
			return new Image[size];
		}
	};
}
