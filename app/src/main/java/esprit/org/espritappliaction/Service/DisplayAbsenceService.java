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

import esprit.org.espritappliaction.Entity.Absence;

/**
 * Created by ayach on 14/02/16.
 */
public class DisplayAbsenceService extends AsyncTask<String,Void,ArrayList<Absence>> {
    @Override
    protected ArrayList<Absence> doInBackground(String... params) {
        String id = params[0];

        HttpClient httpClientetudiant = new DefaultHttpClient();
        HttpContext localContextetudiant = new BasicHttpContext();
        ArrayList <Absence> list = new ArrayList<Absence>();


        String resultEtudiant;

        String urletudiant ="http://esprit-tn.com/ws/service1/Absence//"+URLEncoder.encode(id);
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
                        JSONObject jsonobj = jsonArray.getJSONObject(i);;
                        String jsonseance = jsonobj.getString("NUM_SEANCE");

           String seance = "" ;
            switch (jsonseance)
            {
                case "1" :
                    seance = "9:00 à 10:30";
                    break;
                case "2" :
                    seance = "10:45 à 12:15";
                    break;
                case "3" :
                    seance = "14:00 à 15:30";
                    break;
                case "4" :
                    seance = "15:45 à 17:15";
                    break;

            }
                        String datejson = jsonobj.getString("DATE_SEANCE");
                        int debut = datejson.indexOf(">");
                        int fin = datejson.indexOf("<", debut);
                        String texte = datejson.substring(debut + 1, fin);
                        String date = texte.substring(0,11);
                        System.out.println(date);


                        Absence abs = new Absence();
                        abs.setName(jsonobj.getString("ID_ET"));
                        abs.setNum_seance(seance);
                        abs.setCode_classe(jsonobj.getString("CODE_CL"));
                        abs.setCode_Module(jsonobj.getString("CODE_MODULE"));
                        abs.setDate_abs(date);
                        list.add(abs);


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

        return  list;
    };
    }





