package com.example.bookkeeping;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class analysis extends AppCompatActivity {
    private ProgressBar progressBar;
    private LineChart lineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysislayout);
        findViews();
        setProgressBar();
        ArrayList<Entry> values_green = new ArrayList<>();
        values_green.add(new Entry(1, 30.65f));
        values_green.add(new Entry(1.3f, 30.69f));
        values_green.add(new Entry(3.3f, 10.10f));
        values_green.add(new Entry(5.8f, 30.58f));
        values_green.add(new Entry(6, 30.58f));
        ArrayList<Entry> values_yellow = new ArrayList<>();
        values_yellow.add(new Entry(1f, 30.63f));
        values_yellow.add(new Entry(1.5f, 30.65f));
        values_yellow.add(new Entry(1.8f, 30.66f));
        values_yellow.add(new Entry(5.7f, 30.53f));
        values_yellow.add(new Entry(6, 30.53f));
// greenLine
        ArrayList<Entry> values_end = new ArrayList<>();
        values_end.add(new Entry(6, 30.58f));
//yellowLine
        ArrayList<Entry> values1_end = new ArrayList<>();
        values1_end.add(new Entry(6, 30.53f));
        initDataSet(values_green, values_yellow, values_end, values1_end);

    }

    private void setProgressBar() {
        progressBar.setMax(1000);
        progressBar.setProgress(100);
    }

    private void findViews() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        lineChart = (LineChart) findViewById(R.id.linechart);
    }

    private void initDataSet(final ArrayList<Entry> values, ArrayList<Entry> values1, ArrayList<Entry> values_end, ArrayList<Entry> values1_end) {
        final LineDataSet set, set1 , set_end, set1_end;
        // greenLine
        set = new LineDataSet(values, "");
        set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set.setColor(getResources().getColor(R.color.green));//線的顏色
        set.setLineWidth(1.5f);//線寬
        set.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)

//greenLine最後的圓點
        set_end = new LineDataSet(values_end, "");
        set_end.setCircleColor(getResources().getColor(R.color.green));//圓點顏色
        set_end.setColor(getResources().getColor(R.color.green));//線的顏色
        set_end.setCircleRadius(4);//圓點大小
        set_end.setDrawCircleHole(false);//圓點為實心(預設空心)
        set_end.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)

        set1 = new LineDataSet(values_end, "");
        set1.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set1.setColor(getResources().getColor(R.color.green));//線的顏色
        set1.setLineWidth(1.5f);//線寬
        set1.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set1.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)

//greenLine最後的圓點
        set1_end = new LineDataSet(values1_end, "");
        set1_end.setCircleColor(getResources().getColor(R.color.green));//圓點顏色
        set1_end.setColor(getResources().getColor(R.color.green));//線的顏色
        set1_end.setCircleRadius(4);//圓點大小
        set1_end.setDrawCircleHole(false);//圓點為實心(預設空心)
        set1_end.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        /**
         * yellowLine及其最後的圓點設定可比照如上greenLine設定，不再列示
         */

//理解爲多條線的集合
        LineData data = new LineData(set, set1, set_end, set1_end);
        lineChart.setData(data);//一定要放在最後
        lineChart.invalidate();//繪製圖表
    }
}
