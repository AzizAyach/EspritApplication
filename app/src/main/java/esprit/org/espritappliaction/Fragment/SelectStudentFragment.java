package esprit.org.espritappliaction.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;



import java.util.List;
import java.util.concurrent.ExecutionException;

import esprit.org.espritappliaction.Adapter.EtudiantsAdapter;
import esprit.org.espritappliaction.Service.ListStudentService;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.R;

public class SelectStudentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
    View view;
    List<Etudiant> etudiants;
    Bundle bundle;
    ListView listView;
    ListStudentService listStudentService;

    EtudiantsAdapter etudiantsAdapter;
    String aClass;
    private OnFragmentInteractionListener mListener;

    public SelectStudentFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   view= inflater.inflate(R.layout.fragment_select_student, container, false);



        listView=(ListView)view.findViewById(R.id.listEtudiant);






         bundle = this.getArguments();
        Context context = getActivity();
        aClass = bundle.getString("class", "");
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, aClass, duration);
        toast.show();

        listStudentService=new ListStudentService(getActivity(),aClass);
        try {

            etudiants=listStudentService.execute().get();
            etudiantsAdapter = new EtudiantsAdapter(getActivity(),R.layout.etudiant_one_row, etudiants);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        listView.setAdapter(etudiantsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    bundle.putString("nom",etudiants.get(position).getNom());
                    bundle.putString("prenom",etudiants.get(position).getPrenom());
                    bundle.putString("id",etudiants.get(position).getId());
                Toast toast = Toast.makeText(getActivity(), etudiants.get(position).getNom()+" "+etudiants.get(position).getPrenom(), Toast.LENGTH_LONG);
                toast.show();
                DisplayStatFragment fragment=new DisplayStatFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frag_abs_stat, fragment, "displayStat");
                ft.commit();



            }
          });











        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
