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

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import esprit.org.espritappliaction.Entity.AbsenceEns;

/**
 * Created by ayach on 13/02/16.
 */
public class SaisieAbsenceService extends  AsyncTask<ArrayList<AbsenceEns>,Void,Boolean> {

    @Override
    protected Boolean doInBackground(ArrayList<AbsenceEns>... params) {

        ArrayList<AbsenceEns> classeid = new ArrayList<>();
        Boolean type = false;
        classeid = params[0];

        HttpClient httpClientetudiant = new DefaultHttpClient();
        HttpContext localContextetudiant = new BasicHttpContext();


        String resultEtudiant;
        for(AbsenceEns en:classeid) {
            String urletudiant = "http://esprit-tn.com/ws/ABS/Absence/" + URLEncoder.encode(en.getId_edutiant()) + "," + URLEncoder.encode(en.getCode_Module()) + "," + URLEncoder.encode(en.getCode_claasse()) + "," + URLEncoder.encode(en.getAnne_deb()) + ","+en.getNum_seance() +"," + URLEncoder.encode(en.getDate_seance()) + "," +
                    URLEncoder.encode(en.getId_enseign())+","+URLEncoder.encode(en.getSemestre());
            System.out.println(urletudiant);
            HttpGet httpGetetudiant = new HttpGet(urletudiant);
            httpGetetudiant.addHeader("content-type", "application/json");
            try {
                HttpResponse response = httpClientetudiant.execute(httpGetetudiant, localContextetudiant);


                if (params != null) {


                    resultEtudiant = EntityUtils.toString(response.getEntity());
                    System.out.println(resultEtudiant);

                } else {
                    Log.e("Parsing", "No json object from data");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return true;
    }


}
