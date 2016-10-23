package esprit.org.espritappliaction.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aziz on 19/01/2016.
 */
public class Enseignant implements  Parcelable {

    String idenseignant ;
    Map<String,Classe> ListClasse = new HashMap<>();
    Map<String,List<Module>> ListModule = new HashMap<>();

    public Enseignant(String idenseignant, Map<String,Classe> listClasse, Map<String, List<Module>> listModule) {
        this.idenseignant = idenseignant;
        ListClasse = listClasse;
        ListModule = listModule;
    }

    public Map<String, Classe> getListClasse() {
        return ListClasse;
    }

    public void setListClasse(Map<String,Classe> listClasse) {
        ListClasse = listClasse;
    }

    public Map<String, List<Module>> getListModule() {
        return ListModule;
    }

    public void setListModule(Map<String, List<Module>> listModule) {
        ListModule = listModule;
    }

    public Enseignant(){}




    public String getIdenseignant() {
        return idenseignant;
    }

    public void setIdenseignant(String idenseignant) {
        this.idenseignant = idenseignant;
    }



    protected Enseignant(Parcel in) {
        final int size = in.readInt();
        this.idenseignant = in.readString();
        for (int i = 0; i < size; i++) {
            final String key = in.readString();
                final Classe value = in.readParcelable(Classe.class.getClassLoader());


            ListClasse.put(key, value);
        }

            final int size1 = in.readInt();
            for (int f = 0; f < size1; f++) {
                final String key1 = in.readString();
                final int listLength1 = in.readInt();

                final List<Module> listm = new ArrayList<Module>(listLength1);
                for (int h = 0; h < listLength1; h++) {
                    final Module value = in.readParcelable(Module.class.getClassLoader());
                    listm.add(value);
                }

                ListModule.put(key1, listm);
                // this.ListClasse = in.createTypedArrayList(Classe.CREATOR);
                //this.semestre2 = in.createTypedArrayList(Classe.CREATOR);

            }
        }


    public static final Parcelable.Creator<Enseignant> CREATOR = new Parcelable.Creator<Enseignant>() {
        @Override
        public Enseignant createFromParcel(Parcel in) {
            return new Enseignant(in);
        }

        @Override
        public Enseignant[] newArray(int size) {
            return new Enseignant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ListClasse.size());
        dest.writeString(idenseignant);
        for (Map.Entry<String,Classe> entry : ListClasse.entrySet()) {
            dest.writeString(entry.getKey());
            final Classe list = entry.getValue();


                dest.writeParcelable(list, flags);

        }
        dest.writeInt(ListModule.size());
        for (Map.Entry<String, List<Module>> entry : ListModule.entrySet()) {
            dest.writeString(entry.getKey());

            final List<Module> list = entry.getValue();
            final int listLength = list.size();

            dest.writeInt(listLength);

            for (Module item: list) {
                dest.writeParcelable(item, 0);
            }
        }

    //    dest.writeTypedList(list);
       // dest.writeTypedList(semestre2);

    }
}
