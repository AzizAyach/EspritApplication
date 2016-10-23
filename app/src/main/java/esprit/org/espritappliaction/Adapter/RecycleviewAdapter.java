package esprit.org.espritappliaction.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import esprit.org.espritappliaction.Entity.Classe;
import esprit.org.espritappliaction.Entity.Enseignant;
import esprit.org.espritappliaction.Entity.Module;
import esprit.org.espritappliaction.R;

/**
 * Created by aziz on 22/01/2016.
 */
public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.AdapterHolder>{
    List <Classe> lista = new ArrayList<>();
        OnItemClickListener mItemClickListener;
    Module_Adapter module_adapter;
    List<Module> listm =null ;
Context mContext ;
        private Enseignant ens = new Enseignant();
        public RecycleviewAdapter(Enseignant ens ,Context context){
            this.ens=ens;
            this.mContext=context;

        }
        @Override
        public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.champ_classe, parent, false);

            return  new AdapterHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AdapterHolder holder, int position) {

            for(Classe d : ens.getListClasse().values()) {
                lista.add(d);

                Classe cl = new Classe();
                cl = ens.getListClasse().get(position);

            }
            Classe clu =lista.get(position);
            if(clu.getSemsetre()==1){
                holder.semsestre.setText("Semestre 1");
            }
            else {
                holder.semsestre.setText("Semestre 2");
            }


            try {
                holder.classename.setText(clu.getId());


            }
            catch (Exception e){
                System.out.println("erreur de chargement de contenue");


            }
           holder.classe =getItem(position);
        }

        public Classe getItem(int position) {

            return  lista.get(position);
        }


        @Override
        public int getItemCount() {
            return ens.getListClasse().size();
        }

        public class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        {   Classe classe;
            TextView classename;
            TextView semsestre;
            ListView list;
            public AdapterHolder(View row) {
                super(row);
                row.setOnClickListener(this);
                classename = (TextView) row.findViewById(R.id.classename);
                semsestre = (TextView) row.findViewById(R.id.semstre);
                list = (ListView) row.findViewById(R.id.listmodule);
                for(String key : ens.getListClasse().keySet()){

                    listm = new ArrayList<>();
                    listm = ens.getListModule().get(key);
                    module_adapter = new Module_Adapter(mContext, R.layout.champmodule, listm);}

                list.setAdapter(module_adapter);
            }
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, getPosition());

                }
            }

        }
        public interface OnItemClickListener {
            public void onItemClick(View view , int position);
        }

        public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
            this.mItemClickListener = mItemClickListener;
        }

    }
