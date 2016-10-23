package esprit.org.espritappliaction.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import esprit.org.espritappliaction.Adapter.EtudiantlistAdapter;
import esprit.org.espritappliaction.Adapter.RecycleviewAdapter;
import esprit.org.espritappliaction.Entity.Classe;
import esprit.org.espritappliaction.Entity.Enseignant;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.R;
import esprit.org.espritappliaction.View.Enseignat_Accueil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListEtudiantFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListEtudiantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListEtudiantFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
EtudiantlistAdapter etudiantlistAdapter;
    Classe classe;
    List<Etudiant> etudiants ;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
ListView recycleview ;
    Enseignant enseignant = new Enseignant();
    private OnFragmentInteractionListener mListener;

    public ListEtudiantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListEtudiantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListEtudiantFragment newInstance(String param1, String param2) {
        ListEtudiantFragment fragment = new ListEtudiantFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_list_etudiant, container, false);
        recycleview = (ListView)v.findViewById(R.id.recycleetudiant);

        classe = getArguments().getParcelable("classe");
        etudiants = classe.getEtudiants();
        enseignant = getArguments().getParcelable("ENS");
        etudiantlistAdapter = new EtudiantlistAdapter(getActivity(),R.layout.champ_etudiant,etudiants);
        recycleview.setAdapter(etudiantlistAdapter);



        return  v ;
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
    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {


                    MesClasseFragment mesClasseFragment = new MesClasseFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ENS", enseignant);
                    mesClasseFragment.setArguments(bundle);
                    FragmentManager fr = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fr.beginTransaction();
                    ft.replace(R.id.main_frame, mesClasseFragment)
                            .commit();

                }

                return false;
            }
        });
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
