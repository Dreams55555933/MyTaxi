package ru.fomihykh.mytaxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);

        Button startButtonView = findViewById(R.id.startButton);
        Button endButtonView = findViewById(R.id.endButton);
        TextView startCashTextView = findViewById(R.id.startCashText);
        TextView startNonCashTextView = findViewById(R.id.startNonCashText);
        TextView startMileageTextView = findViewById(R.id.startMiletageText);
        TextView infoStartTaxiText = findViewById(R.id.infoStartTaxiText);

        boolean isStartTaxi = pref.getBoolean("isStartTaxi",false);
        if (isStartTaxi){
            startButtonView.setVisibility(View.GONE);
            endButtonView.setVisibility(View.VISIBLE);
            startCashTextView.setVisibility(View.VISIBLE);
            startNonCashTextView.setVisibility(View.VISIBLE);
            startMileageTextView.setVisibility(View.VISIBLE);
            infoStartTaxiText.setVisibility(View.VISIBLE);

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
}