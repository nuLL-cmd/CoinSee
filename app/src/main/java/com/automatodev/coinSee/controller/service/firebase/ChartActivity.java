package com.automatodev.coinSee.controller.service.firebase;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.automatodev.coinSee.R;
import com.automatodev.coinSee.controller.callback.API.RetrofitCallback;
import com.automatodev.coinSee.controller.entity.CoinChildr;
import com.automatodev.coinSee.controller.service.API.CoinService;
import com.automatodev.coinSee.view.component.ChartLine;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.P)
public class ChartActivity extends AppCompatActivity {
    private ChartLine chartLine;
    private LineChart lineChart;
    private RelativeLayout relativeChart_chart;
    private ProgressBar progressChart_chart;
    private CoinService coinService;
    private TextView txtTitleCoin_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        lineChart = findViewById(R.id.lineChart_chart);
        relativeChart_chart = findViewById(R.id.relativeChart_chart);
        progressChart_chart = findViewById(R.id.progressChart_chart);
        txtTitleCoin_chart = findViewById(R.id.txtTitleCoin_chart);
        coinService = new CoinService(this);
        getData();
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            txtTitleCoin_chart.setText(bundle.getString("dataCoin"));
            coinService.requestRangeDays(bundle.getString("dataChart"), new RetrofitCallback() {
                @Override
                public void onSucces(List<CoinChildr> coinChildrList) throws InterruptedException {
                    chartLine = new ChartLine(ChartActivity.this, lineChart, coinChildrList, 1);
                    chartLine.makeGraph();
                    relativeChart_chart.setVisibility(View.GONE);
                    progressChart_chart.setVisibility(View.GONE);
                }

                @Override
                public void onSucces(CoinChildr coinChildr0) throws InterruptedException {
                }
            });
        }
    }
    public void backDetailsActivity(View view){
        NavUtils.navigateUpFromSameTask(ChartActivity.this);
    }
}
