package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappsur.R;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

import Manager.LineChartManager;
import data.IncomeBean;
import data.LineChartBean;
import utils.LocalJsonAnalyzeUtil;

public class LinearChartAdapter extends RecyclerView.Adapter<LinearChartAdapter.LinearViewHolder> {
    private Context mContext;
    public LinearChartAdapter(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public LinearChartAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_line_chart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearChartAdapter.LinearViewHolder holder, int position) {
        holder.lineChartManager.showLineChart(holder.incomeBeanList,"qqq", Color.parseColor("#000000"));
        holder.lineChartManager.setMarkerView(mContext);
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class LinearViewHolder extends RecyclerView.ViewHolder {
        private LineChart lineChart;
        private LineChartManager lineChartManager;
        private LineChartBean lineChartBean;
        private List<IncomeBean> incomeBeanList;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            lineChart = itemView.findViewById(R.id.btn_linear_chart);
            lineChartManager = new LineChartManager(lineChart);
            lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(mContext, "line_chart.json", LineChartBean.class);
            incomeBeanList = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();
        }
    }
}
