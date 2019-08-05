package com.example.myappsur;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

import Manager.LineChartManager;
import utils.LocalJsonAnalyzeUtil;
import data.IncomeBean;
import data.LineChartBean;

public class LineChartActivity extends AppCompatActivity {
    private LineChartBean lineChartBean;
    private List<IncomeBean> incomeBeanList;
    private LineChart lineChart;
    private LineChartManager lineChartManager;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        initData();
        initView();
        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LineChartActivity.this,RecyVLineCActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initData(){
        lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(this, "line_chart.json", LineChartBean.class);
        incomeBeanList = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();
    }
    private void initView(){
        lineChart = findViewById(R.id.linechart);
        lineChartManager = new LineChartManager(lineChart);
        lineChartManager.showLineChart(incomeBeanList, "qqq", Color.parseColor("#11C2EE"));
        lineChartManager.setMarkerView(this);
    }
}
