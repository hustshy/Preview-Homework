package Manager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import data.IncomeBean;
import view.LineChartMarkView;

public class LineChartManager {
    private LineChart lineChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYAxis;           //右侧Y轴
    private LimitLine limitLine;        //限制线

    public LineChartManager(LineChart lineChart) {
        this.lineChart = lineChart;
        leftYAxis = lineChart.getAxisLeft();
        rightYAxis = lineChart.getAxisRight();
        xAxis = lineChart.getXAxis();

        initChart(lineChart);
    }

    private void initChart(LineChart lineChart) {
        lineChart.setDrawGridBackground(false);
        lineChart.setBackgroundColor(Color.WHITE);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(true);
        lineChart.getDescription().setEnabled(false);
        lineChart.setDragDecelerationFrictionCoef(0.3f);
        lineChart.setScaleEnabled(false);
        lineChart.setHighlightPerDragEnabled(true);
        lineChart.setPinchZoom(true);

        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(false);
        leftYAxis.setEnabled(false);
        rightYAxis.setEnabled(false);
    }
    private void initLineDataSet(LineDataSet lineDataSet, int color) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
    }
    /**
     * 展示一条曲线 默认x轴
     *
     * @param yData    y轴的数据
     * @param lineName 曲线名称
     * @param color    曲线颜色
     */
    public void showOneLineChart(List<Float> yData, String lineName, int color) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < yData.size(); i++) {
            entries.add(new Entry(yData.get(i), yData.get(i)));
        }

        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, lineName);
        initLineDataSet(lineDataSet, color);

        LineData data = new LineData();
        data.addDataSet(lineDataSet);
        lineChart.setData(data);
    }
    /**
     * 注意 集合的长度一致，在此未做处理
     *
     * @param yDataList List<Integer> 代表一条曲线的数据  yDataList.size 代表曲线的条数
     * @param lineNames 曲线名称
     * @param colors    曲线颜色
     */
    public void showMultiNormalLineChart(List<List<Float>> yDataList, List<String> lineNames, List<Integer> colors) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < yDataList.size(); i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < yDataList.get(i).size(); j++) {
                entries.add(new Entry(yDataList.get(i).get(j), yDataList.get(i).get(j)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, lineNames.get(i));
            initLineDataSet(lineDataSet, colors.get(i));
            dataSets.add(lineDataSet);
        }
        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
//    public void setDescription(String str) {
//        Description description = new Description();
//        description.setText(str);
//        lineChart.setDescription(description);
//        lineChart.invalidate();
//    }

    /**
     * 设置线条填充背景颜色
     *
     * @param drawable
     */
    public void setChartFillDrawable(Drawable drawable) {
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            LineDataSet lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            //避免在 initLineDataSet()方法中 设置了 lineDataSet.setDrawFilled(false); 而无法实现效果
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(drawable);
            lineChart.invalidate();
        }
    }

    /**
     * 展示曲线
     *
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart(final List<IncomeBean> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            IncomeBean data = dataList.get(i);
            Entry entry = new Entry(i, (float) data.getValue());
            entries.add(entry);
        }

        /******根据需求的不同 在此在次设置X Y轴的显示内容******/
        //是否绘制X轴线
        xAxis.setDrawAxisLine(false);


        leftYAxis.setLabelCount(8);
        leftYAxis.setDrawZeroLine(false); // draw a zero line
        leftYAxis.setZeroLineColor(Color.GRAY);
        leftYAxis.setZeroLineWidth(1f);
        leftYAxis.setAxisLineWidth(1f);
        leftYAxis.setAxisLineColor(Color.GRAY);
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries,name);
        initLineDataSet(lineDataSet, color);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        float ratio = (float) dataList.size()/(float) 10;
        lineChart.zoom(ratio,1f,0,0);
    }

    /**
     * 设置 可以显示X Y 轴自定义值的 MarkerView
     */
    public void setMarkerView(Context context) {
        LineChartMarkView mv = new LineChartMarkView(context, xAxis.getValueFormatter());
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
        lineChart.invalidate();
    }
}
