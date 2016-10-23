package esprit.org.espritappliaction.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.goka.blurredgridmenu.GridMenu;
import com.goka.blurredgridmenu.GridMenuFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import esprit.org.espritappliaction.Service.ClasseEnseignantDao;
import esprit.org.espritappliaction.Entity.Enseignant;
import esprit.org.espritappliaction.Entity.User;
import esprit.org.espritappliaction.Fragment.AbsenceFragment;
import esprit.org.espritappliaction.Fragment.CalandrierFragment;
import esprit.org.espritappliaction.Fragment.ExtraFragment;
import esprit.org.espritappliaction.Fragment.ExtraLogementFragment;
import esprit.org.espritappliaction.Fragment.HomeFragment;
import esprit.org.espritappliaction.Fragment.ListEtudiantFragment;
import esprit.org.espritappliaction.Fragment.MesClasseFragment;
import esprit.org.espritappliaction.Fragment.NoteFragment;
import esprit.org.espritappliaction.Fragment.TimeLineFragment;
import esprit.org.espritappliaction.R;

public class Enseignat_Accueil extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener
        ,CalandrierFragment.OnFragmentInteractionListener,TimeLineFragment.OnFragmentInteractionListener,ListEtudiantFragment.OnFragmentInteractionListener
        ,MesClasseFragment.OnFragmentInteractionListener,ExtraFragment.OnFragmentInteractionListener
        ,AbsenceFragment.OnFragmentInteractionListener,
        ExtraLogementFragment.OnFragmentInteractionListener,NoteFragment.OnFragmentInteractionListener {
    private GridMenuFragment mGridMenuFragment;
    Enseignant enseignant = new Enseignant();
    List<GridMenu> menus ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enseignat__accueil);
        User user = new User("P-13-08","09","");
        menus = new ArrayList<>();
        mGridMenuFragment = GridMenuFragment.newInstance(R.drawable.backhome);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main_frame, mGridMenuFragment);
        tx.addToBackStack(null);
        tx.commit();
        setupGridMenu();
        ClasseEnseignantDao classeEnseignantDao = new ClasseEnseignantDao(this);
        try {
            enseignant=classeEnseignantDao.execute(user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        mGridMenuFragment.setOnClickMenuListener(new GridMenuFragment.OnClickMenuListener() {
            @Override
            public void onClickMenu(GridMenu gridMenu, int position) {


                if (gridMenu.getTitle().equals("Classe")) {

                    MesClasseFragment mesClasseFragment = new MesClasseFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ENS", enseignant);
                    mesClasseFragment.setArguments(bundle);
                    FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                    tx.replace(R.id.main_frame, mesClasseFragment);
                    tx.addToBackStack(null);
                    tx.commit();

                }
                if (gridMenu.getTitle().equals("Absence")) {

                    AbsenceFragment mesClasseFragment = new AbsenceFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ENS", enseignant);
                    mesClasseFragment.setArguments(bundle);
                    FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                    tx.replace(R.id.main_frame, mesClasseFragment);
                    tx.addToBackStack(null);
                    tx.commit();

                }
                if(gridMenu.getTitle().equals("Stat Absence")){

                    Intent intent = new Intent(Enseignat_Accueil.this,
                            AbsStatActivity.class);
                    startActivity(intent);
                    finish();

                }

                if(gridMenu.getTitle().equals("Exit")){
                    Intent intent = new Intent(Enseignat_Accueil.this,MainActivity.class);
                    startActivity(intent);
                    finish();


                }

            }
        });


    }
    private void setupGridMenu() {

        menus.add(new GridMenu("Classe", R.drawable.classroom));
        menus.add(new GridMenu("Absence", R.drawable.profile));
        menus.add(new GridMenu("Stat Absence",R.drawable.overview));
        menus.add(new GridMenu("Exit",R.drawable.exit));

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
