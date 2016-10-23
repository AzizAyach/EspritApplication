package esprit.org.espritappliaction.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by aziz on 12/01/2016.
 */

public class Etudiant implements Parcelable{

    private  String id;
    private  String mail ;
    private  String nom ;
    private  String prenom ;
    private  int cin ;

public Etudiant (){}

    public Etudiant(String nom_et, String pnom_et, String id_et) {
        this.nom=nom_et;
        this.prenom=pnom_et;
        this.id=id_et;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }



    public Etudiant(String id, String mail, String nom, String prenom, int cin) {
        this.id = id;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
    }

    public Etudiant(Parcel in) {
        this.id = in.readString();
        this.mail = in.readString();
        this.nom = in.readString();
        this.prenom = in.readString();
        this.cin = in.readInt();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(mail);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeInt(cin);

    }


    public static final Parcelable.Creator<Etudiant> CREATOR = new Parcelable.Creator<Etudiant>()
    {
        @Override
        public Etudiant createFromParcel(Parcel source)
        {
            return new Etudiant(source);
        }

        @Override
        public Etudiant[] newArray(int size)
        {
            return new Etudiant[size];
        }
    };
}
