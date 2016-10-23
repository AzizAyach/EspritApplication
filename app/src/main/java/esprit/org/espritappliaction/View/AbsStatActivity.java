package esprit.org.espritappliaction.View;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import esprit.org.espritappliaction.Fragment.DisplayStatFragment;
import esprit.org.espritappliaction.Fragment.SelectClassFragment;
import esprit.org.espritappliaction.Fragment.SelectModuleFragment;
import esprit.org.espritappliaction.Fragment.SelectStudentFragment;
import esprit.org.espritappliaction.R;


public class AbsStatActivity extends AppCompatActivity
        implements SelectClassFragment.OnFragmentInteractionListener,
        SelectStudentFragment.OnFragmentInteractionListener,
        SelectModuleFragment.OnFragmentInteractionListener,
       DisplayStatFragment.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_stat);
        SelectClassFragment fragment=new SelectClassFragment();
     //   DisplayStatFragment fragment=new DisplayStatFragment();

        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction().replace(R.id.frag_abs_stat,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
