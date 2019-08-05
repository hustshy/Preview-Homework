package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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

public class LinearAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;
    public LinearAdapter2(Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linechart_2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((LinearViewHolder)holder).lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(mContext, "line_chart.json", LineChartBean.class);
        ((LinearViewHolder)holder).incomeBeanList = ((LinearViewHolder)holder).lineChartBean.getGRID0().getResult().getClientAccumulativeRate();
        ((LinearViewHolder)holder).lineChartManager = new LineChartManager(((LinearViewHolder)holder).lineChart);
        ((LinearViewHolder)holder).lineChartManager.showLineChart(((LinearViewHolder)holder).incomeBeanList, "qqq", Color.parseColor("#11C2EE"));
        ((LinearViewHolder)holder).lineChartManager.setMarkerView(mContext);
        Log.d("life","-----被bind----");
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2 != 0){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class LinearViewHolder extends RecyclerView.ViewHolder {
        private LineChart lineChart;
        private LineChartBean lineChartBean;
        private List<IncomeBean> incomeBeanList;
        private LineChartManager lineChartManager;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            lineChart = itemView.findViewById(R.id.line_chart_2);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
