package com.example.test1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab1 extends Fragment {
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1,container,false);
        final GraphView graph = (GraphView)v.findViewById(R.id.graph);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        try {
            LineGraphSeries <DataPoint> series = new LineGraphSeries< >(new DataPoint[] {
                    new DataPoint(0, 1),
                    new DataPoint(Integer.valueOf(1), Integer.valueOf(5)),
                    new DataPoint(Integer.valueOf(4), Integer.valueOf(20)),
                    new DataPoint(Integer.valueOf(6), Integer.valueOf(4)),
                    new DataPoint(Integer.valueOf(8), Integer.valueOf(5))
            });
            graph.addSeries(series);
            series.setAnimated(true);
            series.setThickness(10);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return v;
    }
}
