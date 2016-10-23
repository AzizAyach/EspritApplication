package esprit.org.espritappliaction.Service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.Entity.User;

/**
 * Created by aziz on 16/01/2016.
 */
public class Autehntification_service extends AsyncTask<User,Void,Etudiant> {



    @Override
    protected Etudiant doInBackground(User... params) {
        User user = new User();
        Etudiant etudiant = new Etudiant();
        user = params[0];
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();

                String url ="http://esprit-tn.com/ws/Authentification/login/"+URLEncoder.encode(user.getId()) +","+URLEncoder.encode(user.getCin())+","+URLEncoder.encode(user.getPasword());
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("content-type", "application/json");
        String result;
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);

            try {
                if (params != null) {


                    result = EntityUtils.toString(response.getEntity());
                    JSONObject x = new JSONObject(result);
                    String edutStr = x.toString();
                    String mail = x.getString("ADRESSE_MAIL_ESP");
                    String nom = x.getString("NOM_ET");
                    String cin = x.getString("NUM_CIN_PASSEPORT");
                    String prenom = x.getString("PRENOM_ET");
                    String id = x.getString("ID_ET");
                    System.out.println(etudiant);
                    System.out.println(edutStr);
                    Gson gpr = new Gson();
                    etudiant.setId(id);
                    etudiant.setCin(Integer.parseInt(cin));
                    etudiant.setMail(mail);
                    etudiant.setNom(nom);
                    etudiant.setPrenom(prenom);



                } else {
                    Log.e("Parsing", "No json object from data");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return etudiant;
    }
}
