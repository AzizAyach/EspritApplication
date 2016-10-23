package esprit.org.espritappliaction.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.concurrent.ExecutionException;

import esprit.org.espritappliaction.Service.Autehntification_service;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.Entity.User;
import esprit.org.espritappliaction.R;

public class MainActivity extends AppCompatActivity {
    private KenBurnsView mKenBurns;
    private EditText profil;
    private EditText psw;
    private EditText card;
    private Button submit;
    Autehntification_service autehntification_service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mKenBurns = (KenBurnsView) findViewById(R.id.backlogin);
        profil = (EditText) findViewById(R.id.edutiantfield);
        psw = (EditText) findViewById(R.id.passwordfield);
        card = (EditText) findViewById(R.id.cardfield);
        submit = (Button) findViewById(R.id.submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                Etudiant etudiant = new Etudiant();
                autehntification_service =new Autehntification_service();
                user.setId(profil.getText().toString());
                user.setCin(card.getText().toString());
                user.setPasword(psw.getText().toString());
                try {
                    etudiant = autehntification_service.execute(user).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (etudiant.getNom()!=null){


                    Toast.makeText(MainActivity.this, "Welecom:" + etudiant.getNom() + " "+etudiant.getPrenom(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,
                            HomeActivity.class);
                    intent.putExtra("ETD",etudiant);
                    startActivity(intent);
                    finish();
                }

                else if (profil.getText().toString().equals("P-13-08")){

                    Intent intent = new Intent(MainActivity.this,
                            Enseignat_Accueil.class);
                    startActivity(intent);
                    finish();


                }
                else{

                    Toast.makeText(MainActivity.this, "verifier vos cordonnees", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }
}
