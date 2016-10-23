package esprit.org.espritappliaction.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import esprit.org.espritappliaction.Adapter.ListAbsenceAdpter;
import esprit.org.espritappliaction.Service.DisplayAbsenceService;
import esprit.org.espritappliaction.Entity.Absence;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.R;
import esprit.org.espritappliaction.View.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AbsenceEtudiantAbsence.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AbsenceEtudiantAbsence#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AbsenceEtudiantAbsence extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
ListView absencelist;
    ArrayList<Absence> abs = new ArrayList<Absence>();
    Etudiant etudiant = new Etudiant();
    private OnFragmentInteractionListener mListener;

    public AbsenceEtudiantAbsence() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AbsenceEtudiantAbsence.
     */
    // TODO: Rename and change types and number of parameters
    public static AbsenceEtudiantAbsence newInstance(String param1, String param2) {
        AbsenceEtudiantAbsence fragment = new AbsenceEtudiantAbsence();
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
       View v = inflater.inflate(R.layout.fragment_absence_etudiant_absence, container, false);
        absencelist = (ListView)v.findViewById(R.id.absence_lista);

        DisplayAbsenceService displayAbsenceService = new DisplayAbsenceService();
        etudiant = getArguments().getParcelable("ETD");
        try {
       abs = displayAbsenceService.execute(etudiant.getId()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        ListAbsenceAdpter listAbsenceAdpter = new ListAbsenceAdpter(getActivity(),R.layout.row_absence,abs);
        absencelist.setAdapter(listAbsenceAdpter);
        


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
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {


                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.putExtra("ETD",etudiant);
                    startActivity(intent);
                    return true;
                    //  FragmentManager mg = getFragmentManager();
                    //mg.beginTransaction().replace(R.id.frame, new Welcom_Fragment(), "welcom").commit();

                }

                return false;
            }
        });
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
