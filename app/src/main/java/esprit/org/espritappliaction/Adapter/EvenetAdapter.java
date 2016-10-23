package esprit.org.espritappliaction.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import esprit.org.espritappliaction.Entity.Evenement;
import esprit.org.espritappliaction.R;

/**
 * Created by ayach on 3/7/16.
 */
public class EvenetAdapter extends ArrayAdapter {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Evenement> events = new ArrayList<Evenement>();

    public EvenetAdapter(Context mContext, int layoutResourceId, ArrayList<Evenement> events) {
        super(mContext, layoutResourceId, events);
        this.setLayoutResourceId(layoutResourceId);
        this.mContext = mContext;
        this.setEvents(events);
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }

    public void setLayoutResourceId(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    public ArrayList<Evenement> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Evenement> stadiums) {
        this.events = stadiums;
        notifyDataSetChanged();
    }


    static class Handlerloge {

        ImageView image;
        ImageView imag_club;
        TextView nom;
        TextView create_ev;
        TextView desc_ev;
        TextView  date ;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Evenement getItem(int position) {
        // TODO Auto-generated method stub
        return events.get(position);
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

            handler.image = (ImageView) row.findViewById(R.id.image_ev);
            handler.nom = (TextView) row.findViewById(R.id.title_event);
            //handler.create_ev = (TextView) row.findViewById(R.id.creat_event);
            handler.imag_club = (ImageView) row.findViewById(R.id.image_ev_logo);
            handler.desc_ev = (TextView)row.findViewById(R.id.description_event);
            handler.date = (TextView) row.findViewById(R.id.dat_ev);

            row.setTag(handler);
        } else {

            handler = (Handlerloge) row.getTag();

        }

        Evenement ev = new Evenement();
        ev = events.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(ev.getImage(), 0, ev.getImage().length);
        handler.image.setImageBitmap(bitmap);
        handler.nom.setText(ev.getTitre());
        handler.date.setText(ev.getDebut().toString());
        handler.create_ev.setText(ev.getClub().getNom());
        handler.desc_ev.setText(ev.getDescription());
        return row;

    }
}
