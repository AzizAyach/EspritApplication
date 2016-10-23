package esprit.org.espritappliaction.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import esprit.org.espritappliaction.Entity.Classe;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.R;

/**
 * Created by aziz on 25/01/2016.
 */
public class AdapterAbsence extends RecyclerView.Adapter<AdapterAbsence.AdapterHolderAbs>{

    private Classe classe ;
    List<Etudiant> list;
    ArrayList<Etudiant> etudiantAbsent = new ArrayList<>();
    public AdapterAbsence(Classe classe){
        this.classe=classe;


    }

    @Override
    public AdapterHolderAbs onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.champlayout_abcense_etudiant, parent, false);
        list= classe.getEtudiants();
        return  new AdapterHolderAbs(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolderAbs holder, int position) {
        final Etudiant etudiant = classe.getEtudiants().get(position);
        holder.idetudiant.setText(etudiant.getId());
        holder.name.setText(etudiant.getNom());
       // holder.prename.setText(etudiant.getPrenom());
        holder.sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){etudiantAbsent.add(etudiant);}
                if(isChecked==false){etudiantAbsent.remove(etudiant);}

            }
        });
    }

    @Override
    public int getItemCount() {
        return classe.getEtudiants().size();

    }
public ArrayList <Etudiant>etudiantAbsent(){

return etudiantAbsent;
}

    public class AdapterHolderAbs extends RecyclerView.ViewHolder {
        Etudiant etudiant;
        TextView idetudiant;
        TextView name;
        TextView prename;
        SwitchButton sb;

        public AdapterHolderAbs(View row) {
            super(row);
            idetudiant = (TextView) row.findViewById(R.id.idetudiant);
            name = (TextView) row.findViewById(R.id.etudinatname);
          //  prename = (TextView) row.findViewById(R.id.etudiantprename);
            sb = (SwitchButton) row.findViewById(R.id.recycler_item_sb);

        }
    }
}
