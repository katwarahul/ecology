package com.example.test1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab2 extends Fragment {
    private int calsBurned = 4,calsConsumed = 10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2,container,false);
        TextView textView1 = (TextView)v.findViewById(R.id.number_of_calories);
        textView1.setText(calsBurned+"/"+calsConsumed);
        // Calculate the slice size and update the pie chart:
        ProgressBar pieChart = v.findViewById(R.id.stats_progressbar);
        pieChart.setAlpha(0.0001f);
        pieChart.animate().alpha(1).rotation(2880).setDuration(3000);
        double d = (double) calsBurned / (double) calsConsumed;
        int progress = (int) (d * 100);
        pieChart.setProgress(progress);
        return v;
    }
}
