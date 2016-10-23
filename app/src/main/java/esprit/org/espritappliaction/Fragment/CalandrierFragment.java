package esprit.org.espritappliaction.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.pdfview.PDFView;

import java.io.File;
import java.util.concurrent.ExecutionException;

import esprit.org.espritappliaction.Service.DownloadPdf;
import esprit.org.espritappliaction.Entity.Etudiant;
import esprit.org.espritappliaction.R;
import esprit.org.espritappliaction.View.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalandrierFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalandrierFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalandrierFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    PDFView pdfView;

    private String mParam1;
    private String mParam2;
    Etudiant etudiant = new Etudiant();
    private OnFragmentInteractionListener mListener;

    public CalandrierFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalandrierFragment.
     */

    public static CalandrierFragment newInstance(String param1, String param2) {
        CalandrierFragment fragment = new CalandrierFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calandrier, container, false);
        pdfView = (PDFView)v.findViewById(R.id.pdfview);
        etudiant = getArguments().getParcelable("ETD");
        DownloadPdf downloadPdf = new DownloadPdf();
        File file = null;
        try {
        downloadPdf.execute("https://drive.google.com/open?id=0B-eGaHI4eHWxZnUtOW9GcWFKM2c","calndrier.pdf").get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/assets/" + "calndrier.pdf");
        pdfView.fromAsset("document.pdf")
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
return  v;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {


                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.putExtra("ETD",etudiant);
                    startActivity(intent);
                    return true;
                    //  FragmentManager mg = getFragmentManager();
                    //mg.beginTransaction().replace(R.id.frame, new Welcom_Fragment(), "welcom").commit();

                }

                return false;
            }
        });
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
