package esprit.org.espritappliaction.View;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.goka.blurredgridmenu.GridMenu;
import com.goka.blurredgridmenu.GridMenuFragment;

import java.util.ArrayList;
import java.util.List;

import esprit.org.espritappliaction.Service.DisplayAbsenceService;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.Fragment.AbsenceEtudiantAbsence;
import esprit.org.espritappliaction.Fragment.AbsenceFragment;
import esprit.org.espritappliaction.Fragment.AjoutEvenementFragment;
import esprit.org.espritappliaction.Fragment.CalandrierFragment;;
import esprit.org.espritappliaction.Fragment.EventFragment;
import esprit.org.espritappliaction.Fragment.ExtraFragment;
import esprit.org.espritappliaction.Fragment.ExtraLogementFragment;
import esprit.org.espritappliaction.Fragment.HomeFragment;
import esprit.org.espritappliaction.Fragment.ListEtudiantFragment;
import esprit.org.espritappliaction.Fragment.MesClasseFragment;
import esprit.org.espritappliaction.Fragment.NoteFragment;
import esprit.org.espritappliaction.Fragment.TimeLineFragment;
import esprit.org.espritappliaction.R;

public class HomeActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener
,CalandrierFragment.OnFragmentInteractionListener,TimeLineFragment.OnFragmentInteractionListener,ListEtudiantFragment.OnFragmentInteractionListener
        ,MesClasseFragment.OnFragmentInteractionListener,ExtraFragment.OnFragmentInteractionListener
,AbsenceFragment.OnFragmentInteractionListener,
        ExtraLogementFragment.OnFragmentInteractionListener,NoteFragment.OnFragmentInteractionListener
,AbsenceEtudiantAbsence.OnFragmentInteractionListener,EventFragment.OnFragmentInteractionListener,
        AjoutEvenementFragment.OnFragmentInteractionListener{
    private GridMenuFragment mGridMenuFragment;
    Etudiant etudiant = new Etudiant();
    List<GridMenu> menus ;
   // TODO: Update blank fragment layout -->
    DisplayAbsenceService displayAbsenceService = new DisplayAbsenceService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mGridMenuFragment = GridMenuFragment.newInstance(R.drawable.backhome);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main_frame, mGridMenuFragment);
        tx.addToBackStack(null);
        tx.commit();
        etudiant = getIntent().getParcelableExtra("ETD");
        menus= new ArrayList<>();
        setupGridMenu();




        mGridMenuFragment.setOnClickMenuListener(new GridMenuFragment.OnClickMenuListener() {
            @Override
            public void onClickMenu(GridMenu gridMenu, int position) {
              if ( gridMenu.getTitle().equals("Calendar")){

                  CalandrierFragment calandrierFragment =new CalandrierFragment();
                  FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                  Bundle bundle =new Bundle();
                  bundle.putParcelable("ETD", etudiant);
                  calandrierFragment.setArguments(bundle);
                  tx.replace(R.id.main_frame, calandrierFragment);
                  tx.addToBackStack(null);
                  tx.commit();

              }
                if( gridMenu.getTitle().equals("Timeline")){

                    TimeLineFragment timeLineFragment =new TimeLineFragment();
                    FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                    Bundle bundle =new Bundle();
                    bundle.putParcelable("ETD", etudiant);
                    timeLineFragment.setArguments(bundle);
                    tx.replace(R.id.main_frame,timeLineFragment);
                    tx.addToBackStack(null);
                    tx.commit();

                }

                if(gridMenu.getTitle().equals("Extra")){
                    Intent intent = new Intent(HomeActivity.this,PCRemoteActivity.class);
                    intent.putExtra("ETD",etudiant);
                    startActivity(intent);
                    finish();


                }
                if(gridMenu.getTitle().equals("Absence")){

                    AbsenceEtudiantAbsence absence  =new AbsenceEtudiantAbsence();
                    Bundle bundle =new Bundle();
                    bundle.putParcelable("ETD", etudiant);
                    absence.setArguments(bundle);
                    FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                    tx.replace(R.id.main_frame,absence);
                    tx.addToBackStack(null);
                    tx.commit();

                }
                if(gridMenu.getTitle().equals("Markes")){

                    NoteFragment noteFragment =new NoteFragment();
                    Bundle bundle =new Bundle();
                    bundle.putParcelable("ETD", etudiant);
                    noteFragment.setArguments(bundle);
                    FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                    tx.replace(R.id.main_frame,noteFragment);
                    tx.addToBackStack(null);
                    tx.commit();

                }


                if(gridMenu.getTitle().equals("Exit")){
                    Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();


                }

            }
        });


    }
    private void setupGridMenu() {

        menus.add(new GridMenu("Calendar", R.drawable.calendar));
        menus.add(new GridMenu("Markes", R.drawable.overview));
        menus.add(new GridMenu("Extra", R.drawable.lists));
        menus.add(new GridMenu("Absence", R.drawable.profile));
        menus.add(new GridMenu("Timeline", R.drawable.timeline));
        menus.add(new GridMenu("Exit",R.drawable.exit));
        //menus.add(new GridMenu("Deconnexion",))

        mGridMenuFragment.setupMenu(menus);
    }

    @Override
    public void onBackPressed() {
        if (0 == getSupportFragmentManager().getBackStackEntryCount()) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
