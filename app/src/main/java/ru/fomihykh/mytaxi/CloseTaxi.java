package ru.fomihykh.mytaxi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CloseTaxi extends AppCompatActivity {

    EditText cashViewText;
    EditText nonCashViewText;
    EditText mileageViewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.taxi_close);

        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);

        cashViewText = findViewById(R.id.cash);
        nonCashViewText = findViewById(R.id.nonCash);
        mileageViewText = findViewById(R.id.mileage);



        cashViewText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!cashViewText.getText().toString().isEmpty() && !nonCashViewText.getText().toString().isEmpty()){
                    int cashBefore = pref.getInt("cash",0);
                    int cashAfter = Integer.parseInt(cashViewText.getText().toString());
                    int resultCash = cashAfter-cashBefore;

                    int nonCashBefore = pref.getInt("nonCash",0);
                    int nonCashAfter = Integer.parseInt(nonCashViewText.getText().toString());
                    int resultNonCash = nonCashAfter-nonCashBefore;

                    int result = resultCash+resultNonCash;
                    TextView profitTextView = findViewById(R.id.profit);
                    profitTextView.setText("Прибыль: "+result);
                }
            }
        });
        nonCashViewText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!cashViewText.getText().toString().isEmpty() && !nonCashViewText.getText().toString().isEmpty()){
                    int cashBefore = pref.getInt("cash",0);
                    int cashAfter = Integer.parseInt(cashViewText.getText().toString());
                    int resultCash = cashAfter-cashBefore;

                    int nonCashBefore = pref.getInt("nonCash",0);
                    int nonCashAfter = Integer.parseInt(nonCashViewText.getText().toString());
                    int resultNonCash = nonCashAfter-nonCashBefore;

                    int result = resultCash+resultNonCash;
                    TextView profitTextView = findViewById(R.id.profit);
                    profitTextView.setText("Прибыль: "+result+"р.");
                }
            }
        });
        mileageViewText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!mileageViewText.getText().toString().isEmpty()){
                    int mileageBefore = pref.getInt("mileage",0);
                    int mileageAfter = Integer.parseInt(mileageViewText.getText().toString());
                    int result = mileageAfter-mileageBefore;
                    TextView mileageResult = findViewById(R.id.mileageAfter);
                    mileageResult.setText("Пробег: "+result+"км.");
                }
            }
        });
    }

    public void closeTaxi(View view) {
        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();

        int afterCash = pref.getInt("cash",0);
        int afterNonCash = pref.getInt("nonCash",0);
        int afterMileage = pref.getInt("mileage",0);

        Date instance = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        String date = formater.format(instance);




    }

    public void cansel(View view) {
        finish();
    }


}