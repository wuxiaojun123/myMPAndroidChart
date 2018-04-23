package com.wxj.chart.barDemo.interfaces;


import com.wxj.chart.barDemo.Utils.Transformer;
import com.wxj.chart.barDemo.components.YAxis;
import com.wxj.chart.barDemo.data.BarLineScatterCandleBubbleData;


public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(YAxis.AxisDependency axis);
    boolean isInverted(YAxis.AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
