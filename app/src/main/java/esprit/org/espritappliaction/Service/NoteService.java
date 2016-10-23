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
import java.util.ArrayList;

import esprit.org.espritappliaction.Entity.Note;

/**
 * Created by aziz on 07/02/2016.
 */
public class NoteService extends AsyncTask<String,Void,ArrayList<Note>> {

    @Override
    protected ArrayList<Note> doInBackground(String... params) {
        String id = params[0];
        ArrayList<Note> notes = new ArrayList<Note>();
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        String url = "http://esprit-tn.com/ws/Note/Res1/"+id;
        HttpGet  httpGet = new HttpGet(url);
        httpGet.addHeader("content-type", "application/json");
        String result;

        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);

            try {
                if (params != null) {


                    result= EntityUtils.toString(response.getEntity());
                    System.out.println(url);
                    JSONArray jsonarray = new JSONArray(result);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                   String des =jsonobj.getString("DESIGNATION");
                        int c = jsonobj.getInt("COEF");
                        String ens = jsonobj.getString("NOM_ENS");
                        float cc= Float.parseFloat(jsonobj.getString("NOTE_CC"));
                        float tp= Float.parseFloat(jsonobj.getString("NOTE_TP"));
                        float ex= Float.parseFloat(jsonobj.getString("NOTE_EXAM"));
                        Note no = new Note();
                        no.setDesignation(des);
                        no.setCoefficient(c);
                        no.setControle_continue(cc);
                        no.setControle_tp(tp);
                        no.setControle_exam(ex);
                        no.setNom_enseignat(ens);
                        if((jsonobj.getString("ABSENT_CC").equals("O")||jsonobj.getString("ABSENT_EXAM").equals("O")||
                                jsonobj.getString("ABSENT_TP").equals("O"))){

                            no.setAbsence(true);
                        }
                        else{
                            no.setAbsence(false);
                        }
                        if(jsonobj.getString("EXISTE_NOTE_TP").equals("O")){
                            no.setTp_note(true);
                        }else{
                            no.setTp_note(false);

                        }



                        notes.add(no);
                    }

                }
                else {
                    Log.e("Parsing", "No json object from data");
                }
            }
            catch (JSONException e) {
                e.printStackTrace();}

        } catch (IOException e) {
            e.printStackTrace();
        }






return  notes;
    }
}
