package esprit.org.espritappliaction.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import esprit.org.espritappliaction.Service.ListAbsService;
import esprit.org.espritappliaction.Entity.Absence_Stat;
import esprit.org.espritappliaction.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisplayStatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisplayStatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayStatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
   int nbAbsc=0;
    int duree=0;
    private OnFragmentInteractionListener mListener;
    private LinearLayout mainLayout;
    private PieChart mChart;
    Bundle bundle;
    // we're going to display pie chart for smartphones martket shares

     float[] yData;
    private String[] xData = {"Presence","Absence" };
List<Absence_Stat> absences;
    public DisplayStatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayStatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayStatFragment newInstance(String param1, String param2) {
        DisplayStatFragment fragment = new DisplayStatFragment();
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
        view=inflater.inflate(R.layout.fragment_display_stat, container, false);

     bundle=this.getArguments();
      duree=Integer.parseInt(bundle.getString("duree").substring(0,2));
        mainLayout = (LinearLayout) view.findViewById(R.id.mainLayout);
        mChart = new PieChart(getActivity());
        mChart.setMinimumWidth(500);
        mChart.setMinimumHeight(500);
        // add pie chart to main layout
        mainLayout.addView(mChart);

        ListAbsService absenceService=new ListAbsService(getActivity(),bundle.getString("id"));
        try {
            absences=absenceService.execute().get();
            System.out.println("xwxw= "+ absences.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


      for(Absence_Stat x:absences)
        {
            if(x.getCode_module().equals(bundle.getString("module")))
            {
                nbAbsc++;

            }

        }
        System.out.println("nb abs "+nbAbsc+" / "+duree);
        yData = new float[]{duree - nbAbsc, nbAbsc};
    //    mainLayout.setBackgroundColor(Color.parseColor("#55656C"));

        // configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription(bundle.getString("nom")+" "+bundle.getString("prenom"));

        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        // set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(getActivity(),
                        xData[e.getXIndex()] + " = " + e.getVal() +" fois", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected() {

            }
        });

        addData();

        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

            return view;



    }



            // TODO: Rename method, update argument and hook method into UI event
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Absence Statistique");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

       /* for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);*/
        colors.add(getResources().getColor(R.color.green));
        colors.add(getResources().getColor(R.color.Red));
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();

    }
}
