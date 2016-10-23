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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import esprit.org.espritappliaction.Entity.Classe;
import esprit.org.espritappliaction.Entity.Enseignant;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.Entity.Module;
import esprit.org.espritappliaction.Entity.User;

/**
 * Created by aziz on 18/01/2016.
 */
public class ClasseEnseignantDao extends AsyncTask<User,Void,Enseignant>{

    ListEtudiantService listEtudiantService =new ListEtudiantService();
    Context context;
    private ProgressDialog progressDialog;
    public ClasseEnseignantDao(Context context){
        this.context=context;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    @Override
    protected Enseignant doInBackground(User... params) {
        User user = new User();
        user = params[0];
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();

        String url ="http://esprit-tn.com/ws/Classes/classes/"+URLEncoder.encode(user.getId())+",1";
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("content-type", "application/json");
        List<String> list = new ArrayList<>();
        String result;
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);

            try {
                if (params != null) {


                    result = EntityUtils.toString(response.getEntity());
                    JSONArray classe = new JSONArray(result);
                    for (int i = 0; i < classe.length(); i++) {
                        JSONObject jsonobj = classe.getJSONObject(i);
                         String nbclasse = jsonobj.getString("CODE_CL");
                       list.add(nbclasse);
                        System.out.println(nbclasse);


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

        HttpClient httpClient2 = new DefaultHttpClient();
        HttpContext localContext2 = new BasicHttpContext();

        String url2 ="http://esprit-tn.com/ws/Classes/classes/"+URLEncoder.encode(user.getId())+",2";
        System.out.println(url2);
        HttpGet httpGet2 = new HttpGet(url2);
        httpGet2.addHeader("content-type", "application/json");
        List<String> List2 = new ArrayList<>();
        String result2;
        try {
            HttpResponse response2 = httpClient2.execute(httpGet2, localContext2);

            try {
                if (params != null) {


                    result2 = EntityUtils.toString(response2.getEntity());
                    JSONArray classe2 = new JSONArray(result2);
                    for (int i = 0; i < classe2.length(); i++) {
                        JSONObject jsonobj = classe2.getJSONObject(i);
                        String nbclasse2 = jsonobj.getString("CODE_CL");
                        List2.add(nbclasse2);
                        System.out.println(nbclasse2);
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



//recupertaion  des attrubut enseignat
        Map<String,Classe> ListClasse = new HashMap<>();
        Map<String,Classe> ListClasse1 = new HashMap<>();
        Map  ListClasseAll = new HashMap<>();
        Map<String,List<Module>> ListModule1 = new HashMap<>();
        Map<String,List<Module>> ListModule2 = new HashMap<>();
        Map  ListModuleAll = new HashMap<>();
        Enseignant enseignant = new Enseignant();

        try {
            ListClasse = this.listEtudiant(list,1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ListClasse1= this.listEtudiant(List2,2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ListClasseAll.putAll(ListClasse);
        ListClasseAll.putAll(ListClasse1);
        try {
          ListModule1= this.listModule(list,user,1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ListModule2= this.listModule(List2,user,2);
        } catch (Exception e) {
            e.printStackTrace();
        }
ListModuleAll.putAll(ListModule1);
        ListModuleAll.putAll(ListModule2);
enseignant.setIdenseignant(user.getId());
        enseignant.setListClasse(ListClasseAll);
        enseignant.setListModule(ListModuleAll);

ListClasse.get(1);
        ListClasse.get(2);
        //Log.d("map", ListClasse.get(1).toString());
       // System.out.println(ListClasse.get(1).toString() );
        return enseignant;
    }

    public Map<String,Classe> listEtudiant(List<String> classeid ,int sem){
        Map<String,Classe> ListClasse = new HashMap<>();
        HttpClient httpClientetudiant = new DefaultHttpClient();
        HttpContext localContextetudiant = new BasicHttpContext();
        ArrayList <Etudiant> list = null;

        for (String s : classeid) {
            String resultEtudiant;
            list = new ArrayList<Etudiant>();
            String urletudiant ="http://esprit-tn.com/ws/ListEtudiants/listEtudiants/"+ URLEncoder.encode(s);
            System.out.println(urletudiant);
            HttpGet httpGetetudiant = new HttpGet(urletudiant);
            httpGetetudiant.addHeader("content-type", "application/json");
            try {
                HttpResponse response = httpClientetudiant.execute(httpGetetudiant, localContextetudiant);

                try {
                    if (classeid.size()!= 0) {


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

                        Classe cls = new Classe();
                        cls.setId(s);
                        cls.setEtudiants(list);
                        cls.setSemsetre(sem);
                        ListClasse.put(s,cls);





                    } else {
                        Log.e("Parsing", "No json object from data");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        return  ListClasse;



    }

    public Map<String,List<Module>> listModule(List<String> list,User user,int sem){
        Map<String,List<Module>> MapModule = new HashMap<>();
        HttpClient httpClientetudiant = new DefaultHttpClient();
        HttpContext localContextetudiant = new BasicHttpContext();

        ArrayList<Module> modulelist  =null ;
        for (String s : list) {
            String resultEtudiant;
            modulelist = new ArrayList<>();
            String urletudiant ="http://esprit-tn.com/ws/Modules/modules/"+URLEncoder.encode(s)+","+URLEncoder.encode(user.getId())+","+sem;
            System.out.println(urletudiant);
            HttpGet httpGetetudiant = new HttpGet(urletudiant);
            httpGetetudiant.addHeader("content-type", "application/json");
            try {
                HttpResponse response = httpClientetudiant.execute(httpGetetudiant, localContextetudiant);

                try {
                    if (list.size()!= 0) {


                        resultEtudiant = EntityUtils.toString(response.getEntity());
                        JSONArray jsonArray = new JSONArray(resultEtudiant);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobj = jsonArray.getJSONObject(i);
                            String idmodule = jsonobj.getString("CODE_MODULE");
                            String designation = jsonobj.getString("DESIGNATION");

                            Module module = new Module();
                            module.setIdModule(idmodule);
                            module.setDesignation(designation);
                            module.setIdClasse(s);
                            modulelist.add(module);





                        }

MapModule.put(s,modulelist);




                    } else {
                        Log.e("Parsing", "No json object from data");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        return  MapModule;






    }


    @Override
    protected void onPostExecute(Enseignant ens) {
        progressDialog.dismiss();
    }




    }



