package esprit.org.espritappliaction.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import esprit.org.espritappliaction.Entity.Classe;
import esprit.org.espritappliaction.Entity.Enseignant;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.R;

/**
 * Created by aziz on 24/01/2016.
 */
public class EtudiantlistAdapter extends ArrayAdapter<Etudiant> {
    private List<Etudiant> etudiants ;
    private Context mContext;
    private int layoutResourceId;
    List<Etudiant> list =new ArrayList<>();
    public EtudiantlistAdapter(Context mContext, int layoutResourceId,List<Etudiant> etudiants){
        super(mContext, layoutResourceId, etudiants);
        this.layoutResourceId =layoutResourceId;
        this.mContext = mContext;
        this.etudiants=etudiants;
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }

    public void setLayoutResourceId(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }


    @Override
    public int getCount() {
        return etudiants.size();
    }

    @Override
    public Etudiant getItem(int position) {
        // TODO Auto-generated method stub
        return etudiants.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        AdapterHolderetudiant handler;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(getLayoutResourceId(), parent, false);
            handler = new AdapterHolderetudiant();
            handler.idetudiant = (TextView) row.findViewById(R.id.idetudiant);
            handler.name = (TextView) row.findViewById(R.id.etudinatname);
            handler.prename = (TextView) row.findViewById(R.id.etudiantprename);
            row.setTag(handler);
        } else {

            handler = (AdapterHolderetudiant) row.getTag();

        }

        Etudiant etudiant = new Etudiant();
        etudiant = etudiants.get(position);
        handler.idetudiant.setText(etudiant.getId());
        handler.name.setText(etudiant.getNom());
        handler.prename.setText(etudiant.getPrenom());
        return row;

    }

    static class AdapterHolderetudiant {
        TextView idetudiant;
        TextView name;
        TextView prename;
    }




    }

