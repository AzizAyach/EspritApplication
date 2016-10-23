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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import esprit.org.espritappliaction.Adapter.ClassesAdapter;
import esprit.org.espritappliaction.Service.ListModuleService;
import esprit.org.espritappliaction.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectModuleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectModuleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectModuleFragment extends Fragment implements Spinner.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    Spinner moduleSpinner;
    ListView listView;
    List<String> modules;
    Bundle bundle;
    ClassesAdapter classesAdapter;
    private OnFragmentInteractionListener mListener;

    public SelectModuleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectModuleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectModuleFragment newInstance(String param1, String param2) {
        SelectModuleFragment fragment = new SelectModuleFragment();
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
        view=inflater.inflate(R.layout.fragment_select_module, container, false);

        moduleSpinner = (Spinner) view.findViewById(R.id.ModuleSpinner);
        String[] array_spinner = new String[2];
        array_spinner[0] = "24 Heures";
        array_spinner[1] = "48 Heures";

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, array_spinner);
        moduleSpinner.setAdapter(adapter);
        moduleSpinner.setOnItemSelectedListener(this);


        listView=(ListView)view.findViewById(R.id.listModule);

        ListModuleService listModuleService;
        modules=new ArrayList<String>();
        bundle = this.getArguments();
        listModuleService=new ListModuleService(getActivity(),bundle.getString("class"),"P-13-08",Integer.parseInt(bundle.getString("semestre")));



        try {
            modules=listModuleService.execute().get();
            classesAdapter = new ClassesAdapter(getActivity(),R.layout.class_one_row, modules);
            listView.setAdapter(classesAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             //   bundle = new Bundle();
                bundle.putString("module", modules.get(position).toString());

                bundle.putString("duree", String.valueOf(moduleSpinner.getSelectedItem().toString()));
                 SelectStudentFragment fragment = new SelectStudentFragment();

                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frag_abs_stat, fragment, "displayStat");
                ft.commit();


            }
        });









        return view;
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position==0)
        {   Toast toast = Toast.makeText(getActivity(), "24 Heures", Toast.LENGTH_LONG);
            toast.show();

        }
        else {
            Toast toast = Toast.makeText(getActivity(), "48 Heures", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
