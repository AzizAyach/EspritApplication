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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import esprit.org.espritappliaction.Entity.Evenement;

/**
 * Created by ayach on 3/7/16.
 */
public class EventAfficherService extends AsyncTask<Void,Void,ArrayList<Evenement>> {
    @Override
    protected ArrayList<Evenement> doInBackground(Void... params) {
        ArrayList<Evenement> events = new ArrayList<Evenement>();
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:18080/WebserviceApplication-web/esprit/event/afficher");
        httpGet.addHeader("content-type", "application/json");
        String result;

        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);

            try {
                if (params != null) {


                    result= EntityUtils.toString(response.getEntity());
                    JSONArray jsonarray = new JSONArray(result);

                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobj = jsonarray.getJSONObject(i);

                            Evenement ev = new  Evenement ();
                            String logmentstring = jsonobj.toString();
                            System.out.println(logmentstring);
                            Gson gpr = new Gson();
                            ev  = gpr.fromJson(String.valueOf(jsonobj), Evenement.class);
                            events.add(ev);


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



        return events;
    }
}
