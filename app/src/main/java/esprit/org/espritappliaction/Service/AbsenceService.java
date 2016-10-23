package esprit.org.espritappliaction.Service;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import esprit.org.espritappliaction.Entity.Classe;
import esprit.org.espritappliaction.Entity.Etudiant;

/**
 * Created by aziz on 25/01/2016.
 */
public class AbsenceService extends AsyncTask<List<String>,Void,Classe> {
    List<String> list = new ArrayList<>();

    @Override
    protected Classe doInBackground(List<String>... params) {
        List<String> classeid = new ArrayList<>();
        Classe cls = new Classe();
        classeid = params[0];

        HttpClient httpClientetudiant = new DefaultHttpClient();
        HttpContext localContextetudiant = new BasicHttpContext();
        ArrayList <Etudiant> list = new ArrayList<Etudiant>();


            String resultEtudiant;

            String urletudiant ="http://esprit-tn.com/ws/Affichageabsences/Affichage/"+ URLEncoder.encode(classeid.get(0))+","+URLEncoder.encode(classeid.get(2))+","+URLEncoder.encode(classeid.get(1));
            System.out.println(urletudiant);
            HttpGet httpGetetudiant = new HttpGet(urletudiant);
            httpGetetudiant.addHeader("content-type", "application/json");
            try {
                HttpResponse response = httpClientetudiant.execute(httpGetetudiant, localContextetudiant);

                try {
                    if (params != null) {


                        resultEtudiant = EntityUtils.toString(response.getEntity());
                        JSONArray jsonArray = new JSONArray(resultEtudiant);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobj = jsonArray.getJSONObject(i);
                            String idetudiant = jsonobj.getString("ID_ET");
                            String nometudiant = jsonobj.getString("NOM_ET");
                            String prenometudiant = jsonobj.getString("PNOM_ET");

                            Etudiant etudiant = new Etudiant();
                            etudiant.setId(idetudiant);
                            etudiant.setNom(nometudiant);
                            etudiant.setPrenom(prenometudiant);

                            list.add(etudiant);


                        }


                    } else {
                        Log.e("Parsing", "No json object from data");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }catch (IOException e) {
                e.printStackTrace();
            }

            cls.setId(classeid.get(1));

        cls.setEtudiants(list);
        return  cls;
    }
    }

