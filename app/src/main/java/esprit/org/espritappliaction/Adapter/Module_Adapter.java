package esprit.org.espritappliaction.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import esprit.org.espritappliaction.Entity.Module;
import esprit.org.espritappliaction.R;

/**
 * Created by aziz on 24/01/2016.
 */
public class Module_Adapter extends ArrayAdapter<Module> {
    private Context mContext;

    public int getLayoutResourceId() {
        return layoutResourceId;
    }

    public void setLayoutResourceId(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    public List<Module> getlModule() {
        return lModule;
    }

    public void setlModule(List<Module> lModule) {
        this.lModule = lModule;
        notifyDataSetChanged();
    }

    private int layoutResourceId;
    private List<Module> lModule = new ArrayList<Module>();

    public Module_Adapter(Context context, int layoutResourceId,List<Module> mData) {
        super(context,layoutResourceId,mData);
        this.setLayoutResourceId(layoutResourceId);
        this.mContext = context;
        this.setlModule(mData);
    }
    static class Handlerloge
    {

        TextView nom;

    }
    @Override
    public int getCount() {
        return this.lModule.size();
    }
    @Override
    public Module getItem(int position) {
        return lModule.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row ;
        row =convertView;
        Handlerloge handler;
        if (convertView==null){

            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(getLayoutResourceId(), parent, false);
            handler = new Handlerloge();
            handler.nom = (TextView)row.findViewById(R.id.desiModule);


            row.setTag(handler);
        }
        else{

            handler= (Handlerloge)row.getTag();

        }
        Module module = (Module) this.getItem(position);
        handler.nom.setText(module.getDesignation());
        return row;
    }


}
