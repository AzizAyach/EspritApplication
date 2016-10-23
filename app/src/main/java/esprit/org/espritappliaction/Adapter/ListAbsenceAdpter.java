package esprit.org.espritappliaction.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import esprit.org.espritappliaction.Entity.Absence;
import esprit.org.espritappliaction.R;

/**
 * Created by ayach on 3/14/16.
 */

public class ListAbsenceAdpter extends ArrayAdapter<Absence> {


    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Absence> absences = new ArrayList<Absence>();

    public ListAbsenceAdpter(Context context, int resource, ArrayList<Absence> objects) {
        super(context, resource, objects);
        this.setLayoutResourceId(resource);
        this.mContext = context;
        this.setAbsences(objects);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(ArrayList<Absence> absences) {
        this.absences = absences;
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }

    public void setLayoutResourceId(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    static class Handlerloge {
        TextView nom;
        TextView module;
        TextView date;
        TextView classe;
        TextView numeroScenace;
    }

    @Override
    public int getCount() {
        return absences.size();
    }

    @Override
    public Absence getItem(int position) {
        // TODO Auto-generated method stub
        return absences.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        Handlerloge handler;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(getLayoutResourceId(), parent, false);
            handler = new Handlerloge();


            handler.nom = (TextView) row.findViewById(R.id.id_etud);
            handler.classe = (TextView) row.findViewById(R.id.id_clas_a);
            handler.date = (TextView) row.findViewById(R.id.date_module);
            handler.module = (TextView) row.findViewById(R.id.id_module);
            handler.numeroScenace = (TextView) row.findViewById(R.id.num_seance);
            row.setTag(handler);
        } else {

            handler = (Handlerloge) row.getTag();

        }

        Absence stad = new Absence();
        stad = absences.get(position);

        handler.nom.setText(stad.getName());
        handler.classe.setText(stad.getCode_classe());
        handler.date .setText(stad.getDate_abs());
        handler.module.setText(stad.getCode_Module());
        handler.numeroScenace.setText(stad.getNum_seance());
        return row;

    }


}
