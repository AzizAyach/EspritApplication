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

/**
 * Created by Yahya on 06/03/2016.
 */
public class ListClassService extends AsyncTask<Void,Void,List<String>> {

    Context context;
    ProgressDialog mProgressDialog;
    String idProf;
    int semestre;
    List<String> list;
    public ListClassService(Context context,String idProf,int semestre)
    {
        this.idProf=idProf;
        this.context=context;
        list=new ArrayList<String>();
        this.semestre=semestre;
    }

    @Override
    protected List<String> doInBackground(Void... params) {


        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        String url = "http://esprit-tn.com/ws/Classes/classes/" + idProf+","+semestre;
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("content-type", "application/json");
        String result;
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);

            try {
                if (params != null) {



                    result = EntityUtils.toString(response.getEntity());
                    System.out.println(result);
                    JSONArray array= new JSONArray(result);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject x;
                        x=array.getJSONObject(i);
                        System.out.println(x.toString());
                        list.add(x.getString("CODE_CL"));

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


        return list;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Create a progressdialog
        mProgressDialog = new ProgressDialog(context);
        // Set progressdialog title
        mProgressDialog.setTitle("Loading list of classes");
        // Set progressdialog message
        mProgressDialog.setMessage("Wait...");
        mProgressDialog.setIndeterminate(false);
        // Show progressdialog

        mProgressDialog.show();
    }
    @Override
    protected void onPostExecute(List<String> classes) {
        super.onPostExecute(classes);
        mProgressDialog.dismiss();
    }

}
