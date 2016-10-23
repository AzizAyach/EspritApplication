package esprit.org.espritappliaction.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aziz on 07/02/2016.
 */
public class Note implements Parcelable{

    private String designation ;
    private  int coefficient;
    private String nom_enseignat;
    private  float controle_continue;
    private  float controle_exam;
    private  float controle_tp;
    private boolean tp_note;
    private boolean absence;

    public Note(String designation, int coefficient, String nom_enseignat, float controle_continue, float controle_exam, float controle_tp, boolean tp_note, boolean absence) {
        this.designation = designation;
        this.coefficient = coefficient;
        this.nom_enseignat = nom_enseignat;
        this.controle_continue = controle_continue;
        this.controle_exam = controle_exam;
        this.controle_tp = controle_tp;
        this.tp_note = tp_note;
        this.absence = absence;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public String getNom_enseignat() {
        return nom_enseignat;
    }

    public void setNom_enseignat(String nom_enseignat) {
        this.nom_enseignat = nom_enseignat;
    }

    public float getControle_continue() {
        return controle_continue;
    }

    public void setControle_continue(float controle_continue) {
        this.controle_continue = controle_continue;
    }

    public float getControle_exam() {
        return controle_exam;
    }

    public void setControle_exam(float controle_exam) {
        this.controle_exam = controle_exam;
    }

    public float getControle_tp() {
        return controle_tp;
    }

    public void setControle_tp(float controle_tp) {
        this.controle_tp = controle_tp;
    }

    public boolean isAbsence() {
        return absence;
    }

    public void setAbsence(boolean absence) {
        this.absence = absence;
    }

    public boolean isTp_note() {
        return tp_note;
    }

    public void setTp_note(boolean tp_note) {
        this.tp_note = tp_note;
    }

    public Note (){}


    protected Note(Parcel in) {
        this.designation = in.readString();
        this.coefficient = in.readInt();
        this.nom_enseignat = in.readString();
        this.controle_continue = in.readFloat();
        this.controle_exam = in.readFloat();
        this.controle_tp = in.readFloat();
        this.tp_note =(in.readInt() == 0) ? false : true;
        this.absence =(in.readInt() == 0) ? false : true;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(designation);
        dest.writeInt(coefficient);
        dest.writeString(nom_enseignat);
        dest.writeFloat(controle_continue);
        dest.writeFloat(controle_exam);
        dest.writeFloat(controle_tp);
        dest.writeInt(tp_note ? 1 : 0);
        dest.writeInt(absence ? 1 : 0);
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
