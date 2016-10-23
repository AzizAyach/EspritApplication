package esprit.org.espritappliaction.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by aziz on 24/01/2016.
 */
public class Evenement implements Parcelable{


    private int id;
    private String titre;
    private String Description;
    private Date debut;
    private Date fin;
    private String heure;
    private Club club;
    private String type;
    private byte [] image;
    private static final long serialVersionUID = 1L;

    public Evenement() {

    }

    protected Evenement(Parcel in) {

        this.titre = in.readString();;
        this.Description = in.readString();
        this.debut = (Date) in.readSerializable();
        this.heure = in.readString();
        this.fin = (Date) in.readSerializable();
        this.club= (Club)in.readParcelable(Club.class.getClassLoader());
        this.type = in.readString();
        this.image =new byte[in.readInt()];
        in.readByteArray(image);

    }

    public Evenement(String titre, String description, Date debut, String heure, Date fin, Club club, String type, byte[] image) {
        this.titre = titre;
        this.Description = description;
        this.debut = debut;
        this.heure = heure;
        this.fin = fin;
        this.club = club;
        this.type = type;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }




    public void setImage(byte[] image) {
        this.image = image;
    }




    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    public Date getDebut() {
        return this.debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }
    public Date getFin() {
        return this.fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
    public String getHeure() {
        return this.heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
    public Club getClub() {
        return this.club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titre);
        dest.writeString(Description);
        dest.writeString(titre);
        dest.writeSerializable(debut);
        dest.writeSerializable(fin);
        dest.writeString(heure);
        dest.writeParcelable(club,flags);
        dest.writeString(type);
        dest.writeInt(image.length);
        dest.writeByteArray(image);
    }



    public static final Creator<Evenement> CREATOR = new Creator<Evenement>() {
        @Override
        public Evenement createFromParcel(Parcel in) {
            return new Evenement(in);
        }

        @Override
        public Evenement[] newArray(int size) {
            return new Evenement[size];
        }
    };



}
