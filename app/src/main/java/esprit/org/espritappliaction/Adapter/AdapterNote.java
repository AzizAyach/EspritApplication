package esprit.org.espritappliaction.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import esprit.org.espritappliaction.Entity.Note;
import esprit.org.espritappliaction.R;

/**
 * Created by aziz on 08/02/2016.
 */
public class AdapterNote extends ArrayAdapter<Note>{
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Note> notes = new ArrayList<>();

    public AdapterNote(Context mContext,int layoutResourceId ,ArrayList<Note> notes){
        super(mContext, layoutResourceId, notes);
        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.notes = notes; }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }

    public void setLayoutResourceId(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        // TODO Auto-generated method stub
        return notes.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        AdapterHolderNote holder;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(getLayoutResourceId(), parent, false);
            holder = new AdapterHolderNote();
            holder.designation = (TextView) row.findViewById(R.id.modulename);
            holder.name_ens = (TextView) row.findViewById(R.id.ensname);
            holder.coefficient = (TextView) row.findViewById(R.id.cofic);
            holder.note_cc = (TextView) row.findViewById(R.id.cc_note);
            holder. note_tp = (TextView) row.findViewById(R.id.tp_note);
            holder.note_ex = (TextView) row.findViewById(R.id.ex_note);
            row.setTag(holder);
        } else {

            holder = (AdapterHolderNote) row.getTag();

        }


        holder.designation.setText(notes.get(position).getDesignation());
        holder.name_ens.setText(notes.get(position).getNom_enseignat());
        holder.coefficient.setText(""+notes.get(position).getCoefficient());
        holder.note_cc.setText(""+notes.get(position).getControle_continue());
        if(!notes.get(position).isTp_note())
        {holder.note_tp.setText("");}
        else { holder.note_tp.setText(""+notes.get(position).getControle_tp());}
        holder.note_ex.setText(""+notes.get(position).getControle_exam());

        return row;

    }



    static class AdapterHolderNote {
        Note note;
        TextView designation;
        TextView name_ens;
        TextView coefficient;
        TextView note_cc;
        TextView note_tp;
        TextView note_ex;

    }


}
