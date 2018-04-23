package com.wxj.chart.barDemo.interfaces;


import com.wxj.chart.barDemo.components.YAxis;
import com.wxj.chart.barDemo.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
