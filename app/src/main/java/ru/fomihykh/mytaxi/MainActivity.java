package ru.fomihykh.mytaxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDataSaveListener{
    DataBaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();
        dbHelper = new DataBaseHelper(this);
        ArrayList<String>taxis = dbHelper.readData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,taxis);
        ListView listView = findViewById(R.id.listView);

        List<Taxi>taxis1 = dbHelper.getTaxis();
        TaxiAdapter taxiAdapter = new TaxiAdapter(this,R.layout.taxi_list,taxis1);


        listView.setAdapter(taxiAdapter);
        listView.setSelection(adapter.getCount() - 1);
        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);

        Button startButtonView = findViewById(R.id.startButton);
        Button endButtonView = findViewById(R.id.endButton);
        TextView startCashTextView = findViewById(R.id.startCashText);
        TextView startNonCashTextView = findViewById(R.id.startNonCashText);
        TextView startMileageTextView = findViewById(R.id.startMiletageText);
        TextView infoStartTaxiText = findViewById(R.id.infoStartTaxiText);
        TextView addLossTextView = findViewById(R.id.addLoss);
        TextView lossTextView = findViewById(R.id.otherLoss);
        TextView gsmTextView = findViewById(R.id.gsmView);

        TextView gsmView = findViewById(R.id.gsmView);
        int gsmValue = pref.getInt("gsm",0);
        String gsmText = "Расходы на бензин: <font color='red'>"+gsmValue+"</font>.";
        gsmView.setText(Html.fromHtml(gsmText,Html.FROM_HTML_MODE_LEGACY));
        //Проверка
        TextView otherView = findViewById(R.id.otherLoss);
        int otherValue = pref.getInt("other_loss",0);
        String otherText = "Остальные расходы: <font color='red'>"+otherValue+"</font>.";
        otherView.setText(Html.fromHtml(otherText,Html.FROM_HTML_MODE_LEGACY));

        boolean isStartTaxi = pref.getBoolean("isStartTaxi",false);
        if (isStartTaxi){
            startButtonView.setVisibility(View.GONE);
            endButtonView.setVisibility(View.VISIBLE);
            startCashTextView.setVisibility(View.VISIBLE);
            startNonCashTextView.setVisibility(View.VISIBLE);
            startMileageTextView.setVisibility(View.VISIBLE);
            infoStartTaxiText.setVisibility(View.VISIBLE);
            addLossTextView.setVisibility(View.VISIBLE);
            lossTextView.setVisibility(View.VISIBLE);
            gsmTextView.setVisibility(View.VISIBLE);

            int cash = pref.getInt("cash",0);
            int nonCash = pref.getInt("nonCash",0);
            int mileage = pref.getInt("mileage",0);

            String cashText = "Наличка: <font color='green'>"+cash+"</font>.";
            String nonCashText = "Безнал: <font color='green'>"+nonCash+"</font>.";
            String mileageText = "Пробег: <font color='blue'>"+mileage+"</font>.";

            startCashTextView.setText(Html.fromHtml(cashText,Html.FROM_HTML_MODE_LEGACY));
            startNonCashTextView.setText(Html.fromHtml(nonCashText,Html.FROM_HTML_MODE_LEGACY));
            startMileageTextView.setText(Html.fromHtml(mileageText,Html.FROM_HTML_MODE_LEGACY));
        }else {
            startButtonView.setVisibility(View.VISIBLE);
            endButtonView.setVisibility(View.GONE);
            startCashTextView.setVisibility(View.GONE);
            startNonCashTextView.setVisibility(View.GONE);
            startMileageTextView.setVisibility(View.GONE);
            infoStartTaxiText.setVisibility(View.GONE);
            addLossTextView.setVisibility(View.GONE);
            lossTextView.setVisibility(View.GONE);
            gsmTextView.setVisibility(View.GONE);
        }
    }

    public void start(View view) {
        Intent intent = new Intent(this,StartTaxi.class);
        startActivity(intent);
    }

    public void end(View view) {
        Intent intent = new Intent(this, CloseTaxi.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }

    public void addLoss(View view) {
        DialogAddLoss dialogAddLoss = new DialogAddLoss();
        dialogAddLoss.setDataSaveListener(MainActivity.this);
        dialogAddLoss.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void onDataSaved() {
        //Проверка
        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);
        TextView gsmView = findViewById(R.id.gsmView);
        int gsmValue = pref.getInt("gsm",0);
        String gsmText = "Расходы на бензин: <font color='red'>"+gsmValue+"</font>.";
        gsmView.setText(Html.fromHtml(gsmText,Html.FROM_HTML_MODE_LEGACY));

        //Проверка
        TextView otherView = findViewById(R.id.otherLoss);
        int otherValue = pref.getInt("other_loss",0);
        String otherText = "Остальные расходы: <font color='red'>"+otherValue+"</font>.";
        otherView.setText(Html.fromHtml(otherText,Html.FROM_HTML_MODE_LEGACY));
    }
}