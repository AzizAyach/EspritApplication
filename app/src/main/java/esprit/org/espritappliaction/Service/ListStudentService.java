package esprit.org.espritappliaction.Service;

import android.app.ProgressDialog;
import android.content.Context;
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
import java.util.List;

import esprit.org.espritappliaction.Entity.Etudiant;

/**
 * Created by Yahya on 07/03/2016.
 */
public class ListStudentService extends AsyncTask<Void,Void,List<Etudiant>> {

    Context context;
    ProgressDialog mProgressDialog;
    String classe;
    List<Etudiant> etudiants;

    public ListStudentService(Context context, String classe) {
        this.classe = classe;
        this.context = context;
        etudiants = new ArrayList<Etudiant>();

    }

    @Override
    protected List<Etudiant> doInBackground(Void... params) {


        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        String url = "http://esprit-tn.com/ws/ListEtudiants/listEtudiants/" + classe;
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("content-type", "application/json");
        String result;
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);

            try {
                if (params != null) {


                    result = EntityUtils.toString(response.getEntity());
                    System.out.println(result);
                    JSONArray array = new JSONArray(result);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject x;
                        x = array.getJSONObject(i);
                        System.out.println(x.toString());
                        etudiants.add(new Etudiant(x.getString("NOM_ET"), x.getString("PNOM_ET"), x.getString("id_et")));

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


        return etudiants;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Create a progressdialog
        mProgressDialog = new ProgressDialog(context);
        // Set progressdialog title
        mProgressDialog.setTitle("Loading list of students");
        // Set progressdialog message
        mProgressDialog.setMessage("Wait...");
        mProgressDialog.setIndeterminate(false);
        // Show progressdialog

        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(List<Etudiant> etudiants) {
        super.onPostExecute(etudiants);
        mProgressDialog.dismiss();
    }
}
