package esprit.org.espritappliaction.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.shamanland.fab.FloatingActionButton;
import com.shamanland.fab.ShowHideOnScroll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import esprit.org.espritappliaction.Adapter.AdapterAbsence;
import esprit.org.espritappliaction.Service.AbsenceDispoService;
import esprit.org.espritappliaction.Service.AbsenceService;
import esprit.org.espritappliaction.Service.SaisieAbsenceService;
import esprit.org.espritappliaction.Service.SupprimerAbsenceService;
import esprit.org.espritappliaction.Service.VerifAbsence;
import esprit.org.espritappliaction.Entity.AbsenceEns;
import esprit.org.espritappliaction.Entity.Classe;
import esprit.org.espritappliaction.Entity.Enseignant;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.Entity.Module;
import esprit.org.espritappliaction.R;
import esprit.org.espritappliaction.View.Enseignat_Accueil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AbsenceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AbsenceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AbsenceFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Spinner spinnerClasse;
    Spinner spinnerModule;
    Spinner spinnerSeance;
    DatePicker datePicker;
    RecyclerView recyclerView;
    Enseignant enseignant;
    Button confirmer;
    String cls;
    String sce ;
    String mod;
    String formatedDate;
    int an ;
    Classe cl = new Classe();
    AdapterAbsence adapterAbsence;
    ArrayList<String> classes = new ArrayList<String>();
    ArrayList<Etudiant> etudiantlist = new ArrayList<>();
    ArrayList<String> modules ;
    ArrayList<String> idmod ;
    AbsenceDispoService absenceDispoService;
    ArrayList<String> field ;
    ArrayList<AbsenceEns> addAbs;
    private OnFragmentInteractionListener mListener;
    FloatingActionButton fab;
    ScrollView scroll ;

    public AbsenceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AbsenceFragment.
     */

    public static AbsenceFragment newInstance(String param1, String param2) {
        AbsenceFragment fragment = new AbsenceFragment();
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

        View v = inflater.inflate(R.layout.fragment_absence, container, false);
         fab = (FloatingActionButton) v.findViewById(R.id.fab);
        scroll = (ScrollView) v.findViewById(R.id.scroll) ;
        enseignant = getArguments().getParcelable("ENS");
        for(Classe d : enseignant.getListClasse().values()) {
            classes.add(d.getId());

        }
        spinnerClasse =(Spinner)v.findViewById(R.id.idclas);
        spinnerSeance =(Spinner)v.findViewById(R.id.idseance);
        spinnerModule =(Spinner)v.findViewById(R.id.idmodule);
        datePicker = (DatePicker)v.findViewById(R.id.datepiker);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycleabs);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        fab.setColor(Color.RED);
        fab.initBackground();
        fab.setVisibility(v.GONE);
       confirmer = (Button)v.findViewById(R.id.confirmer);
        recyclerView.setOnTouchListener(new ShowHideOnScroll(fab));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.seance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeance.setAdapter(adapter);
        ArrayAdapter<String> classe = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, classes);
        classe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClasse.setAdapter(classe);
confirmer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        field = new ArrayList<String>();
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear() - 1900;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            formatedDate = sdf.format(new Date(year, month, day));
            an = datePicker.getYear() - 1;
            field.add(formatedDate);
            field.add(cls);
            field.add(sce);
            field.add(mod);
            field.add("" + an);
            field.add(enseignant.getIdenseignant());
            AbsenceService absenceService = new AbsenceService();
            try {
                cl = absenceService.execute(field).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            adapterAbsence = new AdapterAbsence(cl);
            recyclerView.setAdapter(adapterAbsence);


    }


});

        spinnerSeance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sce = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
spinnerModule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mod= idmod.get(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.fullScroll(ScrollView.FOCUS_UP);
                return false;
            }
        });




        spinnerClasse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cls = parent.getItemAtPosition(position).toString();
                List<Module> liste = new ArrayList<Module>();
                modules = new ArrayList<String>();
                idmod = new ArrayList<String>();
                for(Module d : enseignant.getListModule().get(cls)) {
               modules.add(d.getDesignation());
                    idmod.add(d.getIdModule());

                }
                ArrayAdapter<String> mdl = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item,modules);
                mdl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModule.setAdapter(mdl);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setHasOptionsMenu(true);
        return  v ;
    }


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
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_check_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuchek:
                absenceDispoService=new AbsenceDispoService();
                boolean dispo = false;
                try {
                    dispo = absenceDispoService.execute(field).get();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (dispo) {
                    etudiantlist = adapterAbsence.etudiantAbsent();
                    addAbs = new ArrayList<AbsenceEns>();
                    for (Etudiant e :etudiantlist) {
                        AbsenceEns absenceEns = new AbsenceEns();
                        absenceEns.setAnne_deb("" + an);
                        absenceEns.setCode_claasse(cls);
                        absenceEns.setCode_Module(mod);
                        absenceEns.setDate_seance(formatedDate);
                        absenceEns.setId_edutiant(e.getId());
                        absenceEns.setId_enseign(enseignant.getIdenseignant());
                        absenceEns.setNum_seance(sce);
                        absenceEns.setSemestre("2");
                        addAbs.add(absenceEns);
                    }
                        new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                                .setMessage("confirmer vous les absence ?  "+etudiantlist.size())
                                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SaisieAbsenceService saisieAbsenceService = new SaisieAbsenceService();
                                        try {
                                            saisieAbsenceService.execute(addAbs).get();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).setNegativeButton("Non",null).show();





                }
                else {
                    new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Alert")
                            .setMessage("Vous avez deja saisie l absence pour cette date !")
                    .show();

                }
                break;
            case R.id.supp_abs:
               VerifAbsence verifAbsence =new VerifAbsence();
                boolean ver = false;
                try {
                    ver= verifAbsence.execute(field).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if(ver){
                    new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                            .setMessage("supprimer cette absence ?")
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SupprimerAbsenceService supprimerAbsenceService = new SupprimerAbsenceService();
                                    try {
                                       supprimerAbsenceService.execute(field).get();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).setNegativeButton("Non",null).show();



                }
                else {new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Alert")
                        .setMessage("pas d' absence a cette date !")
                        .show();}

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
        }

    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {


                    Intent intent = new Intent(getActivity(), Enseignat_Accueil.class);
                    startActivity(intent);
                    return true;
                    //  FragmentManager mg = getFragmentManager();
                    //mg.beginTransaction().replace(R.id.frame, new Welcom_Fragment(), "welcom").commit();

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
        void onFragmentInteraction(Uri uri);
    }
}
