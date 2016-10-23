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
 * Created by aziz on 19/01/2016.
 */
public class ListEtudiantService extends AsyncTask<List<String>,Void,ArrayList<Classe>> {
    @Override
    protected ArrayList<Classe> doInBackground(List<String>... params) {
List<String> classeid = new ArrayList<>();
        classeid = params[0];

        HttpClient httpClientetudiant = new DefaultHttpClient();
        HttpContext localContextetudiant = new BasicHttpContext();
        ArrayList <Etudiant> list = new ArrayList<Etudiant>();
        ArrayList<Classe> classelist = new ArrayList<>();

        for (String s : classeid) {
            String resultEtudiant;
            String urletudiant ="http://esprit-tn.com/ws/ListEtudiants/listEtudiants/"+ URLEncoder.encode(s);
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
                            String idetudiant = jsonobj.getString("id_et");
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

            } catch (IOException e) {
                e.printStackTrace();
            }


            Classe cls = new Classe();
            cls.setId(s);
            cls.setEtudiants(list);
            classelist.add(cls);
        }
     return  classelist;
    }
}
