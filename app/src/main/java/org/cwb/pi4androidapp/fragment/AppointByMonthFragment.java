package org.cwb.pi4androidapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import org.cwb.pi4androidapp.R;
import java.util.ArrayList;
import java.util.Random;

public class AppointByMonthFragment extends Fragment {



    ArrayList<String> dates;
    Random random;
    ArrayList<BarEntry> barEntries;

    public AppointByMonthFragment() {
        // Required empty public constructor
    }

    public static AppointByMonthFragment newInstance(String param1, String param2) {
        AppointByMonthFragment fragment = new AppointByMonthFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_appoint_by_month,container,false);

         BarChart barChart = (BarChart)v.findViewById(R.id.bargraph);

        //createRandomBarGraph("2017/06/06", "2017/12/12");

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(35,0));
        barEntries.add(new BarEntry(42,1));
        barEntries.add(new BarEntry(53,2));
        barEntries.add(new BarEntry(28,3));
        barEntries.add(new BarEntry(34,4));
        barEntries.add(new BarEntry(41,5));

        BarDataSet bds = new BarDataSet(barEntries,"Consultas por mês");

        ArrayList<String> datess = new ArrayList<>();
        datess.add("Julho");
        datess.add("Agosto");
        datess.add("Setembro");
        datess.add("Outubro");
        datess.add("Novembro");
        datess.add("Dezembro");

        BarData bardata = new BarData(datess,bds);
        barChart.setData(bardata);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setDescription("consultas por mes");

        return v;
    }
}
