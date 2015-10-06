package de.davidartmann.charowin.fragment.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;

/**
 * Fragment View which contains the charts to visualize the statistics.
 *
 * Created by David on 06.10.2015.
 */
public class TrainingFragmentCharts extends Fragment {

    private static final String TRAINING_FRAGMENT_CHARTS =
            TrainingFragmentCharts.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_charts, container, false);
        PieChart pieChart = (PieChart) view.findViewById(R.id.fragment_training_charts_linechart);
        pieChart.animateX(2000, Easing.EasingOption.EaseInCubic);
        //TODO: testing with mockup data
        List<String> xVals = new ArrayList<>();
        xVals.add("Bizeps");
        xVals.add("Trizeps");
        xVals.add("Brust");
        xVals.add("Rücken");
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(2, 0));
        entries.add(new Entry(3, 0));
        entries.add(new Entry(5, 0));
        entries.add(new Entry(3, 0));
        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        PieData pieData = new PieData(xVals, set);
        pieChart.setData(pieData);
        pieChart.setDescription("Übungsverteilung");
        return view;
    }
}
