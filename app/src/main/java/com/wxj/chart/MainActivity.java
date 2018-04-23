package com.wxj.chart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wxj.chart.bar.BarChartActivity;

public class MainActivity extends AppCompatActivity {

    private TextView id_tv_bar_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_tv_bar_chart = this.findViewById(R.id.id_tv_bar_chart);
        id_tv_bar_chart.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BarChartActivity.class));
            }
        });
    }


}
