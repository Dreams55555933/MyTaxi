package ru.fomihykh.mytaxi;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
    TextView profitTextView;
    private DataBaseHelper dbHelper;
    int profit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.taxi_close);

        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);

        cashViewText = findViewById(R.id.cashClose);
        nonCashViewText = findViewById(R.id.nonCash);
        mileageViewText = findViewById(R.id.mileage);

        EditText npEditText = findViewById(R.id.np);
        EditText gsmEditText = findViewById(R.id.gsm);

        int np = pref.getInt("other_loss",0);
        int gsm = pref.getInt("gsm",0);
        npEditText.setText(String.valueOf(np));
        gsmEditText.setText(String.valueOf(gsm));



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

                    profit = resultCash+resultNonCash;
                    profitTextView = findViewById(R.id.profit);
                    profitTextView.setText("Прибыль: "+profit);
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

                    profit = resultCash+resultNonCash;
                    TextView profitTextView = findViewById(R.id.profit);
                    profitTextView.setText("Прибыль: "+profit+"р.");
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
        EditText npEditText = findViewById(R.id.np);
        EditText gsmEditText = findViewById(R.id.gsm);
        EditText commentEditText = findViewById(R.id.comment);
        EditText mileagEditText = findViewById(R.id.mileage);

        Date instance = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        String date = formater.format(instance);
        int np = Integer.parseInt(npEditText.getText().toString());
        int gsm = Integer.parseInt(gsmEditText.getText().toString());
        String comment = commentEditText.getText().toString();


        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);
        int mileage = pref.getInt("mileage",0);
        int afterMileage = Integer.parseInt(mileagEditText.getText().toString());
        int resultMileage = afterMileage-mileage;
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.clear().apply();

        dbHelper = new DataBaseHelper(this);
        dbHelper.addData(date,profit,np,gsm,comment,mileage);
        finish();
    }

    public void cansel(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}