package esprit.org.espritappliaction.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aziz on 24/01/2016.
 */
public class Logement implements Parcelable{

    public Logement(String idlogement, double Lng,double lat, String mailuser, String city, int nbchambre, int nbperso, int nblit, int price, Image image, String description) {
        this.idlogement = idlogement;
        this.lat= lat;
        this.Lng=Lng;
        this.mailuser = mailuser;
        this.city = city;
        this.nbchambre = nbchambre;
        this.nbperso = nbperso;
        this.nblit = nblit;
        this.Price = price;
        this.image = image;
        this.description = description;
    }

    public String getIdlogement() {
        return idlogement;
    }

    public void setIdlogement(String idlogement) {
        this.idlogement = idlogement;
    }



    public String getMailuser() {
        return mailuser;
    }

    public void setMailuser(String mailuser) {
        this.mailuser = mailuser;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNbchambre() {
        return nbchambre;
    }

    public void setNbchambre(int nbchambre) {
        this.nbchambre = nbchambre;
    }

    public int getNbperso() {
        return nbperso;
    }

    public void setNbperso(int nbperso) {
        this.nbperso = nbperso;
    }

    public int getNblit() {
        return nblit;
    }

    public void setNblit(int nblit) {
        this.nblit = nblit;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String idlogement;
    private double lat ;
    private double Lng ;
    private String mailuser;
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    private String city;
    private int nbchambre;
    private int nbperso;
    private int nblit;
    private int Price;
    private Image image;
    public Image getImage() {
        return image;
    }

    private String description;



    public Logement(){}



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idlogement);
        dest.writeString(mailuser);
        dest.writeString(city);
        dest.writeString(description);
        dest.writeDouble(lat);
        dest.writeDouble(Lng);
        dest.writeInt(nbchambre);
        dest.writeInt(nbperso);
        dest.writeInt(nblit);
        dest.writeInt(Price);
        dest.writeParcelable(image,flags);

    }
    public Logement(Parcel in) {
        this.idlogement = in.readString();
        this.lat= in.readDouble();
        this.Lng=in.readDouble();
        this.mailuser = in.readString();
        this.city = in.readString();
        this.nbchambre = in.readInt();
        this.nbperso = in.readInt();
        this.nblit = in.readInt();
        this.Price = in.readInt();
        this.image= (Image)in.readParcelable(Image.class.getClassLoader());
        this.description = in.readString();



    }
    public static final Parcelable.Creator<Logement> CREATOR = new Parcelable.Creator<Logement>()
    {
        @Override
        public Logement createFromParcel(Parcel source)
        {
            return new Logement(source);
        }

        @Override
        public Logement[] newArray(int size)
        {
            return new Logement[size];
        }
    };
}
