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


import java.util.ArrayList;
import java.util.List;

import esprit.org.espritappliaction.Adapter.ClassesAdapter;
import esprit.org.espritappliaction.Service.ListClassService;
import esprit.org.espritappliaction.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectClassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectClassFragment extends Fragment implements Spinner.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
Spinner semetreSpinner;
 ListView listView;
    List<String> classes;
    Bundle bundle;
    ClassesAdapter classesAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectClassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectClassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectClassFragment newInstance(String param1, String param2) {
        SelectClassFragment fragment = new SelectClassFragment();
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


       view= inflater.inflate(R.layout.fragment_select_class, container, false);
        semetreSpinner = (Spinner) view.findViewById(R.id.semestreSpinner);
        String[] array_spinner = new String[2];
        array_spinner[0] = "Semestre 1";
        array_spinner[1] = "Semestre 2";


        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item, array_spinner);
        semetreSpinner.setAdapter(adapter);

        semetreSpinner.setOnItemSelectedListener(this);

        listView=(ListView)view.findViewById(R.id.listClass);

        ListClassService listClassService;
classes=new ArrayList<String>();

        listClassService=new ListClassService(getActivity(),"P-13-08",1);



                try {
                    classes=listClassService.execute().get();

                    classesAdapter = new ClassesAdapter(getActivity(),R.layout.class_one_row, classes);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             bundle = new Bundle();
                bundle.putString("class", classes.get(position).toString());
                bundle.putString("semestre",String.valueOf(semetreSpinner.getSelectedItemPosition()+1));
              //  SelectStudentFragment fragment = new SelectStudentFragment();
                SelectModuleFragment fragment=new SelectModuleFragment();
                fragment.setArguments(bundle);
               FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frag_abs_stat, fragment, "ModuleFragment");
                ft.commit();


            }
        });




                return view;
       // return inflater.inflate(R.layout.fragment_select_class, container, false);
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
        ListClassService listClassService;

        if(position==0)
        {




            try {

                classes=new ArrayList<String>();

                listClassService=new ListClassService(getActivity(),"P-13-08",1);
                classes=listClassService.execute().get();

                classesAdapter = new ClassesAdapter(getActivity(),R.layout.class_one_row, classes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            listView.setAdapter(classesAdapter);


        }
        else {
try {
    classes=new ArrayList<String>();



    listClassService=new ListClassService(getActivity(),"P-13-08",2);
            classes=listClassService.execute().get();

    classesAdapter = new ClassesAdapter(getActivity(),R.layout.class_one_row, classes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        listView.setAdapter(classesAdapter);



        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
