package esprit.org.espritappliaction.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.List;

import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.R;

/**
 * Created by Yahya on 06/03/2016.
 */
public class EtudiantsAdapter extends ArrayAdapter<Etudiant> {
    Context context;
    int layoutResourceId;
    List<Etudiant> etudiants = null;

    public EtudiantsAdapter(Context context, int layoutResourceId,  List<Etudiant> etudiants) {
        super(context,layoutResourceId,etudiants);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.etudiants = etudiants;

    }

    @SuppressLint("SdCardPath")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ClasseHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ClasseHolder();

            holder.title = (TextView) row
                    .findViewById(R.id.tv_etudiant_fullName);

            row.setTag(holder);
        } else {
            holder = (ClasseHolder) row.getTag();
        }
     Etudiant etudiant = etudiants.get(position);

        holder.title.setText(etudiant.getNom()+" "+etudiant.getPrenom());



        return row;
    }

    public class ClasseHolder {

        TextView title;
        //  TextView descrtiption;

    }
}