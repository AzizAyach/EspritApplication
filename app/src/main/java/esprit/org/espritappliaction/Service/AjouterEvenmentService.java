package esprit.org.espritappliaction.Service;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import esprit.org.espritappliaction.Entity.Evenement;

/**
 * Created by ayach on 3/7/16.
 */
public class AjouterEvenmentService extends AsyncTask<Evenement,Void,Void> {
    @Override
    protected Void doInBackground(Evenement... params) {
        Evenement e = new Evenement();
        e=params[0];
        Gson g = new Gson();
        String src = g.toJson(e,Evenement.class);
        System.out.println(src);
        HttpClient httpClient = new DefaultHttpClient();
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;



        HttpPost httpPost = new HttpPost("https://api.parse.com/1/classes/Logements");
        httpPost.addHeader("content-type","text/plain");
        StringEntity a = null;
        try {
            a = new StringEntity(src);
            httpPost.setEntity(a);
        } catch (UnsupportedEncodingException exc) {
            exc.printStackTrace();

        }
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

    }

}
