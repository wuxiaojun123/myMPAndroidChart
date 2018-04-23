package com.wxj.chart.bar;

import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.wxj.chart.R;
import com.wxj.chart.barDemo.BarChart;
import com.wxj.chart.barDemo.Utils.ColorTemplate;
import com.wxj.chart.barDemo.Utils.MPPointF;
import com.wxj.chart.barDemo.components.YAxis;
import com.wxj.chart.barDemo.data.BarData;
import com.wxj.chart.barDemo.data.BarDataSet;
import com.wxj.chart.barDemo.data.BarEntry;
import com.wxj.chart.barDemo.data.Entry;
import com.wxj.chart.barDemo.highLine.Highlight;
import com.wxj.chart.barDemo.interfaces.IBarDataSet;
import com.wxj.chart.barDemo.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * Created by wuxiaojun on 2018/3/29.
 */

public class BarChartActivity extends FragmentActivity implements OnChartValueSelectedListener {

    private BarChart mChart;
    protected Typeface mTfLight;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        mChart = findViewById(R.id.id_barchart);
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");


        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);

        setData(12, 50);
    }

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            if (Math.random() * 100 < 25) {
                yVals1.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.star)));
            } else {
                yVals1.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");

            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            mChart.setData(data);
        }
    }


    protected RectF mOnValueSelectedRectF = new RectF();

    @Override public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        mChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + mChart.getLowestVisibleX() + ", high: "
                        + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override public void onNothingSelected() {

    }
}
