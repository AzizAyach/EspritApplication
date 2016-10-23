package esprit.org.espritappliaction.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aziz on 12/01/2016.
 */
public class Module implements Parcelable{

    public String getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(String idClasse) {
        this.idClasse = idClasse;
    }
    public int semsestre;
    String idClasse;
    String idModule;

    public int getSemsestre() {
        return semsestre;
    }

    public void setSemsestre(int semsestre) {
        this.semsestre = semsestre;
    }

    public Module (){}
    public Module(String idModule, String designation,String idClasse,int semsestre) {

        this.idModule = idModule;
        this.Designation = designation;
        this.idClasse =idClasse;
        this.semsestre =semsestre;
    }
    public Module (Parcel in ){

            this.idModule = in.readString();
            this.Designation = in.readString();
            this.idClasse = in.readString();
            this.semsestre=in.readInt();
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    String Designation;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idModule);
        dest.writeString(Designation);
        dest.writeString(idClasse);
        dest.writeInt(semsestre);
    }
    public static final Parcelable.Creator<Module> CREATOR = new Parcelable.Creator<Module>() {
        @Override
        public Module createFromParcel(Parcel in) {
            return new Module(in);
        }

        @Override
        public Module[] newArray(int size) {
            return new Module[size];
        }
    };


}
