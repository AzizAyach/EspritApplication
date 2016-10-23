package esprit.org.espritappliaction.Service;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by aziz on 18/01/2016.
 */
public class DownloadPdf extends AsyncTask<String, Void, File> {

    @Override
    protected File doInBackground(String... strings) {
        String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
        String fileName = strings[1];  // -> maven.pdf
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory,"assets");
        folder.mkdir();

        File pdfFile = new File(folder, fileName);

        try{
            pdfFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        StorePfd.downloadFile(fileUrl, pdfFile);
        return pdfFile;
    }
}



