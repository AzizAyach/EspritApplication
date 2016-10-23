package esprit.org.espritappliaction.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ayach on 3/7/16.
 */
public class Club implements Parcelable {


    private int id;
    private String Nom;
    private String Type;
    private List<Evenement> event;
    private byte[] logo;

    public Club( String nom, String type, List<Evenement> event, byte[] logo) {
        Nom = nom;
        Type = type;
        this.event = event;
        this.logo = logo;
    }

    public Club(Parcel in) {
        this.Nom = in.readString();
        this.Type = in.readString();
        this.event = in.createTypedArrayList(Evenement.CREATOR);;
        this.logo =new byte[in.readInt()];
        in.readByteArray(logo);
    }

    public Club() {
        super();
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return this.Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    public String getType() {
        return this.Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public List<Evenement> getEvent() {
        return event;
    }

    public void setEvent(List<Evenement> event) {
        this.event = event;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(Nom);
            dest.writeString(Type);
            dest.writeTypedList(event);
            dest.writeInt(logo.length);
            dest.writeByteArray(logo);

    }


    public static final Creator<Club> CREATOR = new Creator<Club>() {
        @Override
        public Club createFromParcel(Parcel in) {
            return new Club(in);
        }

        @Override
        public Club[] newArray(int size) {
            return new Club[size];
        }
    };
}
