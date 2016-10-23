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
import java.util.List;

/**
 * Created by aziz on 08/02/2016.
 */
public class AbsenceDispoService extends AsyncTask<ArrayList<String>,Void,Boolean> {


    @Override
    protected Boolean doInBackground(ArrayList<String>... params) {
        List<String> classeid = new ArrayList<>();
        Boolean type = false;
        classeid = params[0];

        HttpClient httpClientetudiant = new DefaultHttpClient();
        HttpContext localContextetudiant = new BasicHttpContext();


        String resultEtudiant;

        String urletudiant ="http://esprit-tn.com/ws/EntetABScs/EntetABS/"+URLEncoder.encode(classeid.get(3))+","+URLEncoder.encode(classeid.get(4))+","+URLEncoder.encode(classeid.get(2))+","+URLEncoder.encode(classeid.get(1))+",1,"+URLEncoder.encode(classeid.get(0))+","+URLEncoder.encode(classeid.get(5));
        System.out.println(urletudiant);
        HttpGet httpGetetudiant = new HttpGet(urletudiant);
        httpGetetudiant.addHeader("content-type", "application/json");
        try {
            HttpResponse response = httpClientetudiant.execute(httpGetetudiant, localContextetudiant);


                if (params != null) {


                    resultEtudiant = EntityUtils.toString(response.getEntity());
                    System.out.println(resultEtudiant);
                    if(resultEtudiant.equals("true")){
                        type=true;

                    }
                   else {}

                } else {
                    Log.e("Parsing", "No json object from data");
                }


        }catch (IOException e) {
            e.printStackTrace();
        }




        return type;
    }
}
