package esprit.org.espritappliaction.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by aziz on 12/01/2016.
 */
public class Classe implements Parcelable{
    public  Classe (){}
    public Classe(String id, List<Etudiant> etudiants,int semsetre) {
        this.id = id;
        this.etudiants = etudiants;
        this.semsetre=semsetre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    private String id ;
    private List<Etudiant> etudiants;
    private int semsetre ;

    public int getSemsetre() {
        return semsetre;
    }

    public void setSemsetre(int semsetre) {
        this.semsetre = semsetre;
    }

    protected Classe(Parcel in) {
        this.id = in.readString();
        this.etudiants = in.createTypedArrayList(Etudiant.CREATOR);
        this.semsetre = in.readInt();

    }

    public static final Creator<Classe> CREATOR = new Creator<Classe>() {
        @Override
        public Classe createFromParcel(Parcel in) {
            return new Classe(in);
        }

        @Override
        public Classe[] newArray(int size) {
            return new Classe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeTypedList(etudiants);
        dest.writeInt(semsetre);

    }
}
